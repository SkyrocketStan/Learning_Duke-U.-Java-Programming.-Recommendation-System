import java.util.ArrayList;

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

  public SecondRatings() {
    // default constructor
    this("ratedmoviesfull.csv", "ratings.csv");
  }

  // The constructor should create a FirstRatings object and then call the loadMovies and
  // loadRaters methods in FirstRatings to read in all the movie and ratings data and store them
  // in the two private ArrayList variables of the SecondRatings class, myMovies and myRaters.
  public SecondRatings(String moviesFileName, String ratingsFileName) {
    FirstRatings firstRatings = new FirstRatings(moviesFileName, ratingsFileName);
    myMovies = firstRatings.getMovieArrayList();
    myRaters = firstRatings.getRaterArrayList();
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
    return this.myRaters.size();
  }
}
