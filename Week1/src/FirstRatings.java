import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * A class to process the movie and ratings data and to answer questions about them.
 *
 * @author Stanislav Rakitov
 * @version 1.0
 */
public class FirstRatings {
  private ArrayList<Movie> movieArrayList;
  private ArrayList<Rater> raterArrayList;

  private HashMap<String, HashSet<String>> directorsAndItsMovies;
  private HashMap<String, Rater> ratersWithIds;
  private HashMap<String, Integer> ratersAndCountOfRatings;
  private HashMap<String, ArrayList<String>> moviesAndRatersMap;

  // The constrictor for only Movies
  public FirstRatings(String fileName) {
    String fullFileName = getFullFileName(fileName);
    this.movieArrayList = loadMovies(fullFileName);
    this.directorsAndItsMovies = getDirectorsAndMovies();
  }

  // The constructor for Movies and Raters
  public FirstRatings(String fileNameMovies, String fileNameRaters) {
    this(fileNameMovies);
    String fullFileNameRaters = getFullFileName(fileNameRaters);
    this.raterArrayList = loadRaters(fullFileNameRaters);
    this.ratersWithIds = loadRatersWithIDs(raterArrayList);
    this.ratersAndCountOfRatings = getAllRatersIDandItsRatingsCounterMap();
    this.moviesAndRatersMap = getAllMoviesAndRatesMap(this.movieArrayList);
  }

  public FirstRatings() {
    // Maybe later..
  }

  private HashMap<String, ArrayList<String>> getAllMoviesAndRatesMap(ArrayList<Movie> moviesList) {
    HashMap<String, ArrayList<String>> allMoviesAndRatesTempMap = new HashMap<>();
    // fulfill map with all MoviesID with null ArrayList
    for (Movie movie : moviesList) {
      allMoviesAndRatesTempMap.putIfAbsent(movie.getID(), new ArrayList<>());
    }

    for (Rater rater : raterArrayList) {
      ArrayList<String> ratedMovies = rater.getItemsRated();
      for (String ratedMovieID : ratedMovies) {
        allMoviesAndRatesTempMap.get(ratedMovieID).add(rater.getID());
      }
    }

    return allMoviesAndRatesTempMap;
  }

  private HashMap<String, Rater> loadRatersWithIDs(ArrayList<Rater> ratersList) {
    HashMap<String, Rater> map = new HashMap<>();
    for (Rater rater : ratersList) {
      map.put(rater.getID(), rater);
    }
    return map;
  }

  // Proceed map of all directors and its movies
  private HashMap<String, HashSet<String>> getDirectorsAndMovies() {
    HashMap<String, HashSet<String>> map = new HashMap<>();
    for (Movie movie : movieArrayList) {
      String movieName = movie.getTitle();
      String[] directors = movie.getDirector().split(",");
      for (String director : directors) {
        director = director.trim();
        HashSet<String> set = new HashSet<>();
        if (map.containsKey(director)) {
          set = map.get(director);
          set.add(movieName);
        } else {
          set.add(movieName);
          map.put(director, set);
        }
      }
    }
    return map;
  }

  // Process every record from the CSV file.
  private ArrayList<Movie> loadMovies(String filename) {

    // Create empty list of Movie's
    ArrayList<Movie> moviesList = new ArrayList<>();

    // Get Duke's FileResource.
    FileResource fileResource = new FileResource(filename);

    // Get Apache CSV
    CSVParser csvRecords = fileResource.getCSVParser();

    // Proceed every CSVRecord
    for (CSVRecord record : csvRecords) {
      moviesList.add(parseMovieCSVRecord(record));
    }
    return moviesList;
  }

  // Creates new Movie object
  private Movie parseMovieCSVRecord(CSVRecord record) {
    /* id(0),title(1),year(2),country(3),genre(4),director(5),minutes(6),poster(7)
     * As it shows in CSV file
     */
    String id = record.get(0);
    String title = record.get(1);
    String year = record.get(2);
    String country = record.get(3);
    String genre = record.get(4);
    String director = record.get(5);
    // The constructor takes minutes as int
    int minutes = Integer.parseInt(record.get(6));
    String poster = record.get(7);

    // return new Movie object
    return new Movie(id, title, year, genre, director, country, poster, minutes);
  }

  /**
   * A void method for tests cases.
   *
   * <ul>
   *   <li>determine how many movies in total.
   *   <li>determine how many movies include the Comedy genre.
   *   <li>determine how many movies are greater than 150 minutes in length.
   *   <li>determine the maximum number of movies by any director, and who the directors are that
   *       directed that many movies.
   * </ul>
   */
  public void testLoadMovies() {
    int totalMoviesCount = movieArrayList.size();
    int totalComedyCount = totalComedy();
    int totalMoviesLonger150MinutesCount = total150();
    String directorWithMaxFilms = maxFilmsOneDirectorName()[0];
    String oneDirectorMaxFilmsCounter = maxFilmsOneDirectorName()[1];

    System.out.printf("Note there are %d movies in this file.\n", totalMoviesCount);
    System.out.println("How many of the movies include the Comedy genre? > " + totalComedyCount);
    System.out.println(
        "How many of these movies run longer than 150 minutes? > "
            + totalMoviesLonger150MinutesCount);
    System.out.println(
        "What is the maximum number of films directed by one director? > "
            + oneDirectorMaxFilmsCounter);
    System.out.println(
        "What is the name of the director who directed more films than any other "
            + "director? "
            + directorWithMaxFilms);
    System.out.println("---");
  }

  // Determine the Director with max movies and its count
  private String[] maxFilmsOneDirectorName() {
    String director = "";
    int counter = 0;
    for (Map.Entry<String, HashSet<String>> pair : directorsAndItsMovies.entrySet()) {
      if (pair.getValue().size() > counter) {
        counter = pair.getValue().size();
        director = pair.getKey();
      }
    }
    String stringCounter = String.valueOf(counter);
    return new String[] {director, stringCounter};
  }

  // determine how many movies are greater than 150 minutes in length.
  private int total150() {
    int counter = 0;
    for (Movie movie : movieArrayList) {
      if (movie.getMinutes() > 150) {
        counter++;
      }
    }
    return counter;
  }

  // determine how many movies include the Comedy genre
  private int totalComedy() {
    int counter = 0;
    for (Movie movie : movieArrayList) {
      if (movie.getGenres().toLowerCase().contains("comedy")) {
        counter++;
      }
    }
    return counter;
  }

  // Make full filename for any OS types
  private String getFullFileName(String fileName) {
    if (!(new File(fileName).exists())) {
      fileName =
          System.getProperty("user.dir") + File.separator + "data" + File.separator + fileName;
    }
    return fileName;
  }

  public void testLoadRaters(String raterID, String movieID) {

    // Use for test purposes
    testPrintAllRaterWithRatings();

    // find the number of ratings for a particular rater
    printNumberOfRatings(raterID);

    // Find the maximum number of ratings by any rater
    printMaxNumOfRatings();

    // Print the number of ratings a particular movie has
    //    String movieID = "1798709";
    System.out.printf(
        "How many ratings does the movie %s have? > %d\n",
        movieID, getNumberOfRatingsByMovie(movieID));
    // And how many different movies have been rated by all these raters

    // total number of unique movies that have been rated
    printTotalNumberMoviesRated();
  }

  // Q10. What is the total number of unique movies that have been rated?
  private void printTotalNumberMoviesRated() {
    int total = 0;
    for (Map.Entry<String, ArrayList<String>> entry : moviesAndRatersMap.entrySet()) {
      if (entry.getValue() != null) {
        total++;
      }
    }
    System.out.println(
        "What is the total number of unique movies that have been rated? > " + total);
  }

  // Get number of Ratings by MovieID
  private int getNumberOfRatingsByMovie(String movieID) {
    return moviesAndRatersMap.get(movieID).size();
  }

  // Determine how many raters have this maximum number of ratings and who those raters are.
  private void printMaxNumOfRatings() {

    // Find max rating value
    int maxRatings = getMaxRatings();
    System.out.println("What is the maximum number of ratings by any rater? > " + maxRatings);

    for (Map.Entry<String, Integer> entry : ratersAndCountOfRatings.entrySet()) {
      if (entry.getValue() == maxRatings) {
        System.out.println("Which rater rated the most number of movies? > " + entry.getKey());
      }
    }
  }

  // find the maximum number of ratings by any rater
  private int getMaxRatings() {
    int maxRatings = 0;
    for (int counter : ratersAndCountOfRatings.values()) {
      if (counter > maxRatings) {
        maxRatings = counter;
      }
    }
    return maxRatings;
  }

  // Get map with number of rates for every Rater
  private HashMap<String, Integer> getAllRatersIDandItsRatingsCounterMap() {
    HashMap<String, Integer> map = new HashMap<>();
    for (String id : ratersWithIds.keySet()) {
      int count = getNumberOfRatingsByRaterID(id);
      map.put(id, count);
    } // for
    return map;
  }

  // Find the number of ratings for a particular rater
  private void printNumberOfRatings(String id) {
    int ratingsCount = getNumberOfRatingsByRaterID(id);
    System.out.printf("How many ratings does the rater number %s have? > %s\n", id, ratingsCount);
    //    System.out.printf("Rater id: %s has %d ratings\n", id, ratingsCount);
  }

  // Get total number of rates for specific rater's id
  private int getNumberOfRatingsByRaterID(String id) {
    return ratersWithIds.get(id).numRatings();
  }

  /*
  Print the total number of raters. Then for each rater, print the rater’s ID and the number of
  ratings they did on one line, followed by each rating (both the movie ID and the rating given)
  on a separate line. If you run your program on the file ratings_short.csv you will see there
  are five raters.
   */
  private void testPrintAllRaterWithRatings() {
    // Print the total number of raters.
    int totalRaterCount = raterArrayList.size();
    System.out.printf("Note there are %d raters in this file.\n", totalRaterCount);

    // Then for each rater, print the rater’s ID
    // and the number of ratings they did on one line
    //    printAllRaterRatings();
  }

  // Print out number o rates for all raters
  private void printAllRaterRatings() {
    for (Map.Entry<String, Integer> entry : ratersAndCountOfRatings.entrySet()) {
      String raterId = entry.getKey();
      int ratinsCount = entry.getValue();
      System.out.printf("Rater id: %s has %d ratings\n", raterId, ratinsCount);
      printAllRatingsByRater(raterId);
    }
  }

  // Print all ratings for specific Rater ID
  private void printAllRatingsByRater(String raterID) {
    Rater rater = ratersWithIds.get(raterID);
    ArrayList<String> allRatedMoviesIDs = rater.getItemsRated();
    for (String id : allRatedMoviesIDs) {
      double rating = rater.getRating(id);
      System.out.printf("Movie ID: %s, rating: %f\n", id, rating);
    }
  }

  /*
   * This method should process every record from the CSV file whose name is
   * filename, a file of raters and their ratings
   */
  private ArrayList<Rater> loadRaters(String fileName) {
    ArrayList<Rater> ratersList = new ArrayList<>();
    ArrayList<String> idsList = new ArrayList<>();

    String fullFileName = getFullFileName(fileName);

    // Get Duke's FileResource.
    FileResource fileResource = new FileResource(fullFileName);

    // Get Apache CSV
    CSVParser csvRecords = fileResource.getCSVParser();

    // Proceed every CSVRecord
    for (CSVRecord record : csvRecords) {
      // rater_id,movie_id,rating,time
      String rater_id = record.get(0);
      String movie_id = record.get(1);
      double rating = Double.parseDouble(record.get(2));
      String time = record.get(3);
      if (!idsList.contains(rater_id)) {
        Rater rater = new Rater(rater_id);
        ratersList.add(rater);
        rater.addRating(movie_id, rating);
        idsList.add(rater_id);
      } else {
        for (Rater r : ratersList) {
          if (r.getID().equals(rater_id)) {
            r.addRating(movie_id, rating);
          }
        }
      }
    }

    return ratersList;
  } // loadRaters
} // class
