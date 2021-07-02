import java.util.ArrayList;
import java.util.HashMap;

/**
 * Week2. A new class to do many of the calculations focusing on computing averages on movie
 * ratings.
 *
 * @author Stanislav Rakitov
 * @version 1.0
 */
public class SecondRatings {
  private final ArrayList<Movie> myMovies;
  private final ArrayList<Rater> myRaters;
  private final FirstRatings firstRatings;
  private final HashMap<String, ArrayList<Double>> moviesAndRatings;

  public SecondRatings() {
    // default constructor
    this("ratedmoviesfull.csv", "ratings.csv");
  }

  // The constructor should create a FirstRatings object and then call the loadMovies and
  // loadRaters methods in FirstRatings to read in all the movie and ratings data and store them
  // in the two private ArrayList variables of the SecondRatings class, myMovies and myRaters.
  public SecondRatings(String moviesFileName, String ratingsFileName) {
    firstRatings = new FirstRatings(moviesFileName, ratingsFileName);
    myMovies = firstRatings.getMovieArrayList();
    myRaters = firstRatings.getRaterArrayList();
    moviesAndRatings = getMoviesAndRatingsMap();
  }

  // Returns a Map with movieID and all its ratings
  private HashMap<String, ArrayList<Double>> getMoviesAndRatingsMap() {
    HashMap<String, ArrayList<Double>> map = new HashMap<>();

    for (Movie movie : myMovies) {
      map.putIfAbsent(movie.getID(), new ArrayList<>());
    }

    for (Rater rater : myRaters) {
      ArrayList<String> ratings = rater.getItemsRated();
      for (String movieID : ratings) {
        // String movieID, double rating
        ArrayList<Double> list = map.get(movieID);
        list.add(rater.getRating(movieID));
      }
    }

    return map;
  }

  /**
   * A public method which returns the number of movies that were read in and stored in the
   * ArrayList of type Movie
   *
   * @return int number of movies
   */
  public int getMovieSize() {
    return this.myMovies.size();
  }

  /**
   * A public method which returns the number of raters that were read in and stored in the
   * ArrayList of type Rater.
   *
   * @return int number of movies
   */
  public int getRaterSize() {
    return this.firstRatings.getRatersNumber();
  }

  /*
  This method returns a double representing the average movie rating for this ID
  if there are at least minimalRaters ratings.
  If there are not minimalRaters ratings, then it returns 0.0.
  */
  private Double getAverageByID(String id, Integer minimalRaters) {
    double result = 0.0;
    ArrayList<Double> ratings = moviesAndRatings.get(id);
    if (ratings != null && ratings.size() >= minimalRaters) {
      result = ratings.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    // If there are no ratings returns 0.0
    return result;
  }
}
