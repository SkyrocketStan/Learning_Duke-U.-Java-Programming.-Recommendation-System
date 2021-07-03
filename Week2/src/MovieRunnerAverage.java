import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

  public MovieRunnerAverage() {}

  public void printAverageRatings() {
    SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
    System.out.printf(
        "Total number of movies %d, total number of raters %d\n",
        secondRatings.getMovieSize(), secondRatings.getRaterSize());

    /*
     * In the MovieRunnerAverage class in the printAverageRatings method,
     * add code to print a list of movies and their average ratings,
     * for all those movies that have at least a specified number of ratings,
     * sorted by averages.
     * Specifically, this method should print the list of movies,
     * one movie per line (print its rating first, followed by its title)
     * in sorted order by ratings, lowest rating to highest rating.
     * For example, if printAverageRatings is called on the files
     * ratings_short.csv and ratedmovies_short.csv with the argument 3,
     * then the output will display two movies:
     *
     * 8.25 Her
     * 9.0 The Godfather
     * */
    ArrayList<Rating> ratedList = secondRatings.getAverageRatings(3);
    Collections.sort(ratedList);
    for (Rating ratedObj : ratedList) {
      double rating = ratedObj.getValue();
      String movieID = ratedObj.getItem();
      String movieTitle = secondRatings.getTitle(movieID);
      System.out.println(rating + " " + movieTitle);
    }
  }
}
