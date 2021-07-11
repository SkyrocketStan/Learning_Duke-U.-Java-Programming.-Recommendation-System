public class Week3 {
  public static void main(String[] args) {
    //    ThirdRatings week3 = new ThirdRatings();
    //    ArrayList<Rating> averageRatings = week3.getAverageRatings(1);
    //    averageRatings.forEach(System.out::println);
    MovieRunnerWithFilters filters = new MovieRunnerWithFilters();
    filters.printAverageRatingsByYear(1, 2000);
    filters.printAverageRatingsByGenre(1, "Crime");
    filters.printAverageRatingsByMinutes(1, 110, 170);
    filters.printAverageRatingsByDirectors(1, "Charles Chaplin,Michael Mann,Spike Jonze");
    filters.printAverageRatingsByYearAfterAndGenre(1, 1980, "Romance");
    filters.printAverageRatingsByDirectorsAndMinutes(
        1, 30, 170, "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
    //    MovieRunnerWithFilters filtersFull =
    //            new MovieRunnerWithFilters("ratedmoviesfull.csv", "ratings.csv");
    //    filtersFull.printAverageRatingsByYear(20, 2000);
  }
}
