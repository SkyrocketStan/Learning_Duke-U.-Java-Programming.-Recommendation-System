public class Week1 {
  public static void main(String[] args) {

    FirstRatings testLong = new FirstRatings("ratedmoviesfull.csv", "ratings.csv");
    //    FirstRatings testShort = new FirstRatings("ratedmovies_short.csv", "ratings_short.csv");

    testLong.testLoadMovies();
    //    testShort.testLoadMovies();

    testLong.testLoadRaters("193", "1798709");
    //    testShort.testLoadRaters();

  }
}
