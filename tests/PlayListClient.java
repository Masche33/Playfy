package tests;

import java.util.ArrayList;
import java.util.Iterator;
import main.java.Album;
import main.java.Album.Track;
import main.java.Duration;
import main.java.PlayList;

public class PlayListClient {
        public static void main(String[] args) {

            ArrayList<String> names = new ArrayList<>();
            ArrayList<Duration> durations = new ArrayList<>();
            fillAlbum1(names, durations);
            
            Album album1 = new Album("Bardomagno", names, durations);
            names.clear();
            durations.clear();
            fillAlbum2(names, durations);
            Album album2 = new Album("Rustage", names, durations);
            PlayList pl = new PlayList("Favorites");
            pl.add(album1.getTrackByPosition(1));
            pl.add(album1.getTrackByPosition(2));
            pl.add(album1.getTrackByPosition(3));
            pl.add(album1.getTrackByPosition(4));
            pl.add(album1.getTrackByPosition(5));
            pl.add(album1.getTrackByPosition(6));
            pl.add(album1.getTrackByPosition(7));
            pl.add(album1.getTrackByPosition(8));
            pl.add(album1.getTrackByPosition(9));
            pl.add(album1.getTrackByPosition(10));
            pl.add(album2.getTrackByPosition(1));
            pl.add(album2.getTrackByPosition(2));
            pl.add(album2.getTrackByPosition(3));
            pl.add(album2.getTrackByPosition(4));
            pl.add(album2.getTrackByPosition(5));
            pl.add(album2.getTrackByPosition(6));
            pl.add(album2.getTrackByPosition(7));
            pl.add(album2.getTrackByPosition(8));
            pl.add(album2.getTrackByPosition(9));
            pl.add(album2.getTrackByPosition(10));

            System.out.println(pl);
            Iterator<Track> i = pl.iterator("Bardomagno");
            while(i.hasNext()){
                System.out.println(i.next());
            }
            i = pl.iterator("Rustage");
            while(i.hasNext()){
                System.out.println(i.next());
            }

        }
    
        
        private static void fillAlbum1(ArrayList<String> names, ArrayList<Duration> durations){
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
        }
    
        private static void fillAlbum2(ArrayList<String> names, ArrayList<Duration> durations){
            names.add("Blood");
            names.add("FREAK");
            names.add("Turn the volume up");
            names.add("ASSIMILATE");
            names.add("Running in Blind");
            names.add("Devil's Luck");
            names.add("SOLO");
            names.add("Hellfire");
            names.add("Drums of liberation");
            names.add("Too great");
            durations.add(new Duration("3:17"));
            durations.add(new Duration("2:21"));
            durations.add(new Duration("3:06"));
            durations.add(new Duration("2:48"));
            durations.add(new Duration("3:07"));
            durations.add(new Duration("2:53"));
            durations.add(new Duration("2:51"));
            durations.add(new Duration("3:22"));
            durations.add(new Duration("3:34"));
            durations.add(new Duration("2:45"));
    
    }
}
