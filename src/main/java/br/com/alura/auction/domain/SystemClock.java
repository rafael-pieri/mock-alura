package br.com.alura.auction.domain;

import java.util.Calendar;

public class SystemClock implements Clock {

    public Calendar today() {
        return Calendar.getInstance();
    }
}