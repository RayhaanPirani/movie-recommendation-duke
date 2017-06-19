
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters("data\\" + ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id : movies) {
            double avgr =  getAverageByID(id,minimalRaters);
            if(avgr != 0.0) {
                Rating r = new Rating(id,avgr);
                ar.add(r);
            }
        }
        return ar;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ar = new ArrayList<Rating>();
        for(String id : movies) {
            double avgr =  getAverageByID(id,minimalRaters);
            if(avgr != 0.0) {
                Rating r = new Rating(id,avgr);
                ar.add(r);
            }
        }
        return ar;
    }
}
