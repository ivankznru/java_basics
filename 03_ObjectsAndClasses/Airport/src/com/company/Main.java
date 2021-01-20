package com.company;

import com.skillbox.airport.Airport;

import static com.skillbox.airport.Airport.getInstance;

public class Main {


    public static synchronized void main(String[] args) {
        Airport airport = getInstance();

        var amountTerminals = airport.getTerminals().size();
        var amountAircrafts = airport.getAllAircrafts().size();
        System.out.println("Число терминолов:" + amountTerminals);
        System.out.println("Число самолетов:" + amountAircrafts);
        for (int i = 0; i < amountAircrafts; i++) {
            System.out.println ("\t№"+ (i+1) +": "+ airport.getAllAircrafts().get(i));

        }

    }
}