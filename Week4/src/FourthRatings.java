import java.util.ArrayList;

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
}
