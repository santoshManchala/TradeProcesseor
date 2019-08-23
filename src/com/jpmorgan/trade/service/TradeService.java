package com.jpmorgan.trade.service;

import com.jpmorgan.trade.constants.Currency;
import com.jpmorgan.trade.model.Instruction;
import com.jpmorgan.trade.repository.InstructionRepository;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by dn86vid on 22/08/2019.
 */
public class TradeService {

    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * This method will take type parameter as input (Buy/Sell)
     * delegates the responsibility of calculation and Printing
     * @param type
     */

    public void prepareDayReport(String type){
        Map<LocalDate, Double> map = getDayWiseCalculation(type);
        displayDayWiseReport(map,type);
    }

    /**
     * This method will take type parameter as input (Buy/Sell)
     * delegates the responsibility of calculation and Printing
     */
    public void prepareRankingReport(String type) {
        Map<String, Double> map = getEntityWiseCalculation(type);

        LinkedHashMap<String, Double> collect = map.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                        .reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        displayRankingReport(collect,type);

    }

    /**
     * This method will take type parameter as input (Buy/Sell)
     * group the transactions done by Type and returns a map of
     * transactions done on specific date and the amount of it
     * @param type
     * @return Map
     */
    private Map<LocalDate, Double> getDayWiseCalculation(String type) {

        InstructionRepository instructionRepository = new InstructionRepository();
        List<Instruction> instructions = instructionRepository.getInstructions();

        instructions.forEach(instruction -> {
            updateSettlementDate(instruction);
        });

        Map<String, List<Instruction>> buySellToInstructionsMap = instructions.stream().collect(Collectors.groupingBy(ins -> ins.getType()));
        return getMap(buySellToInstructionsMap.get(type));
    }


    /**
     * This method takes list as input and returns
     * map of calculated total amount of transactions
     * done for each day
     * @param list
     * @return
     */
    private Map<LocalDate, Double> getMap(List<Instruction> list) {

        Map<LocalDate, List<Instruction>> settlementDateToInstructionsMap = list.stream().collect(Collectors.groupingBy(inst -> inst.getSettlementDate()));
        Map<LocalDate, Double> collect = settlementDateToInstructionsMap.entrySet().stream()
                .collect(Collectors
                        .toMap(entry -> entry.getKey(), entry -> entry.getValue().stream()
                                .map(i -> i.calculateUSDAmount()).reduce(0d, (a, b) -> a + b)));
        return collect;
    }

    /**
     * This method will take type parameter as input (Buy/Sell)
     * group by Type and returns the map with Entity Type and
     * the total value of the entity
     * @param type
     */

    private Map<String, Double> getEntityWiseCalculation(String type) {

        InstructionRepository instructionRepository = new InstructionRepository();
        List<Instruction> instructions = instructionRepository.getInstructions();

        instructions.forEach(instruction -> {
            updateSettlementDate(instruction);
        });

        Map<String, List<Instruction>> buySellToInstructionsMap = instructions.stream().collect(Collectors.groupingBy(ins -> ins.getType()));
        return getMapByEntity(buySellToInstructionsMap.get(type));
    }


    /**
     * This method will take the input list
     * and returns  a map of Entity and its total value of
     * transactions
     * @param list
     * @return
     */

    private Map<String, Double> getMapByEntity(List<Instruction> list) {
        Map<String, List<Instruction>> settlementDateToInstructionsMap = list.stream().collect(Collectors.groupingBy(inst -> inst.getEntity()));
        Map<String, Double> collect = settlementDateToInstructionsMap.entrySet().stream()
                .collect(Collectors
                        .toMap(entry -> entry.getKey(), entry -> entry.getValue().stream()
                                .map(i -> i.calculateUSDAmount()).reduce(0d, (a, b) -> a + b)));
        return collect;
    }





    /**
     * This method will display each day wise report
     * for all entities with total amount of trade
     * done Buy/Sell
     * @param map,type
     */

    private void displayDayWiseReport(Map<LocalDate, Double> map, String type){
        System.out.println("***** Daywise settled Amount in USD for "+type+" ***** ");
        map.forEach((date, amount) -> {
            System.out.println("Date:" + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " and amount $" + df.format(amount));
        });
        System.out.println("*************************************************\n");
    }

    /**
     * This method will display the ranking based on
     * the entity and category (Buy/Sell).
     *
     * @param map,type
     */

    private void displayRankingReport(Map<String, Double> map, String type) {
        System.out.println("***** Entity Ranking report on highest "+type+" Amount ****** ");
        map.forEach((entity,amount)->System.out.println(entity+ "  $"+ df.format(amount)));
        System.out.println("*************************************************\n");

    }


    /**
     * This method will set the actual settlement date for the trade
     * in instruction data object based on the day of week & currency.
     *
     * @param instruction
     */
    public void updateSettlementDate(Instruction instruction) {

        LocalDate settlementDate = instruction.getSettlementDate();
        LocalDate actualSettlementDate = settlementDate;
        if (Currency.AED.equals(instruction.getCurrency()) || Currency.SAR.equals(instruction.getCurrency())) {
            switch (settlementDate.getDayOfWeek()) {
                case FRIDAY:
                    actualSettlementDate = settlementDate.plusDays(2);
                    break;
                case SATURDAY:
                    actualSettlementDate = settlementDate.plusDays(1);
                    break;
                default:
                    break;
            }
        } else {
            switch (settlementDate.getDayOfWeek()) {
                case SATURDAY:
                    actualSettlementDate = settlementDate.plusDays(2);
                    break;
                case SUNDAY:
                    actualSettlementDate = settlementDate.plusDays(1);
                    break;
                default:
                    break;
            }
        }
        instruction.setSettlementDate(actualSettlementDate);
    }

}
