import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

  /**
   * Print a list of movies and their average ratings sorted by averages
   *
   * @param minimalRatings int specified number of ratings
   */
  public static void printAverageRatings(int minimalRatings) {
    ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");
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
        "How many movies with ratings %d are returned: %d\n",
        minimalRatings, averageRatings.size());

    averageRatings.stream()
        .sorted()
        .forEach(
            rating ->
                System.out.printf(
                    "%-4s %s%n", rating.getValue(), MovieDatabase.getTitle(rating.getItem())));
    //    System.out.printf(
    //            "The name of the movie that has the lowest rating is \"%s\"\n",
    //            thirdRatings.getTitle(ratedList.get(0).getItem()));
    //    for (Rating ratedObj : ratedList) {
    //      double rating = ratedObj.getValue();
    //      String movieID = ratedObj.getItem();
    //      String movieTitle = secondRatings.getTitle(movieID);
    //      System.out.println(rating + " " + movieTitle);
    //    }
  }
}
