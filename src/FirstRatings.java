
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {

    public ArrayList<Movie> loadMovies(String moviefile){
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource file = new FileResource(moviefile);
        CSVParser cp = file.getCSVParser();
        for(CSVRecord rec : cp){
           Movie m = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"), 
           rec.get("director"),rec.get("country"), rec.get("poster"), 
           Integer.parseInt(rec.get("minutes")));
           list.add(m);
        }
        return list;
    }
    
    public ArrayList<Rater> loadRaters(String ratingsfile){
        ArrayList<Rater> list = new ArrayList<Rater>();
        FileResource file = new FileResource(ratingsfile);
        CSVParser cp = file.getCSVParser();
        HashMap<String, Rater> hm = new HashMap<String, Rater>();
        for(CSVRecord rec: cp){
           String key = rec.get("rater_id");
            if(!hm.containsKey(key)){
            Rater r = new EfficientRater(rec.get("rater_id"));
            r.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
            hm.put(key, r);
           }
           else{
               hm.get(key).addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
           }
        }
        for(Rater r : hm.values()){
            list.add(r);
        }
        return list;
    }
    
//     public void testLoadRaters(){
//        String file = "data/ratings.csv";
//        // String file = "data/ratings_short.csv";
//         ArrayList<Rater> list = loadRaters(file);
//         System.out.println("The number of raters in the file is: " + list.size());
//         System.out.println("--------------------------------------------------------------");
//         String raterID = "193", maxRater = "", movieID = "1798709";
//         int max = 0, movieCount = 0;
//         ArrayList<String> totalMovies = new ArrayList<String>();
//         ArrayList<String> currentMovies = new ArrayList<String>();
//         /*
//         for(Rater r : list){
//             String ID = r.getID();
//             int numRating = r.numRatings();
//             System.out.println("Audience " +ID+" rated " +numRating+ " movies.");
//             ArrayList<String> total = r.getItemsRated();
//             for( String s : total){
//               double rating = r.getRating(s);
//               System.out.println("Movie number " +s+ " and rating is "+ rating);
//             }
//             System.out.println("--------------------------------------------------------------");
//         }
//         */
//         for(Rater r: list){
//          if( r.getID().equals(raterID)){
//              int numOfRatings = r.numRatings();
//              System.out.println("The Audience whose ID is "+raterID+" has "+numOfRatings+" ratings");
//          }
//          if(r.numRatings() > max){
//              max = r.numRatings();
//              maxRater = r.getID();
//             }
//          if(r.hasRating(movieID)){
//              movieCount++;
//             }
//          currentMovies = r.getItemsRated();
//          for(String s : currentMovies){
//              if( !totalMovies.contains(s)){
//                  totalMovies.add(s);
//                 }
//             }
//       }
//       System.out.println("--------------------------------------------------------------");
//       System.out.println("The Audience who has the maximum ratings has RaterID "+
//       maxRater+" has " + max+ " time ratings.");
//       System.out.println("--------------------------------------------------------------");
//       System.out.println("Movie "+ movieID+" has been rated by "+movieCount+" Raters.");
//       System.out.println("--------------------------------------------------------------");
//       System.out.println("There are total "+totalMovies.size()+
//       " different movies has been rated by those raters.");
//     }
// 
// 
// 
// 
//     
//     
//     public void testLoadMovies(){
//         String file = "data/ratedmoviesfull.csv";
//         //String file = "data/111.csv";
//         ArrayList<Movie> list = loadMovies(file);
//         System.out.println("The number of movies in the file is: " + list.size());
//         HashMap<String, Integer> hm = new HashMap<String, Integer>();
//         int countGenre = 0, countLength = 0, countDirector = 0, maxFile = 0;
//         String genres = "Comedy", name = "";
//         for(Movie m : list){
//             if(m.getGenres().contains(genres)){
//                countGenre++; 
//             }
//             if(m.getMinutes() > 150){
//               countLength++;  
//             }
//             if(m.getDirector().contains(",")){
//               countDirector++;
//               String[] ss = m.getDirector().split(",");
//               int i = 0;
//               while(i < ss.length){
//                   if(!hm.containsKey(ss[i])){
//                       hm.put(ss[i], 1);
//                     }
//                    else{
//                      int k =  hm.get(ss[i]) + 1;
//                      hm.put(ss[i], k);
//                     }
//                     i++;
//                 }
//             }
//             else{
//                 if(! hm.containsKey(m.getDirector())){
//                     hm.put(m.getDirector(), 1);
//                 }
//                 else{
//                     int k = hm.get(m.getDirector()) +1;
//                     hm.put(m.getDirector(), k);
//                 }
//             }
//         }
//         int oneFile = list.size()-countDirector;
//         System.out.println("There are "+countGenre+" movies are "+genres);
//         System.out.println("There are "+countLength+" movies are longer than 150 mins.");
//         System.out.println("There are "+oneFile+" movies are directed only by one director.");
//         for(String s : hm.keySet()){
//             int count = hm.get(s);
//             if(count > maxFile){
//                 maxFile = count;
//                 name = s;
//             }
//         }
//         System.out.println("--------------------------------------------------------------");
//         System.out.println("The director who directed the most files are " + name +
//         " who has directed "+maxFile+" files.");
//     }
}
    