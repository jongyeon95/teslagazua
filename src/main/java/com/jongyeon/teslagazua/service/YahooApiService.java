package com.jongyeon.teslagazua.service;

import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
@Service
public class YahooApiService {

        /*
         * 주식정보 API 주소
         * https://financequotes-api.com/
         *
         */


        public Stock getSingleStock(String s) throws IOException {
            Stock stock= YahooFinance.get(s);
            return stock;
        }

        Map<String, Stock> getStocks(String[] s) throws IOException {
            return YahooFinance.get(s);
        }

        public Stock getPeriodStock(String s) throws  IOException{
            return getPeriodStock(s,-1);
        }

        Stock getPeriodStock(String s , int period) throws IOException{
            if(s.equals(""))
                return null;
            Calendar from = Calendar.getInstance();
            Calendar to = Calendar.getInstance();
            from.add(Calendar.DATE, period); // from 5 years ago
            return YahooFinance.get(s,from, to, Interval.DAILY);
        }
}
