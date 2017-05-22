
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int min, max;
    public MinutesFilter(int minm, int maxm){
        min = minm;
        max = maxm;
    }
    
    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
