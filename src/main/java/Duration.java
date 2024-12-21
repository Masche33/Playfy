package main.java;

import java.util.Objects;

/**
 * A {@code Duration} an <i>immutable</i> class that is the representation of a time duration.
 * 
 * <p>A Duration cannot be 00:00
 * 
 * <p>The {@code Duration} class can:
 * <ul>
 *      <li> Give it's duration in seconds.
 *      <li> Give it's duration in a {@code String} formatted {@code 'hh:mm:ss'} or {@code 'mm:ss'} if the {@code 'hh'} is '00'.
 *      <li> Create a {@code Duration} given a {@code String} formatted like {@code 'hh:mm:ss'} or {@code 'mm:ss'} or {@code 'ss'}.
 *      <li> Sum two {@code Duration}
 *      <li> Subctract two {@code Duration} given that the result is greater or equal to 1 second. 
 * </ul>
 */
public class Duration {
    
    private final int duration;

    /**
     * Creates a {@code Duration} given a stricktly positive integer number.
     * 
     * @param duration The duration.
     * @throws IllegalArgumentException Iff {@code duration} is <= 0.
     */
    public Duration(int duration){
        if(duration <= 0) throw new IllegalArgumentException("Duration.Duration: duration cannot be negative or 0");
        this.duration = duration;
    }

    /**
     * Getter of {@code this.duration}
     * @return {@code this.duration}.
     */
    public int duration(){
        return duration;
    }

    @Override
    public String toString() {
        StringBuilder stringDuration = new StringBuilder("");
        //Getting the hh
        int remainingTime = duration;
        if(remainingTime/3600 != 0){
            stringDuration.append(remainingTime/3600);
            stringDuration.append(":");
        }
        //Getting the mm
        remainingTime = remainingTime%3600;
        if(remainingTime/60 == 0)
            stringDuration.append("00:");
        else{
            if(remainingTime/60/10 == 0)
                stringDuration.append("0");
            stringDuration.append(remainingTime/60);
            stringDuration.append(":");
        }
        //Getting the ss
        remainingTime = remainingTime%60;
        if(remainingTime/10 == 0)
                stringDuration.append("0");
        stringDuration.append(remainingTime);

        return  stringDuration.toString();
    }

    /**
     * Returns the {@code Duration} that is the sum of duration of {@code this} and {@code otherDuration}.
     * @param otherDuration A non-{@code null} {@code Duration}.
     * @return a new {@code Duration} given by the sum of {@code this} and {@code otherDuration}.
     * @throws NullPointerException Iff {@code otherDuration} is {@code null}.
     */
    public Duration sum(final Duration otherDuration){
        Objects.requireNonNull(otherDuration);
        return new Duration(this.duration+otherDuration.duration);
    }

    /**
     * Returns the {@code Duration} that is the subtraction of {@code otherDuration} from {@code this}.
     * @param otherDuration A non-{@code null} {@code Duration}.
     * @return a new {@code Duration} given by the sum of {@code this} and {@code otherDuration}.
     * @throws NullPointerException Iff {@code otherDuration} is {@code null}.
     */
    public Duration subtract(final Duration otherDuration){
        Objects.requireNonNull(otherDuration);
        return new Duration(this.duration-otherDuration.duration);
    }
}
