
/**
 * Write a description of tttttt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    private String ratingFile, genre, movieFile, directors;
    private int minimalRaters, year, minMinutes, maxMinutes;
    
    public MovieRunnerWithFilters(){
        ratingFile = "/Users/qinqing/Desktop/Java/StepThree/data/ratings.csv";
        movieFile = "/Users/qinqing/Desktop/Java/StepThree/data/ratedmoviesfull.csv";
        minimalRaters = 3;
        year = 1990;
        genre = "Drama";
        minMinutes = 90;
        maxMinutes = 180;
        directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
    }
    
	public void printAverageRatings() {
		ThirdRatings tr = new ThirdRatings(ratingFile);
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    MovieDatabase.initialize(movieFile);
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
	    System.out.println("found " + ratings.size() + " movies");
	    System.out.println("-------------------------------------------");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
	    }
	}
	
	public void printAverageRatingsByYear(){
	    ThirdRatings tr = new ThirdRatings(ratingFile);
	    MovieDatabase.initialize(movieFile);
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    Filter filterCriteria = new YearAfterFilter(year);
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    System.out.println("found " + ratings.size() + " movies");
	    System.out.println("-------------------------------------------");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(MovieDatabase.getTitle(r.getItem()) +" Rating: "+r.getValue() +"\n"
	    	+MovieDatabase.getYear(r.getItem()));
	    	System.out.println("-------------------------------------------");
	    }	
	}
	
	public void printAverageRatingsByGenre() {
		ThirdRatings tr = new ThirdRatings(ratingFile);
	    MovieDatabase.initialize(movieFile);
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    Filter filterCriteria = new GenreFilter(genre);
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
	    System.out.println("found " + ratings.size() + " movies");
	    System.out.println("-------------------------------------------");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(MovieDatabase.getTitle(r.getItem()) +" Rating: "+r.getValue() +"\n"
	    	+ MovieDatabase.getGenres(r.getItem()));
	    	System.out.println("-------------------------------------------");
	    }	
	}
	
	public void printAverageRatingsByMinutes(){
	    ThirdRatings tr = new ThirdRatings(ratingFile);
	    MovieDatabase.initialize(movieFile);
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    Filter f = new MinutesFilter(minMinutes, maxMinutes);
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, f);
	    System.out.println("found " + ratings.size() + " movies");
	    System.out.println("-------------------------------------------");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(MovieDatabase.getTitle(r.getItem()) +" Rating: "+r.getValue() +"\n"
	    	+"Length: " + MovieDatabase.getMinutes(r.getItem()));
	    	System.out.println("-------------------------------------------");
	    }	
	}
	
	public void printAverageRatingsByDirectors(){
	    ThirdRatings tr = new ThirdRatings(ratingFile);
	    MovieDatabase.initialize(movieFile);
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    Filter f = new DirectorsFilter(directors);
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, f);
	    System.out.println("found " + ratings.size() + " movies");
	    System.out.println("-------------------------------------------");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(MovieDatabase.getTitle(r.getItem()) +" Rating: "+r.getValue() +"\n"
	    	+"Director(s): " + MovieDatabase.getDirector(r.getItem()));
	    	System.out.println("-------------------------------------------");
	    }	
	}
	
	public void printAverageRatingsByYearAfterAndGenre(){
	    ThirdRatings tr = new ThirdRatings(ratingFile);
	    MovieDatabase.initialize(movieFile);
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    AllFilters f = new AllFilters();
	    f.addFilter(new GenreFilter(genre));
	    f.addFilter(new YearAfterFilter(year));
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, f);
	    System.out.println("found " + ratings.size() + " movies");
	    System.out.println("-------------------------------------------");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(MovieDatabase.getTitle(r.getItem()) +" Rating: "+r.getValue() +"\n"
	    	+MovieDatabase.getGenres(r.getItem()));
	    	System.out.println("-------------------------------------------");
	    }	
	}
	
	public void printAverageRatingsByDirectorsAndMinutes (){
	    ThirdRatings tr = new ThirdRatings(ratingFile);
	    MovieDatabase.initialize(movieFile);
	    System.out.println("read data for " + tr.getRaterSize() + " raters");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    AllFilters f = new AllFilters();
	    f.addFilter(new DirectorsFilter(directors));
	    f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
	    ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, f);
	    System.out.println("found " + ratings.size() + " movies");
	    System.out.println("-------------------------------------------");
	    Collections.sort(ratings);
	    for(Rating r: ratings) {
	    	System.out.println(MovieDatabase.getTitle(r.getItem()) +" Rating: "+r.getValue() +"\n"
	    	+"Length: " + MovieDatabase.getMinutes(r.getItem())+
	    	" Director(s): " + MovieDatabase.getDirector(r.getItem()));
	    	System.out.println("-------------------------------------------");
	    }	
	}
}