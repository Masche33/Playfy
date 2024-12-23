package tests;

import java.util.ArrayList;
import main.java.Duration;

public class DurationClient {
    public static void main(String[] args) {
        ArrayList<Duration> list = new ArrayList<>();
        list.add(new Duration(10));
        list.add(new Duration(70));
        list.add(new Duration(3600));
        list.add(Duration.ZERO);
        for (final Duration duration : list) {
            System.out.println(duration);
        }
    }  
}
