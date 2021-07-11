import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

  private final ThirdRatings thirdRatings;

  public MovieRunnerWithFilters(String moviesFileName, String ratingsFileName) {
    this.thirdRatings = new ThirdRatings(moviesFileName, ratingsFileName);
  }

  public MovieRunnerWithFilters() {
    this("ratedmovies_short.csv", "ratings_short.csv");
  }

  /**
   * Print a list of movies and their average ratings sorted by averages
   *
   * @param minimalRatings int specified number of ratings
   */
  public void printAverageRatings(int minimalRatings) {
    //    ThirdRatings thirdRatings = new ThirdRatings("ratedmovies_short.csv",
    // "ratings_short.csv");
    ArrayList<Rating> ratedList = thirdRatings.getAverageRatings(minimalRatings);

    Collections.sort(ratedList);
    // Print the number of raters after creating a ThirdsRating object.
    System.out.printf("Total movies with %d ratings is %d\n", minimalRatings, ratedList.size());

    // You’ll call the MovieDatabase initialize method with the moviefile to set up the movie
    // database.
    MovieDatabase.initialize("ratedmoviesfull.csv");

    // Print the number of movies in the database.
    System.out.println("The number of movies in the database is " + MovieDatabase.size());

    // You will call getAverageRatings with a minimal number of raters to return an ArrayList of
    // type Rating.

    ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(minimalRatings);

    // Print out how many movies with ratings are returned,
    // then sort them, and print out the rating and title of each movie
    System.out.printf(
        "How many movies with ratings %d are returned: %d%n",
        minimalRatings, averageRatings.size());

    printRatingsList(averageRatings);
  }

  private void printRatingsList(ArrayList<Rating> averageRatings) {
    averageRatings.stream()
        .sorted()
        .forEach(
            rating ->
                System.out.printf(
                    "%-4s %s %s%n",
                    rating.getValue(),
                    MovieDatabase.getYear(rating.getItem()),
                    MovieDatabase.getTitle(rating.getItem())));
  }

  public void printAverageRatingsByYear(int minimalRatings, int year) {
    System.out.println("number of raters " + thirdRatings.getRaterSize());
    System.out.println("number of movies " + MovieDatabase.size());
    ArrayList<Rating> aveRating =
        thirdRatings.getAverageRatingsByFilter(minimalRatings, new YearAfterFilter(year));
    System.out.printf("found %d movies%n", aveRating.size());
    printRatingsList(aveRating);
  }
}
