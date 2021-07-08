import java.util.ArrayList;

public interface Rater {
  //  void addRating(String item, double rating);
  void addRating(String movieID, Rating rating);

  boolean hasRating(String item);

  String getID();

  double getRating(String item);

  int numRatings();

  ArrayList<String> getItemsRated();
}
