public class MovieRunnerAverage {

  public MovieRunnerAverage() {}

  public void printAverageRatings() {
    SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
    System.out.printf(
        "Total number of movies %d, total number of raters %d",
        secondRatings.getMovieSize(), secondRatings.getRaterSize());
  }
}
