
import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    public double getAverageByID(String id, int minimalRaters) {
        int n = 0;
        double rt = 0.0;
        for(Rater r : RaterDatabase.getRaters()) {
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
    
    private double dotProduct(Rater me, Rater r) {
        double product = 0;
        ArrayList<String> rIDs = r.getItemsRated();
        ArrayList<String> meIDs = me.getItemsRated();
        for(String meID : meIDs) {
            if(rIDs.contains(meID)) {
                product += (me.getRating(meID) - 5) * (r.getRating(meID) - 5);
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similar = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : raters) {
            String raterID = r.getID();
            if(!raterID.equals(id)) {
                double product = dotProduct(me, r);
                if(product >= 0) {
                    Rating rating = new Rating(raterID, product);
                    similar.add(rating);
                }
            }
        }
        Collections.sort(similar, Collections.reverseOrder());
        return similar;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String mID : movies) {
            double rt = 0.0;
            int n = 0;
            for(int k=0; k < numSimilarRaters; k++){
                Rating r = list.get(k);
                String rID = r.getItem();
                double wt_rt = r.getValue();
                double o_rt = 0;
                try {
                    o_rt = RaterDatabase.getRater(rID).getRating(mID);
                }
                catch(NullPointerException e) {
                    continue;
                }
                rt += wt_rt * o_rt;
                n++;
            }
            if(n >= minimalRaters) ret.add(new Rating(mID, (rt/n)));
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter  (String id, int numSimilarRaters,
                                                        int minimalRaters,Filter filterCriteria) {
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String mID : movies) {
            double rt = 0.0;
            int n = 0;
            for(int k=0; k < numSimilarRaters; k++){
                Rating r = list.get(k);
                String rID = r.getItem();
                double wt_rt = r.getValue();
                double o_rt = 0;
                try {
                    o_rt = RaterDatabase.getRater(rID).getRating(mID);
                }
                catch(NullPointerException e) {
                    continue;
                }
                rt += wt_rt * o_rt;
                n++;
            }
            if(n >= minimalRaters) ret.add(new Rating(mID, (rt/n)));
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
}
