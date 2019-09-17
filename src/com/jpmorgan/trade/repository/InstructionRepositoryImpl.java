package com.jpmorgan.trade.repository;

import com.jpmorgan.trade.constants.Currency;
import com.jpmorgan.trade.model.Instruction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by dn86vid on 22/08/2019.
 */
public class InstructionRepositoryImpl implements InstructionRepository{
    @Override
    public List<Instruction> getInstructions() {

        Instruction instruction1 = new Instruction();
        instruction1.setEntity("foo");
        instruction1.setType("BUY");
        instruction1.setAgreedFx(0.50);
        instruction1.setCurrency(Currency.SGP);
        instruction1.setInstructionDate(LocalDate.of(2019, Month.AUGUST, 03));
        instruction1.setSettlementDate(LocalDate.of(2019, Month.AUGUST, 04));
        instruction1.setUnits(50);
        instruction1.setPricePerUnit(100);

        Instruction instruction2 = new Instruction();
        instruction2.setEntity("bar");
        instruction2.setType("SELL");
        instruction2.setAgreedFx(0.22);
        instruction2.setCurrency(Currency.AED);
        instruction2.setInstructionDate(LocalDate.of(2019, Month.AUGUST, 9));
        instruction2.setSettlementDate(LocalDate.of(2019, Month.AUGUST, 10));
        instruction2.setUnits(200);
        instruction2.setPricePerUnit(125.50);


        Instruction instruction3 = new Instruction();
        instruction3.setEntity("mac");
        instruction3.setType("SELL");
        instruction3.setAgreedFx(0.70);
        instruction3.setCurrency(Currency.GBP);
        instruction3.setInstructionDate(LocalDate.of(2019, Month.JULY, 11));
        instruction3.setSettlementDate(LocalDate.of(2019, Month.JULY, 13));
        instruction3.setUnits(125);
        instruction3.setPricePerUnit(190.90);

        Instruction instruction4 = new Instruction();
        instruction4.setEntity("foo");
        instruction4.setType("SELL");
        instruction4.setAgreedFx(0.60);
        instruction4.setCurrency(Currency.SAR);
        instruction4.setInstructionDate(LocalDate.of(2019, Month.JULY, 18));
        instruction4.setSettlementDate(LocalDate.of(2019, Month.JULY, 19));
        instruction4.setUnits(500);
        instruction4.setPricePerUnit(170.25);


        Instruction instruction5 = new Instruction();
        instruction5.setEntity("bar");
        instruction5.setType("BUY");
        instruction5.setAgreedFx(0.75);
        instruction5.setCurrency(Currency.AED);
        instruction5.setInstructionDate(LocalDate.of(2019, Month.AUGUST, 20));
        instruction5.setSettlementDate(LocalDate.of(2019, Month.AUGUST, 21));
        instruction5.setUnits(275);
        instruction5.setPricePerUnit(270.25);

        Instruction instruction6 = new Instruction();
        instruction6.setEntity("mac");
        instruction6.setType("SELL");
        instruction6.setAgreedFx(0.80);
        instruction6.setCurrency(Currency.SGP);
        instruction6.setInstructionDate(LocalDate.of(2019, Month.JULY, 10));
        instruction6.setSettlementDate(LocalDate.of(2019, Month.JULY, 11));
        instruction6.setUnits(600);
        instruction6.setPricePerUnit(90.70);

        Instruction instruction7 = new Instruction();
        instruction7.setEntity("bar");
        instruction7.setType("BUY");
        instruction7.setAgreedFx(0.80);
        instruction7.setCurrency(Currency.SGP);
        instruction7.setInstructionDate(LocalDate.of(2019, Month.JULY, 23));
        instruction7.setSettlementDate(LocalDate.of(2019, Month.JULY, 24));
        instruction7.setUnits(225);
        instruction7.setPricePerUnit(78.25);

        Instruction instruction8 = new Instruction();
        instruction8.setEntity("mac");
        instruction8.setType("BUY");
        instruction8.setAgreedFx(0.68);
        instruction8.setCurrency(Currency.AED);
        instruction8.setInstructionDate(LocalDate.of(2019, Month.AUGUST, 07));
        instruction8.setSettlementDate(LocalDate.of(2019, Month.AUGUST, 8));
        instruction8.setUnits(500);
        instruction8.setPricePerUnit(212.35);

        return asList(instruction1, instruction2, instruction3, instruction4, instruction5, instruction6, instruction7, instruction8);
    }
}
