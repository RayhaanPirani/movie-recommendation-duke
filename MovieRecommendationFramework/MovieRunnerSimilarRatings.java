import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void  printAverageRatings() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ar = fr.getAverageRatings(35);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
            break;
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        YearAfterFilter yaf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        ArrayList<Rating> ar = fr.getAverageRatingsByFilter(8, af);
        System.out.println("found " + ar.size() + " movies");
        Collections.sort(ar);
        for(Rating r : ar) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem())
                               + "\t" + MovieDatabase.getTitle(r.getItem()) + "\n\t"
                               + MovieDatabase.getGenres(r.getItem()));
                               break;
        }
    }
    
    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ratings = fr.getSimilarRatings("71", 20, 5);
        for(Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue());
            break;
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        for(Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue()
                               + "\n" + MovieDatabase.getGenres(r.getItem()));
            break;
        }
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for(Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue()
                               + "\n" + MovieDatabase.getDirector(r.getItem()));
            break;
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168", 10, 3, f);
        for(Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + MovieDatabase.getMinutes(r.getItem()) + "min\t" + r.getValue()
                               + "\n" + MovieDatabase.getGenres(r.getItem()));
            break;
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(70,200));
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314", 10, 5, f);
        for(Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + MovieDatabase.getMinutes(r.getItem()) + "min\t" + r.getValue()
                               + "\t" + MovieDatabase.getYear(r.getItem()));
            break;
        }
    }
    
    public void test() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings_test.csv");
    }
}
