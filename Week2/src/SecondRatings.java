import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
  private final HashMap<String, String> moviesIdAndTitles;

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
    moviesIdAndTitles = getAllMoviesAndTitlesMap();
  }

  // Get all movies and titles map
  private HashMap<String, String> getAllMoviesAndTitlesMap() {
    HashMap<String, String> map = new HashMap<>();
    for (Movie movie : myMovies) {
      String movieID = movie.getID();
      String movieTitle = movie.getTitle();
      map.put(movieID, movieTitle);
    }
    return map;
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
  Double getAverageByID(String id, Integer minimalRaters) {
    double result = 0.0;
    ArrayList<Double> ratings = moviesAndRatings.get(id);
    if (ratings != null && ratings.size() >= minimalRaters) {
      result = ratings.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    // If there are no ratings returns 0.0
    return result;
  }

  /**
   * This method should find the average rating for every movie that has been rated by at least
   * minimalRaters raters.
   *
   * @param minimalRaters int the minimal number of raters supplying a rating
   * @return an ArrayList of all the Rating objects for movies that have at least the minimal number
   *     of raters supplying a rating
   */
  public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    ArrayList<Rating> list = new ArrayList<>();
    for (Movie movie : myMovies) {
      String movieID = movie.getID();
      Double averageRating = getAverageByID(movieID, minimalRaters);
      if (averageRating != 0.0) {
        list.add(new Rating(movieID, averageRating));
      }
    }
    return list;
  }

  /*
   * This method returns the title of the movie with that ID.
   * If the movie ID does not exist, then this method should return a String
   * indicating the ID was not found.
   * */
  String getTitle(String id) {
    String title = moviesIdAndTitles.get(id);
    if (title != null) {
      return title;
    }
    // No title found
    return "ID " + id + " NOT FOUND";
  }
  /*
   * This method returns the movie ID of this movie.
   * If the title is not found, return “NO SUCH TITLE.
   * */
  String getID(String title) {
    String result = "NO SUCH TITLE";

    for (Map.Entry<String, String> entry : moviesIdAndTitles.entrySet()) {
      if (Objects.equals(title, entry.getValue())) {
        return entry.getKey();
      }
    }

    return result;
  }
}
