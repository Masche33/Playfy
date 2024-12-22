package main.java;

import java.util.ArrayList;

public class AlbumClient {

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Duration> durations = new ArrayList<>();
        names.add("Magister barbero");
        names.add("Giovane Pulzella");
        names.add("Cerveza y Latifondo");
        names.add("Hanno ucciso Carlo Magno");
        names.add("Marco Polo");
        names.add("Li bardi son tornati in locanda");
        names.add("Nel mio feudo");
        names.add("Game of signorie");
        names.add("Federico II c'è");
        names.add("Asburgo");
        durations.add(new Duration("4:26"));
        durations.add(new Duration("4:22"));
        durations.add(new Duration("4:00"));
        durations.add(new Duration("3:29"));
        durations.add(new Duration("3:51"));
        durations.add(new Duration("3:24"));
        durations.add(new Duration("4:04"));
        durations.add(new Duration("3:40"));
        durations.add(new Duration("3:39"));
        durations.add(new Duration("4:43"));


        Album a = new Album("Bardomagno", names, durations);
        System.out.println(a);
    }
}
