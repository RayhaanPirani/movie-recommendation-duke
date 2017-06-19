
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile,String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies("data\\" + moviefile);
        myRaters = fr.loadRaters("data\\" + ratingsfile);
    }
    public int getMovieSize() {
        return myMovies.size();
    }
    public int getRaterSize() {
        return myRaters.size();
    }
    public double getAverageByID(String id, int minimalRaters) {
        int n = 0;
        double rt = 0.0;
        for(Rater r : myRaters) {
            if(r.getItemsRated().contains(id)) {
            n++;
            rt += r.getRating(id);
            }   
        }
        if(n >= minimalRaters) {
            return (double)rt/n;
        }
        return 0.0;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ar = new ArrayList<Rating>();
        for(Movie m : myMovies) {
            String id = m.getID();
            double avgr =  getAverageByID(id,minimalRaters);
            if(avgr != 0.0) {
                Rating r = new Rating(id,avgr);
                ar.add(r);
            }
        }
        return ar;
    }
    public String getTitle(String id) {
        for(Movie m : myMovies) {
            if(m.getID().equals(id)) return m.getTitle();
        }
        return "N/A";
    }
    public String getID(String title) {
        for(Movie m : myMovies) {
            if(m.getTitle().equals(title)) return m.getID();
        }
        return "NO SUCH TITLE.";
    }
}
