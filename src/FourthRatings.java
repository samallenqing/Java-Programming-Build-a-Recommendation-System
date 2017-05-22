
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    public double getAverageByID(String movieID, int minimalRaters){
        double count = 0, scores = 0;
        RaterDatabase rd = new RaterDatabase();
        for(Rater r : rd.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r) {
    	double dotProduct = 0;
    	ArrayList<String> myMovies = me.getItemsRated();
    	for (String id: myMovies)
		{
		    if (r.hasRating(id))
			{
				double myRating = me.getRating(id);
				double rRating = r.getRating(id);
				myRating -= 5;
				rRating -= 5;
				dotProduct += myRating * rRating;
			}
		}
		return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	for(Rater r: RaterDatabase.getRaters()) {
    		// add dot_product(r, me) to list if r!= me
    		if(!r.equals(me)) {
    			double product = dotProduct(me, r);
    			if (product > 0)
    				list.add(new Rating(r.getID(), product));
    		}
    	}
    	Collections.sort(list, Collections.reverseOrder());
    	return list;
    }
    
    public ArrayList<Rating> getRecommendations(String id, int numRaters) {
    	ArrayList<Rating> list = getSimilarities(id);
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	for(String movieID: movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
    		for(int k=0; k < numRaters; k++) {
    			Rating r = list.get(k);
    			String raterID = r.getItem();
    			double weight = r.getValue();
    			Rater myRater = RaterDatabase.getRater(raterID);
    			if(myRater.hasRating(movieID)) {
    				countRaters++;
    				sum += weight * myRater.getRating(movieID);
    			}
    		}
    		weightedAverage = sum / countRaters;
    		res.add(new Rating(movieID, weightedAverage));
    	}
		Collections.sort(res, Collections.reverseOrder());
		return res;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());	
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;		
    }
    
    public ArrayList getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                        Filter filterCriteria){
        ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;	
    }                                     
}
