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

  public void printSimilarRatings() {
    FourthRatings fourthR = new FourthRatings();
    MovieDatabase.initialize("ratedmoviesfull.csv");
    RaterDatabase.initialize("ratings.csv");
    ArrayList<Rating> ratingList = fourthR.getSimilarRatings("71", 20, 5);
    System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
  }

  public void printSimilarRatingsByGenre() {
    FourthRatings fourthR = new FourthRatings();
    MovieDatabase.initialize("ratedmoviesfull.csv");
    RaterDatabase.initialize("ratings.csv");
    ArrayList<Rating> ratingList =
        fourthR.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
    System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
  }

  public void printSimilarRatingsByDirector() {
    FourthRatings fourthR = new FourthRatings();
    MovieDatabase.initialize("ratedmoviesfull.csv");
    RaterDatabase.initialize("ratings.csv");
    ArrayList<Rating> ratingList =
        fourthR.getSimilarRatingsByFilter(
            "120",
            10,
            2,
            new DirectorsFilter(
                "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
    System.out.println(ratingList.size());
    System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
  }

  public void printSimilarRatingsByGenreAndMinutes() {
    FourthRatings fourthR = new FourthRatings();
    MovieDatabase.initialize("ratedmoviesfull.csv");
    RaterDatabase.initialize("ratings.csv");
    AllFilters allFilters = new AllFilters();
    allFilters.addFilter(new MinutesFilter(80, 160));
    allFilters.addFilter(new GenreFilter("Drama"));
    ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("168", 10, 3, allFilters);
    System.out.println(ratingList.size());
    System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
  }

  public void printSimilarRatingsByYearAfterAndMinutes() {
    FourthRatings fourthR = new FourthRatings();
    MovieDatabase.initialize("ratedmoviesfull.csv");
    RaterDatabase.initialize("ratings.csv");
    AllFilters allFilters = new AllFilters();
    allFilters.addFilter(new MinutesFilter(70, 200));
    allFilters.addFilter(new YearAfterFilter(1975));
    ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("314", 10, 5, allFilters);
    System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
  }
}
