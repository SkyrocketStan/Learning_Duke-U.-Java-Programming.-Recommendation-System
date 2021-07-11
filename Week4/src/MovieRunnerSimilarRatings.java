import java.util.ArrayList;
import java.util.Collections;

/** @author Stanislav Rakitov */
public class MovieRunnerSimilarRatings {

  /**
   * Print a list of movies and their average ratings sorted by averages
   *
   * @param minimalRatings int specified number of ratings
   */
  public void printAverageRatings(int minimalRatings) {
    FourthRatings fourthRatings = new FourthRatings();
    ArrayList<Rating> ratedList = fourthRatings.getAverageRatings(minimalRatings);

    Collections.sort(ratedList);
    // Print the number of raters after creating a ThirdsRating object.
    System.out.printf("Total movies with %d ratings is %d\n", minimalRatings, ratedList.size());

    // Print the number of movies in the database.
    System.out.println("The number of movies in the database is " + MovieDatabase.size());

    // You will call getAverageRatings with a minimal number of raters to return an ArrayList of
    // type Rating.

    ArrayList<Rating> averageRatings = fourthRatings.getAverageRatings(minimalRatings);

    // Print out how many movies with ratings are returned,
    // then sort them, and print out the rating and title of each movie
    System.out.printf(
        "How many movies with ratings %d are returned: %d%n",
        minimalRatings, averageRatings.size());

    printRatingsList(averageRatings);
  }

  public void printAverageRatingsByYearAfterAndGenre(int minimalRatings, int year, String genre) {
    FourthRatings fourthRatings = new FourthRatings();
    AllFilters filters = new AllFilters();
    filters.addFilter(new GenreFilter(genre));
    filters.addFilter(new YearAfterFilter(year));
    System.out.println(fourthRatings.getAverageRatingsByFilter(minimalRatings, filters).size());

    //    System.out.printf(
    //        "Print movie(s) with at least %d rating in \"%s\" genre produced after year" + " of %d
    // %n",
    //        minimalRatings, genre, year);
    //    printRatingsList(thirdRatings.getAverageRatingsByFilter(minimalRatings, filters));
  }

  private void printRatingsList(ArrayList<Rating> averageRatingList) {
    System.out.printf("Found %d movie(s)%n", averageRatingList.size());
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
}
