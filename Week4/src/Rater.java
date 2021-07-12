import java.util.ArrayList;
import java.util.HashMap;

public interface Rater {
  //  void addRating(String item, double rating);
  void addRating(String movieID, double rating);

  boolean hasRating(String item);

  String getID();

  double getRating(String item);

  int numRatings();

  ArrayList<String> getItemsRated();

  HashMap<String, Rating> getMyRatings();
}
