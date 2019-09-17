package com.jpmorgan.trade;

import com.jpmorgan.trade.service.TradeService;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dn86vid on 22/08/2019.
 */
public class TradeApplication {

    public static void main(String[] args) {
        TradeService tradeService = new TradeService();

        Map<LocalDate, Double> dayReportBuy = tradeService.prepareDayReport("BUY");
        tradeService.displayDayWiseReport(dayReportBuy,"BUY");

        Map<LocalDate, Double> dayReportSell = tradeService.prepareDayReport("SELL");
        tradeService.displayDayWiseReport(dayReportSell,"SELL");

        LinkedHashMap<String, Double> rankingReportBuy = tradeService.prepareRankingReport("BUY");
        tradeService.displayRankingReport(rankingReportBuy,"BUY");

        LinkedHashMap<String, Double> rankingReportSell = tradeService.prepareRankingReport("SELL");
        tradeService.displayRankingReport(rankingReportSell,"SELL");

    }
}
