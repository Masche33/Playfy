package main.java;

import java.util.ArrayList;

public class DurationClient {
    public static void main(String[] args) {
        ArrayList<Duration> list = new ArrayList<>();
        list.add(new Duration(1));
        list.add(new Duration(60));
        list.add(new Duration(3600));
        list.add(new Duration(1));
        for (final Duration duration : list) {
            System.out.println(duration);
        }
    }  
}
