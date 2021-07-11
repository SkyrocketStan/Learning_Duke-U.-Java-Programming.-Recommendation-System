/**
 * A class for filter movies by genre
 *
 * @author Stanislav Rakitov
 * @version 1.0
 */
public class GenreFilter implements Filter {

  private final String genre;

  // The constructor should have one parameter named genre representing one genre,
  // and the satisfies method should return true if a movie has this genre.
  // Note that movies may have several genres.
  public GenreFilter(String genre) {
    this.genre = genre.toLowerCase();
  }

  @Override
  public boolean satisfies(String id) {
    return MovieDatabase.getGenres(id).toLowerCase().contains(genre);
  }
}
