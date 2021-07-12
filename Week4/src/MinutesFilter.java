/**
 * A class for filter movies by time
 *
 * @author Stanislav Rakitov
 * @version 1.0
 */
public class MinutesFilter implements Filter {
  private final int minMinutes;
  private final int maxMinutes;

  public MinutesFilter(int minMinutes, int maxMinutes) {
    this.minMinutes = minMinutes;
    this.maxMinutes = maxMinutes;
  }

  // No max minutes given
  public MinutesFilter(int minMinutes) {
    this.minMinutes = minMinutes;
    this.maxMinutes = Integer.MAX_VALUE;
  }

  @Override
  public boolean satisfies(String id) {
    return MovieDatabase.getMinutes(id) >= minMinutes && MovieDatabase.getMinutes(id) <= maxMinutes;
  }
}
