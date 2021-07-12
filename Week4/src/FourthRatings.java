import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

/**
 * The week 4 class
 *
 * @author Stanislav Rakitov
 * @version 1.0
 */
public class FourthRatings {

  private Double getAverageByID(String movieID, Integer minimalRaters) {
    RaterDatabase.initialize("ratings.csv");
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
    RaterDatabase.initialize("ratings.csv");
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

    sort(ratingsList);
    return ratingsList;
  }

  public double dotProduct(Rater meRater, Rater otherRater) {
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

  private ArrayList<Rating> getSimilarities(String id) {
    ArrayList<Rating> list = new ArrayList<>();
    Rater me = RaterDatabase.getRater(id);
    for (Rater rater : RaterDatabase.getRaters()) {
      if (!me.getID().equals(rater.getID())) {
        double dotProduct = dotProduct(me, rater);
        if (dotProduct > 0) {
          Rating rating = new Rating(rater.getID(), dotProduct);
          list.add(rating);
        }
      }
    }
    list.sort(reverseOrder());
    return list;
  }

  public ArrayList<Rating> getSimilarRatings(
      String id, Integer numSimilarRaters, Integer minimalRaters) {
    ArrayList<Rating> ratingMoive = new ArrayList<>();
    ArrayList<Rating> ratingRater = getSimilarities(id);
    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    for (String movie_id : movies) {
      int md_id = Integer.parseInt(movie_id);
      if (hasMinRaters(movie_id, minimalRaters, numSimilarRaters, ratingRater)) {
        double sum = 0.0;
        double ave;
        double num = 0.0;
        for (int i = 0; i < numSimilarRaters; i++) {
          Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
          HashMap<String, Rating> movieRated = rater.getMyRatings();
          for (String mo_id : movieRated.keySet()) {
            int rm_id = Integer.parseInt(mo_id);
            if (rm_id == md_id) {
              sum += ratingRater.get(i).getValue() * rater.getRating(mo_id);
              num += 1;
            }
          }
        }
        if (num != 0.0) {
          ave = sum / num;
          Rating rating = new Rating(movie_id, ave);
          ratingMoive.add(rating);
        }
      }
    }
    ratingMoive.sort(reverseOrder());
    return ratingMoive;
  }

  private boolean hasMinRaters(
      String movie_id,
      Integer minimalRaters,
      Integer numSimilarRaters,
      ArrayList<Rating> ratingRater) {
    int numOfRaters = 0;
    int md_id = Integer.parseInt(movie_id);
    for (int i = 0; i < numSimilarRaters; i++) {
      Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
      HashMap<String, Rating> movieRated = rater.getMyRatings();
      for (String mo_id : movieRated.keySet()) {
        int rm_id = Integer.parseInt(mo_id);
        if (rm_id == md_id) {
          numOfRaters += 1;
        }
      }
    }
    return numOfRaters >= minimalRaters;
  }

  public ArrayList<Rating> getSimilarRatingsByFilter(
      String id, Integer numSimilarRaters, Integer minimalRaters, Filter filterCriteria) {
    ArrayList<Rating> ratingMoive = new ArrayList<>();
    ArrayList<Rating> ratingRater = getSimilarities(id);
    ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    for (String movie_id : movies) {
      int md_id = Integer.parseInt(movie_id);
      if (hasMinRaters(movie_id, minimalRaters, numSimilarRaters, ratingRater)) {
        double sum = 0.0;
        double ave;
        double num = 0.0;
        for (int i = 0; i < numSimilarRaters; i++) {
          Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
          HashMap<String, Rating> movieRated = rater.getMyRatings();
          for (String mo_id : movieRated.keySet()) {
            int rm_id = Integer.parseInt(mo_id);
            if (rm_id == md_id) {
              sum += ratingRater.get(i).getValue() * rater.getRating(mo_id);
              num += 1.0;
            }
          }
        }
        if (num != 0.0) {
          ave = sum / num;
          Rating rating = new Rating(movie_id, ave);
          ratingMoive.add(rating);
        }
      }
    }
    ratingMoive.sort(reverseOrder());
    return ratingMoive;
  }
}
