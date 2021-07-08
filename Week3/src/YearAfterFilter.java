public class YearAfterFilter implements Filter {
  private final int myYear;

  public YearAfterFilter(int year) {
    myYear = year;
  }

  @Override
  public boolean satisfies(String id) {
    return MovieDatabase.getYear(id) >= myYear;
  }
}
