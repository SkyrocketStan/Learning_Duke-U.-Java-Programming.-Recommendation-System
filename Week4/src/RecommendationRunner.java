import java.util.ArrayList;

/** @author Stanislav Rakitov */
public class RecommendationRunner implements Recommender {
  @Override
  public ArrayList<String> getItemsToRate() {
    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    ArrayList<String> toRet = new ArrayList<>();
    for (int i = 0; i < 25; i++) {
      toRet.add(movies.get(i));
    }
    return toRet;
  }

  @Override
  public void printRecommendationsFor(String webRaterID) {
    FourthRatings fr = new FourthRatings();
    ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(webRaterID, 50, 2, new TrueFilter());
    if (movies.size() == 0) {
      System.out.println("<p>Sorry, there are no recommendations for you.</p>");
      System.exit(1);
    }
    System.out.println("<table>");
    System.out.println("<tr><th>Rank</th><th>Movie Title</th></tr>");
    for (int i = 0; i < 15; i++) {
      System.out.println(
          "<tr><td>"
              + (i + 1)
              + "</td><td>"
              + MovieDatabase.getTitle(movies.get(i).getItem())
              + "</td></tr>");
    }
    System.out.println("</table>");
  }
}
