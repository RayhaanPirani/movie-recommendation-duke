
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.* ;

public class FirstRatings {
    ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> mlist = new ArrayList<Movie>();
        FileResource file = new FileResource(filename);
        CSVParser parser = file.getCSVParser();
        for(CSVRecord rec : parser) {
            Movie m = new Movie(rec.get("id"),rec.get("title"),rec.get("year"),rec.get("genre"),
                                rec.get("director"),rec.get("country"),rec.get("poster"),
                                Integer.parseInt(rec.get("minutes")));
            mlist.add(m);
        }
        return mlist;
    }
    
    public void testLoadMovies() {
        //ArrayList<Movie> mlist = loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> mlist = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("# movies: " + mlist.size());
        int comedyN = 0;
        int greaterThan150 = 0;
        HashMap<String,Integer> dirmov = new HashMap<String,Integer>();
        for(Movie m : mlist) {
            //System.out.println(m);
            if(m.getGenres().contains("Comedy")) comedyN++;
            if(m.getMinutes() > 150) greaterThan150++;
            String dir = m.getDirector();
            if(! dirmov.containsKey(dir) ) {
                dirmov.put(dir,1);
            }
            else {
                int n = dirmov.get(dir);
                dirmov.put(dir,n+1);
            }
        }
        System.out.println("# Comedy: " + comedyN);
        System.out.println("# >150 min. " + greaterThan150);
        int maxmov = 0;
        for(String dir : dirmov.keySet()) {
            if( dirmov.get(dir) > maxmov ) maxmov = dirmov.get(dir);
        }
        System.out.println("Maximum movies of a single director: " + maxmov);
        int maxmovdirc = 0;
        for(String dir : dirmov.keySet()) {
            if( dirmov.get(dir) == maxmov ) maxmovdirc++;
        }
        System.out.println("Directors having max. movies are " + maxmovdirc + " and are:");
        for(String dir : dirmov.keySet()) {
            if( dirmov.get(dir) == maxmov ) System.out.println(dir);
        }
    }
    
    ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> rlist = new ArrayList<Rater>();
        FileResource file = new FileResource(filename);
        CSVParser parser = file.getCSVParser();
        for(CSVRecord rec : parser) {
            String rid = rec.get("rater_id");
            boolean set = false;
            for(int i=0; i < rlist.size(); i++) {
                if(rlist.get(i).getID().equals(rid)) {
                    Rater r = rlist.get(i);
                    r.addRating(rec.get("movie_id"),
                                Double.parseDouble(rec.get("rating")));
                    rlist.set(i,r);
                    set = true;
                    break;
                }
            }
            if(!set) {
                Rater r = new EfficientRater(rid);
                r.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                rlist.add(r);
            }
        }
        return rlist;
    }
    
    public void testLoadRaters() {
        //ArrayList<Rater> rlist = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> rlist = loadRaters("data/ratings.csv");
        System.out.println("# raters: " + rlist.size());
        int maxrat = 0;
        for(Rater r : rlist) {
            //System.out.println(r.getID() + "\t" + r.getItemsRated().size() );
            //ArrayList<String> rt = r.getItemsRated();
            //for(int i = 0; i < rt.size(); i++) {
            //    System.out.println(rt.get(i) + "\t" + r.getRating(rt.get(i)));
            // }
            int id = 193;
            if(r.getID().equals("" + id)) 
                System.out.println("# of ratings by ID " + id + " is " + r.getItemsRated().size());
            if(r.getItemsRated().size() > maxrat) maxrat = r.getItemsRated().size();
        }
        System.out.println("Max # ratings: " + maxrat);
        int maxratc = 0;
        for(Rater r : rlist) {
            if(r.getItemsRated().size() == maxrat) {
                maxratc++;
                System.out.println("Raters who rated max. movies: " + r.getID());
            }
        }
        System.out.println("# having maximum ratings: " + maxratc);
        String movID = "1798709";
        int nmovr = 0;
        for(Rater r : rlist) {
            if(r.getItemsRated().contains(movID)) nmovr++;
        }
        System.out.println("# ratings for " + movID + " is: " + nmovr);
        ArrayList<String> nmovies = new ArrayList<String>();
        for(Rater r : rlist) {
            for(int i = 0; i < r.getItemsRated().size(); i++)
                if(! nmovies.contains(r.getItemsRated().get(i)) ) 
                    nmovies.add(r.getItemsRated().get(i));
        }
        System.out.println("Diff. movies rated by all raters: " + nmovies.size());
    }
}
