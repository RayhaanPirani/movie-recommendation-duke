
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String myDirectors;
    
    public DirectorsFilter(String directors) {
		myDirectors = directors;
	}
	
	@Override
	public boolean satisfies(String id) {
	    String directorArray[] = myDirectors.split(",");
	    for(String director : directorArray) {
	        if(MovieDatabase.getDirector(id).contains(director)) return true;
	    }
	    return false;
	}
}
