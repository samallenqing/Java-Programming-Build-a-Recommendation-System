
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    private String ratingFile, raterID, movieFile, genre, director;
    private int numSimilarRaters, minimalRaters,year, minMinutes, maxMinutes;
    
    public MovieRunnerSimilarRatings(){
        ratingFile = "/Users/qinqing/Desktop/Java/StepFour/data/ratings.csv";
        movieFile = "/Users/qinqing/Desktop/Java/StepFour/data/ratedmoviesfull.csv";
        numSimilarRaters = 10;
        minimalRaters = 2;
        raterID = "314";
        genre = "Drama";
        minMinutes = 70;
        maxMinutes = 200;
        year = 1975;
        director ="Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
    }

    public void printAverageRatings() {
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize(ratingFile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
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
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings tr = new FourthRatings();
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
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
    
    public void printSimilarRatings(){
        FourthRatings tr = new FourthRatings();
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("-------------------------------------------");
        FourthRatings fr = new FourthRatings();
	ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, 10, 2);
	System.out.println("<h1>Recommended Movies for you</h1>");
	System.out.println("<style> table,{ border: 1px solid black; border-collapse: collapse; margin: 0 auto; } TD { border: 1px solid black; padding: 5px; vertical-align: middle; } TH { border: 1px solid black; padding: 5px;} TR:nth-child(even) {  background-color: #f2f2f2} INPUT[type=submit] { padding:5px; font-size: large; font-weight: bold; display:block;  margin: 0 auto;}</style>");
    System.out.println("<table>");
	System.out.println("<tr>");
	System.out.println("<th>Title</th>");
	System.out.println("<th>Directors</th>");
	System.out.println("<th>Year</th>");
	System.out.println("<th>Runtime(mins)</th>");
	System.out.println("</tr>");
	for(int i = 0; i < 15; i++) {
		String item = ratings.get(i).getItem();
		System.out.println("<tr>");
		System.out.println("<td>" + MovieDatabase.getTitle(item) + "</td>");
		System.out.println("<td>" + MovieDatabase.getGenres(item) + "</td>");
		System.out.println("<td>" + MovieDatabase.getYear(item) + "</td>");
		System.out.println("<td>" + MovieDatabase.getMinutes(item) + "</td>");
		System.out.println("</tr>");
	}
	System.out.println("</table>");

       }
       
    public void printSimilarRatingsByGenre(){
        FourthRatings tr = new FourthRatings();
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter(genre));
        ArrayList<Rating> rate = tr.getSimilarRatingsByFilter(raterID, numSimilarRaters,
                                                                 minimalRaters, f);
        for(Rating r : rate){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "  "+r.getValue());
           }                                                          
    }
       
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings tr = new FourthRatings();
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter(genre));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> rate = tr.getSimilarRatingsByFilter(raterID, numSimilarRaters,
                                                                 minimalRaters, f);
        for(Rating r : rate){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "  "+r.getValue());
           } 
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings tr = new FourthRatings();
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> rate = tr.getSimilarRatingsByFilter(raterID, numSimilarRaters,
                                                                 minimalRaters, f);
        for(Rating r : rate){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + "  "+r.getValue());
           } 
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings tr = new FourthRatings();
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters f = new AllFilters();
        f.addFilter(new DirectorsFilter(director));
        ArrayList<Rating> rate = tr.getSimilarRatingsByFilter(raterID, numSimilarRaters,
                                                                 minimalRaters, f);
        for(Rating r : rate){
            System.out.println(MovieDatabase.getGenres(r.getItem()) + "  "+MovieDatabase.getDirector(r.getItem()));
           }
    }
}
