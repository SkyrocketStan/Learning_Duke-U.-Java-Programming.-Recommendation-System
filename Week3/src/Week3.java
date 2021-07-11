public class Week3 {
  public static void main(String[] args) {
    //    MovieRunnerWithFilters filters = new MovieRunnerWithFilters();
    //    filters.printAverageRatingsByYear(1, 2000);
    //    filters.printAverageRatingsByGenre(1, "Crime");
    //    filters.printAverageRatingsByMinutes(1, 110, 170);
    //    filters.printAverageRatingsByDirectors(1, "Charles Chaplin,Michael Mann,Spike Jonze");
    //    filters.printAverageRatingsByYearAfterAndGenre(1, 1980, "Romance");
    //    filters.printAverageRatingsByDirectorsAndMinutes(
    //        1, 30, 170, "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");

    MovieRunnerWithFilters quiz3 = new MovieRunnerWithFilters("ratedmoviesfull.csv", "ratings.csv");
    System.out.print(
        "Q.4 number of minimal raters set to 35. " + "How many rated movies are returned? ");
    System.out.println(quiz3.getAverageRatingsNumber(35));

    System.out.print(
        "Q.5 printAverageRatingsByYearAfter. Minimal raters 20. Year 2000. How "
            + "many rated movies are returned? ");
    quiz3.printAverageRatingsByYear(20, 2000);

    System.out.print(
        "Q.6 printAverageRatingsByGenre. minimal raters 20. genre Comedy. How many "
            + "rated"
            + " movies are returned? ");
    quiz3.printAverageRatingsByGenre(20, "Comedy");

    System.out.print(
        "Q.7 printAverageRatingsByMinutes. Minimal raters 5. Movies that take at "
            + "least 105 minutes and at most 135 minutes. How many rated movies are returned? ");
    quiz3.printAverageRatingsByMinutes(5, 105, 135);

    System.out.println(
        "Q.8 printAverageRatingsByDirectors. Minimal raters 4. And one of these directors:");
    System.out.println(
        "\"Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack\"");
    System.out.print("How many rated movies are returned? ");
    quiz3.printAverageRatingsByDirectors(
        4,
        "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");

    System.out.println(
        "Q.9 printAverageRatingsByYearAfterAndGenre. Minimal raters 8, the year "
            + "set to 1990, and the genre set to \"Drama\".");
    System.out.print("How many rated movies are returned? ");
    quiz3.printAverageRatingsByYearAfterAndGenre(8, 1990, "Drama");

    System.out.print(
        "Q.10 printAverageRatingsByDirectorsAndMinutes. \n"
            + "Minimal raters 3 \n"
            + "and the length of the film set to at least 90 minutes and no more than 180 minutes.\n"
            + "Find all the movies that have at least one of these directors: \n"
            + "\"Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack\". \n"
            + "How many rated movies are returned? ");
    quiz3.printAverageRatingsByDirectorsAndMinutes(
        3, 90, 180, "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
  }
}
