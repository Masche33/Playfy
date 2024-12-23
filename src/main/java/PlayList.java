package main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import main.java.Album.Track;

/**
 * A {@code Playlist} is a titled list of {@code Tracks} that can belong to differents {@code Albums}.
 * 
 * 
 * <p>It is a mutable class and can:
 * 
 * <ul>
 *      <li>Get the {@code Duration} of all the {@code Tracks} in the list.
 *      <li>Add a {@code Track}.
 *      <li>Remove a {@code Track}.
 *      <li>Get a {@code Track} by title or its position.
 *      <li>Print the name, all the tracks with relative {@code Albums}.
 * </ul>
 */
public class PlayList {
    
    /** The {@code title} of the {@code Playlist}. */
    private final String title;


    /** The list of {@code Tracks} */
    private ArrayList<Track> playlist;

    /**
     * Creates an {@code empty} Playlist with a {@code name}.
     * @param title A non-{@code null}, non empty {@code String}. 
     * @throws NullPointerException iff {@code title} is {@code null}.
     * @throws IllegalArgumentException iff {@code title} is empty of full of blanks.
     */
    public PlayList(String title){
        Objects.requireNonNull(title);
        if(title.isBlank() || title.isEmpty())
            throw new IllegalArgumentException("title cannot be empty all with all spaces");
        this.title=title;
        playlist = new ArrayList<>();
    }

    /**
     * Getter of {@code this.title}.
     * @return {@code this.title}.
     */
    public String title(){
        return title;
    }

    /**
     * Returns the sum of the {@code Duration} of all the Tracks.
     * @return a {@code Duration} resulting from the sum of all the {@code Tracks} durations sum.
     */
    public Duration totalDuration(){
        Duration total = Duration.ZERO;
        for (Track track : playlist) {
            total = total.sum(track.duration());
        }
        return total;
    }

    /**
     * Adds a {@code track} to the playlist if it is not {@code null} or already in it.
     * @param newTrack A track cannot be {@code null}.
     * @throws NullPointerException iff {@code newTrack} is {@code null}.
     * @throws IllegalArgumetException iff {@code newTrack} is already in it.
     */
    public void add(final Track newTrack){
        Objects.requireNonNull(newTrack);
        if(playlist.contains(newTrack))
            throw new IllegalArgumentException("Track already in it");
        playlist.add(newTrack);
    }

    /**
     * Remove a {@code track} to the playlist if it is not {@code null} and it is in it.
     * @param track A track cannot be {@code null}.
     * @throws NullPointerException iff {@code track} is {@code null}.
     * @throws IllegalArgumetException iff {@code track} is already in it.
     */
    public void remove(final Track track){
        Objects.requireNonNull(track);
        playlist.remove(track);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Playlist name:");
        sb.append(title);
        sb.append("\n");
        int counter = 1;
        for (Track track : playlist) {
            sb.append(counter++);
            sb.append(" - ");
            sb.append(track);
            sb.append(", (");
            sb.append(track.album().title());
            sb.append(")\n");
        }
        sb.append("Total duration: ");
        sb.append(totalDuration());
        return sb.toString();
    }

    public Iterator<Track> iterator(final String albumTitle) {
        return new Iterator<Album.Track>() {
            final Object[] values = playlist.toArray();
            int index = 0;
            @Override
            public boolean hasNext() {
                while (index<values.length && !(((Track)values[index]).album().title().equals(albumTitle))) {
                    index++;
                }
                return (index < values.length);
            }

            @Override
            public Track next() {
                return (Track)values[index++];
            }
        };
    }

}
