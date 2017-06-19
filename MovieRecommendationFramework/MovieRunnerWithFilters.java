
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void  printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ar = tr.getAverageRatings(35);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
            break;
        }
    }
    public void printAverageRatingsByYear() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        YearAfterFilter yaf = new YearAfterFilter(2000);
        ArrayList<Rating> ar = tr.getAverageRatingsByFilter(20, yaf);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem())
                               + "\t" + MovieDatabase.getTitle(r.getItem()));
                               break;
        }
    }
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        GenreFilter gf = new GenreFilter("Comedy");
        ArrayList<Rating> ar = tr.getAverageRatingsByFilter(20, gf);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem())
                               + "\n\t" + MovieDatabase.getGenres(r.getItem()));
                               break;
        }
    }
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        MinutesFilter mf = new MinutesFilter(105, 135);
        ArrayList<Rating> ar = tr.getAverageRatingsByFilter(5, mf);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getMinutes(r.getItem()) + " min"
                               + "\t" + MovieDatabase.getTitle(r.getItem()));
                               break;
        }
    }
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ar = tr.getAverageRatingsByFilter(4, df);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem())
                               + "\n\t" + MovieDatabase.getDirector(r.getItem()));
                               break;
        }
    }
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        YearAfterFilter yaf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        ArrayList<Rating> ar = tr.getAverageRatingsByFilter(8, af);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem())
                               + "\t" + MovieDatabase.getTitle(r.getItem()) + "\n\t"
                               + MovieDatabase.getGenres(r.getItem()));
                               break;
        }
    }
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinutesFilter mf = new MinutesFilter(90, 180);
        AllFilters af = new AllFilters();
        af.addFilter(df);
        af.addFilter(mf);
        ArrayList<Rating> ar = tr.getAverageRatingsByFilter(3, af);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getMinutes(r.getItem()) + " min"
                               + "\t" + MovieDatabase.getTitle(r.getItem())
                               + "\n\t" + MovieDatabase.getDirector(r.getItem()));
                               break;
        }
    }
}
