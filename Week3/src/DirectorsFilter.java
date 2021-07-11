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
    String movieDirectors = MovieDatabase.getDirector(id);
    String[] filterDirectors = directors.split(",");
    for (String direcor : filterDirectors) {
      if (movieDirectors.contains(direcor)) {
        return true;
      }
    }
    return false;
  }
}
