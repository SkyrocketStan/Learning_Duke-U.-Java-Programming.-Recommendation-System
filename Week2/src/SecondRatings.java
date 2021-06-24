/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class SecondRatings {
  private ArrayList<Movie> myMovies;
  private ArrayList<Rater> myRaters;

  public SecondRatings() {
    // default constructor
    this("ratedmoviesfull.csv", "ratings.csv");
  }
}
