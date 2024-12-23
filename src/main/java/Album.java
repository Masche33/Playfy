package main.java;

import java.util.Iterator;
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
public class Album{
 
    /**
     * A class to represent a single {@code Track}.
     * 
     * It has a {@code title}, a{@code duration} and an {@code Album}.
     */
    public class Track implements Comparable<Track>{

        /**
         * AF:
         *      (title, duration) = Is a tuple that represents the Title of the Track and the Duration of the Track
         * --------------
         * RI:
         *      title != null
         *      duration != null
         *      title != ""
         *      title is not only full of spaces
         */


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

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Track)) return false;
            return ((Track)obj).title.equals(title);
        }

        @Override
        public int hashCode() {
            return title.hashCode();
        }

        
    }

    /**
     * AF:
     *  (title,  album) is the tuple that represents the title and the ordered set of the tracks 
     * -
     * RI: 
     *      title != null
     *      title != ""
     *      title is not only full of spaces
     *      album != null
     *      album is not empty
     *      album is ordered in alphabetical order
     */
    
    /**Sorted list of the {@code Album}'s {@code Tracks} in Alphabetical Order, */
    SortedSet<Track> album;

    /**{@code Album}'s title' */
    private final String title;

    /**
     * Creates an {@code Album}.
     * @param title a non {@code empty}, non {@code null} {@code String}
     * @param names a non empty {@code List} of names parallel to {@code duration} of valid {@code String} for {@code Track.title}. 
     * @param duration a non empty {@code List} of names parallel to {@code names} of valid {@code Duration}.
     * @throws IllegalArgumentException If {@code title} is {@code empty} or {@code blank}. If {@code names} and {@code duration} are not aligned
     * @throws NullPointerException Iff {@code title} or {@code names} or {@code duration} are equal to {@code null}.
     */
    public Album(final String title, final List<String> names, final List<Duration> duration){
        Objects.requireNonNull(title);
        Objects.requireNonNull(names);
        Objects.requireNonNull(duration);
        if(title.isBlank() || title.isEmpty())
            throw new IllegalArgumentException("Bad title");

        if(names.size() != duration.size() )
            throw new IllegalArgumentException("names and duration mismatched");
        
        album = new TreeSet<>();
        this.title = title;
        
        for (int idx = 0; idx < names.size(); idx++) {
            album.add(new Track(names.get(idx), duration.get(idx)));
        }
    }

    public String title(){
        return title;
    }

    /**
     * Returns the {@code Duration} of the {@code Album}, given by the sum of all the {@code Tracks} {@code Durations} in {@code this.album}.
     * @return The sum of the {@code Duration} of all the {@code Tracks} in {@code album}.
     */
    public Duration totalDuration(){
        Duration res = Duration.ZERO;
        for (Track track : album) {
            res = res.sum(track.duration);            
        }
        return res;
    }

    /**
     * Returns the {@code Track} in the album given the title if it is in it.
     * @param title A non empyt, non null {@code String}
     * @return The {@code Track} with the same title of {@code title}
     */
    public Track getTrackByTitle(final String title){
        for (Track track : album) {
            int res = track.title.compareTo(title);
            if(res >= 1)            
                return null;
            if(res == 0)
                return track;
        }
        return null;

    }

    public Track getTrackByPosition(final int position){
        if(position <= 0 || position > album.size())
            throw new IndexOutOfBoundsException(position+ " is not a valid index for the album");
        int counter = 0;
        for (Track track : album) 
            if(counter++ == position-1)
                return track;
        //This istruction is cannot be reached, if the position does not generate an exception the track will be found
        return null;
    }

    public int getIndexByTrack(final String otherTrack){
        Objects.requireNonNull(otherTrack);
        int counter = 1;
        for (Track track : album) {
            if(track.title.equals(otherTrack))
                return counter;
            counter++;
        }
        return -1;
    }

    /**
     * Returns the {@code iterator}
     * @return {@code this.album.iterator()}
     */
    public Iterator<Track> iterator(){
        return album.iterator();
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