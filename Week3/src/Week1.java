public class Week1 {
  public static void main(String[] args) {
    FirstRatings testLong = new FirstRatings("ratedmoviesfull.csv", "ratings.csv");
    testLong.testLoadMovies();
    testLong.testLoadRaters("193", "1798709");
  }
}
