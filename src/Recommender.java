import java.util.*;


/**
 * Implement this interface to allow your code to be integrated with our
 * web site.
 * 
 * When users first visit the recommender website, our code will call the
 * method <code>getItemsToRate()</code> to get a list of movies to display
 * on the web page for users to rate.
 * 
 * When a user submits their ratings, our code will call the method <code>
 * printRecommendationsFor</code> to get your recommendations based on the
 * user's ratings.  The ID given to this method is for a new Rater that we 
 * have already added to the RaterDatabase with ratings for the movies 
 * returned by the first method.  Whatever is printed from that method will 
 * be displayed on the web page: HTML, plain text, or debugging information.
 * 
 */
public interface Recommender {
    public ArrayList<String> getItemsToRate ();
    public void printRecommendationsFor (String webRaterID);
}