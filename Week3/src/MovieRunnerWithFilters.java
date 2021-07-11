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

  private void printRatingsList(ArrayList<Rating> averageRatingList) {
    System.out.printf("Found %d movies%n", averageRatingList.size());
    averageRatingList.stream()
        .sorted()
        .forEach(
            rating -> {
              String movieID = rating.getItem();
              System.out.printf("%-4s %s%n", rating.getValue(), MovieDatabase.getTitle(movieID));
              System.out.println("     Year: " + MovieDatabase.getYear(movieID));
              System.out.println("     Time: " + MovieDatabase.getMinutes(movieID));
              System.out.println("     Genre(s): " + MovieDatabase.getGenres(movieID));
              System.out.println("     Director(s): " + MovieDatabase.getDirector(movieID));
            });
    System.out.println("-------");
  }

  /**
   * Print a list of movies and their average ratings sorted by Year and minimal number of raters
   *
   * @param minimalRatings Minimal number of ratings
   * @param year int Year of produce
   */
  public void printAverageRatingsByYear(int minimalRatings, int year) {
    printRatingsList(
        thirdRatings.getAverageRatingsByFilter(minimalRatings, new YearAfterFilter(year)));
  }

  /**
   * Print a list of movies and their average ratings sorted by Genre
   *
   * @param minimalRatings Minimal number of ratings
   * @param genre Genre
   */
  public void printAverageRatingsByGenre(int minimalRatings, String genre) {
    printRatingsList(
        thirdRatings.getAverageRatingsByFilter(minimalRatings, new GenreFilter(genre)));
  }

  /**
   * Print a list of movies and their average ratings sorted by time
   *
   * @param minimalRatings Minimal number of ratings
   * @param minMinutes Minimal length of movies in minutes
   * @param maxMinutes Maximum length of movies in minutes
   */
  public void printAverageRatingsByMinutes(int minimalRatings, int minMinutes, int maxMinutes) {
    printRatingsList(
        thirdRatings.getAverageRatingsByFilter(
            minimalRatings, new MinutesFilter(minMinutes, maxMinutes)));
  }

  /**
   * Print a list of movies and their average ratings sorted by time
   *
   * @param minimalRatings Minimal number of ratings
   * @param directors directors of the movies
   */
  public void printAverageRatingsByDirectors(int minimalRatings, String directors) {
    printRatingsList(
        thirdRatings.getAverageRatingsByFilter(minimalRatings, new DirectorsFilter(directors)));
  }
}
