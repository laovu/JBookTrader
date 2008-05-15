package com.jbooktrader.indicator;

import com.jbooktrader.platform.indicator.*;
import com.jbooktrader.platform.marketdepth.*;

/**
 * Regressed depth balance
 */
public class Slope extends Indicator {
    private final int period;

    public Slope(MarketBook marketBook, int period) {
        super(marketBook);
        this.period = period;
    }

    @Override
    public double calculate() {
        int index = marketBook.size() - period - 1;

        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;
        for (int x = 1; x <= period; x++) {
            sumX += x;
            int y = marketBook.getMarketDepth(++index).getMidBalance();
            sumY += y;
            sumXY += x * y;
            sumXX += x * x;
        }

        double denom = period * sumXX - sumX * sumX;
        value = period * (period * sumXY - sumX * sumY) / denom;
        return value;

    }
}
