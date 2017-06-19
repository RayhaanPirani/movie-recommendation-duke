
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void  printAverageRatings() {
        SecondRatings sr = new SecondRatings();
        System.out.println("# of movies: " + sr.getMovieSize());
        System.out.println("# of raters: " + sr.getRaterSize());
        ArrayList<Rating> ar = sr.getAverageRatings(12);
        Collections.sort(ar);
        System.out.println(ar.size());
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + sr.getTitle(r.getItem()));
            break;
        }
    }
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings();
        String mov = "Vacation";
        String id = sr.getID(mov);
        System.out.println(sr.getAverageByID(id, 3) + "\t" + mov);
    }
}
