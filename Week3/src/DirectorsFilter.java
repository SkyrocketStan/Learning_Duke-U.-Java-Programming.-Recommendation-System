import java.util.Arrays;

/**
 * A class for filter movies by directors
 *
 * @author Stanislav Rakitov
 * @version 1.0
 */
public class DirectorsFilter implements Filter {
  String directors;

  public DirectorsFilter(String directors) {
    this.directors = directors;
  }

  @Override
  public boolean satisfies(String id) {
    String[] movieDirectors = MovieDatabase.getDirector(id).split(",");
    String[] filterDirectors = directors.split(",");
    return Arrays.asList(movieDirectors).containsAll(Arrays.asList(filterDirectors));
  }
}
