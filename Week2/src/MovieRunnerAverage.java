import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
  private final SecondRatings secondRatings;

  public MovieRunnerAverage() {
    secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
  }

  public void printAverageRatings(int minimalRatings) {
    //    SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv",
    // "ratings_short.csv");
    //    System.out.printf(
    //        "Total number of movies %d, total number of raters %d\n",
    //        secondRatings.getMovieSize(), secondRatings.getRaterSize());

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
    ArrayList<Rating> ratedList = secondRatings.getAverageRatings(minimalRatings);
    Collections.sort(ratedList);
    System.out.printf("Total movies with %d ratings is %d\n", minimalRatings, ratedList.size());
    System.out.printf(
        "The name of the movie that has the lowest rating is \"%s\"\n",
        secondRatings.getTitle(ratedList.get(0).getItem()));
    //    for (Rating ratedObj : ratedList) {
    //      double rating = ratedObj.getValue();
    //      String movieID = ratedObj.getItem();
    //      String movieTitle = secondRatings.getTitle(movieID);
    //      System.out.println(rating + " " + movieTitle);
    //    }
  }

  Double getAverageRatingOneMovie(String movieTitle) {
    String movieID = secondRatings.getID(movieTitle);
    return secondRatings.getAverageByID(movieID, 1);
  }
}
