import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The week 4 class
 *
 * @author Stanislav Rakitov
 * @version 1.0
 */
public class FourthRatings {

  private Double getAverageByID(String movieID, Integer minimalRaters) {
    ArrayList<Rater> myRaters = RaterDatabase.getRaters();
    long numOfRatings = myRaters.stream().filter(rater -> rater.hasRating(movieID)).count();

    if (numOfRatings >= minimalRaters) {
      return myRaters.stream()
          .filter(rater -> rater.hasRating(movieID))
          .mapToDouble(rating -> rating.getRating(movieID))
          .average()
          .orElse(0.0);
    }
    return 0.0;
  }

  public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    ArrayList<Rating> list = new ArrayList<>();
    ArrayList<String> allMoviesIDs = MovieDatabase.filterBy(new TrueFilter());
    for (String movieID : allMoviesIDs) {
      double averageRating = getAverageByID(movieID, minimalRaters);
      if (averageRating != 0.0) {
        list.add(new Rating(movieID, averageRating));
      }
    }
    return list;
  }

  public ArrayList<Rating> getAverageRatingsByFilter(Integer minimalRaters, Filter filterCriteria) {
    ArrayList<Rating> ratingsList = new ArrayList<>();
    ArrayList<String> allMoviesIDs = MovieDatabase.filterBy(filterCriteria);
    Rating rating;
    for (String movie_id : allMoviesIDs) {
      if (getAverageByID(movie_id, minimalRaters) != 0) {
        rating = new Rating(movie_id, getAverageByID(movie_id, minimalRaters));
        ratingsList.add(rating);
      }
    }

    Collections.sort(ratingsList);
    return ratingsList;
  }

  private double dotProduct(Rater meRater, Rater otherRater) {
    HashMap<String, Rating> myRatings = meRater.getMyRatings();
    double result = 0.0;
    for (String id : myRatings.keySet()) {
      double scale = meRater.getRating(id) - 5;
      if (otherRater.hasRating(id)) {
        result += scale * (otherRater.getRating(id) - 5);
      }
    }
    return result;
  }
}
