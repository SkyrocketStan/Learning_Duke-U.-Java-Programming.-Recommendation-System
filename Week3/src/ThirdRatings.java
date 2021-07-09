import java.util.ArrayList;

public class ThirdRatings {

  private final ArrayList<Rater> myRaters;
  private final ArrayList<String> allMoviesIDs;

  public ThirdRatings() {
    this("ratings.csv");
  }

  public ThirdRatings(String ratingsFileName) {
    this.myRaters = FirstRatings.loadRaters(ratingsFileName);
    this.allMoviesIDs = MovieDatabase.filterBy(new TrueFilter());
  }

  public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    ArrayList<Rating> list = new ArrayList<>();
    for (String movieID : allMoviesIDs) {
      Double averageRating = getAverageByID(movieID, minimalRaters);
      if (averageRating != 0.0) {
        list.add(new Rating(movieID, averageRating));
      }
    }
    return list;
  }

  /*
  This method returns a double representing the average movie rating for this ID
  if there are at least minimalRaters ratings.
  If there are not minimalRaters ratings, then it returns 0.0.
  */
  Double getAverageByID(String movieID, Integer minimalRaters) {
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
} // class
