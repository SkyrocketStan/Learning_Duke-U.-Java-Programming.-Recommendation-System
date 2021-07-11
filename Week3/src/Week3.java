public class Week3 {
  public static void main(String[] args) {
    //    ThirdRatings week3 = new ThirdRatings();
    //    ArrayList<Rating> averageRatings = week3.getAverageRatings(1);
    //    averageRatings.forEach(System.out::println);
    MovieRunnerWithFilters filters = new MovieRunnerWithFilters();
    filters.printAverageRatingsByYear(1, 2000);
    System.out.println("---");
    filters.printAverageRatingsByGenre(1, "Crime");
    System.out.println("---");
    filters.printAverageRatingsByMinutes(1, 110, 170);
    System.out.println("---");

    //    MovieRunnerWithFilters filtersFull =
    //            new MovieRunnerWithFilters("ratedmoviesfull.csv", "ratings.csv");
    //    filtersFull.printAverageRatingsByYear(20, 2000);
  }
}
