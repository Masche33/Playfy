package main.java;

import java.util.Objects;

/**
 * A {@code Duration} an <i>immutable</i> class that is the representation of a time duration.
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
    
    static final public Duration ZERO = new Duration(0);

    private final int duration;

    /**
     * Creates a {@code Duration} given a stricktly positive integer number.
     * 
     * @param duration The duration.
     * @throws IllegalArgumentException Iff {@code duration} is <= 0.
     */
    public Duration(int duration){
        if(duration < 0) throw new IllegalArgumentException("Duration.Duration: duration cannot be negative or 0");
        this.duration = duration;
    }

    /**
     * Given a {@code String} formatted 'hhh...h:mm:ss' it creates the relative {@code Duration}.
     * <p> The number represented byte the h can be an arbitrary positive number
     * but 'mm' and 'ss' cannot be greater then 60. 
     * 
     * @param formattedString A not {@code null} and not {@code empty} String formatted like 'hhhh...h:mm:ss'.
     * @throws IllegalArgumentException If {@code formattedString} is not in the format 'hh...hh:mm:ss' or if 'hh...hh', 'mm' or 'ss' are not representing numbers.
     * @throws NullPointerException Iff {@code formattedString} is {@code null}.
     */
    public Duration(final String formattedString){
        Objects.requireNonNull(formattedString);
        if(formattedString.isBlank() || formattedString.isEmpty())
            throw new IllegalArgumentException("String cannot be empty or full of withe spaces");
        String[] values = formattedString.split(":");
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if(values.length > 3) throw new IllegalArgumentException("String badly formatted");
        try{
            seconds = Integer.parseInt(values[values.length-1]);
            if(values.length >= 2) minutes = Integer.parseInt(values[values.length-2]);
            if(values.length == 3) hours = Integer.parseInt(values[values.length-3]);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("String cannot have chars in it, except ':', for a maximum of 2");
        }
        if(minutes>60) throw new IllegalArgumentException("String badly formatted");
        if(seconds>60) throw new IllegalArgumentException("String badly formatted");
        duration = hours*3600+minutes*60+seconds;
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
