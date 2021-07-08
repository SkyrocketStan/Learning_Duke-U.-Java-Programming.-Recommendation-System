public class Week2 {
  public static void main(String[] args) {
    MovieRunnerAverage average = new MovieRunnerAverage();

    System.out.printf(
        "Q5. What is the rating of the movie “The Maze Runner”? - %.4f\n",
        average.getAverageRatingOneMovie("The Maze Runner"));

    System.out.printf(
        "Q6. What is the rating of the movie “Moneyball”? - %.4f\n",
        average.getAverageRatingOneMovie("Moneyball"));

    System.out.printf(
        "Q7. what is the rating of the movie “Vacation”? - %.4f\n",
        average.getAverageRatingOneMovie("Vacation"));

    // Q8. Using the files ratedmoviesfull.csv and ratings.csv, how many movies have 50 or more
    // ratings?
    average.printAverageRatings(50);

    // Q9. Using the files ratedmoviesfull.csv and ratings.csv, of those movies that have at least
    // 20 ratings,
    // what is the name of the movie that has the lowest rating?

    average.printAverageRatings(20);

    // Using the files ratedmoviesfull.csv and ratings.csv,
    // of those movies that have at least 12 ratings,
    // what is the name of the movie that has the lowest rating?
    average.printAverageRatings(12);
  }
}
