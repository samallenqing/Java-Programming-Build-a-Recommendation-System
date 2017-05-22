
/**
 * Write a description of fffff here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings_short.csv");
        //this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public double getAverageByID(String movieID, int minimalRaters){
        double count = 0, scores = 0;
        for(Rater r : myRaters){
            if(r.hasRating(movieID)){
                count++;
                scores += r.getRating(movieID);
            }
        }
        if(count >= minimalRaters){
            return scores/count;
        }
        return 0;
    }
    
    public ArrayList getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(String s: movies){
            double rate = getAverageByID(s, minimalRaters);
            if(rate != 0){
                Rating r = new Rating(s, rate);
                list.add(r);
            }
        }
        return list;
    }
    
     public int getRaterSize() {
    	return myRaters.size();
    }
    
    public ArrayList getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String id : movies){
           double averageRating = getAverageByID(id,minimalRaters);
    	   if(averageRating!=0){
    			Rating r = new Rating(id, averageRating);
    			list.add(r);
    		}
    	}
    	return list; 
    }
}
