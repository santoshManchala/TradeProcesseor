package com.jpmorgan.trade;

import com.jpmorgan.trade.service.TradeService;

/**
 * Created by dn86vid on 22/08/2019.
 */
public class TradeApplication {

    public static void main(String[] args) {
        TradeService tradeService = new TradeService();
        tradeService.prepareDayReport("BUY");
        tradeService.prepareDayReport("SELL");
        tradeService.prepareRankingReport("BUY");
        tradeService.prepareRankingReport("SELL");

    }
}
