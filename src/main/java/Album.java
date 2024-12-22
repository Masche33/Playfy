package main.java;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An {@code Album} is an ordered, non empty {@code Set} of {@code Tracks}.
 * 
 * An {@code Album} can:
 * 
 * <ul>
 *      <li>Get a {@code Track} by it's title.
 *      <li>Get a {@code Track} given it's <i>position</i> in the {@code Album}.
 *      <li>Get the <i>position</i> of a {@code Track} given the {@code Track}.
 *      <li>Get the total duration
 * </ul>
 */
public class Album {
 
    /**
     * A class to represent a single {@code Track}.
     * 
     * It has a {@code title}, a{@code duration} and an {@code Album}.
     */
    public class Track implements Comparable<Track>{
        /**The {@code title} of the {@code Track}.*/
        private final String title;
        /**The {@code duration}  of the {@code Track}.*/
        private final Duration duration;

        /**
         * Creates a {@code Track}, given that {@code title} is not empty o {@code null} and duration is a positive integer.
         * 
         * @param title a non {@code null}, non {@code empty} {@code String}.
         * @param duration a positive number.
         * @throws NullPointerException Iff {@code title} is {@code null}
         * @throws IllegalArgumetException Iff {@code title} is {@code empty} or blank.
         */
        private Track(String title, Duration duration){
            Objects.requireNonNull(title);
            Objects.requireNonNull(duration);
            if(title.isBlank() || title.isEmpty())
                throw new IllegalArgumentException("Name cannot be blanck or empty");
            this.title = title;
            this.duration = duration;
        }

        

        /**
         * Getter of {@code this.title}
         * @return {@code this.title}
         */
        public String title(){
            return title;
        }

        /**
         * Getter of {@code this.duration}
         * @return {@code this.duration}
         */
        public Duration duration(){
            return duration;
        }

        /**
         * Returns the {@code Album} in wich the {@code Track} is.
         * @return the {@code Album} in wich the {@code Track} is.
         */
        public Album album(){
            return Album.this;
        }

        @Override
        public int compareTo(Track o) {
            return this.title.toUpperCase().compareTo(o.title.toUpperCase());
        }

        @Override
        public String toString() {
            return "\""+ title+ "\" (" +duration+")";
        }

    }

    SortedSet<Track> album;

    private final String title;

    public Album(final String title, final List<String> names, final List<Duration> duration){
        album = new TreeSet<>();
        this.title = title;

        if(names.size() != duration.size())
            throw new IllegalArgumentException("names and duration mismatched");

        for (int idx = 0; idx < names.size(); idx++) {
            album.add(new Track(names.get(idx), duration.get(idx)));
        }
    }

    public Duration totalDuration(){
        Duration res = Duration.ZERO;
        for (Track track : album) {
            res = res.sum(track.duration);            
        }
        return res;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Album Title: ");
        sb.append(title);
        sb.append("\n");
        int index = 1;
        for (Track track : album) {
            sb.append(index++);
            sb.append(" - ");
            sb.append(track.toString());
            sb.append("\n");
        }
        sb.append("Total duration: ");
        sb.append(totalDuration());
        return sb.toString();
    }

    

}
