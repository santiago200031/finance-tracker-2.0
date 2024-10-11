package org.finance.automation;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutomationAPIBTC implements TradeActions {
    //TODO Take to config file to make configurable and use everywhere
    private final String BTC_TO_BUY = "2";
    private final String BTC_TO_SELL = "0.00009";


    @Override
    public void doBuy() {


    }

    @Override
    public void doSell() {


    }
}
