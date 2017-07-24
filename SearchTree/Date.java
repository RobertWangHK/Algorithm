package Search_Tree;

/**
 * Created by rober on 7/2/2017.
 */
public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object that){
        if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass()) return false;
        if (this.day != ((Date)that).day || this.month != ((Date)that).month
                || this.year != ((Date)that).year ) return false;
        return true;
    }
}
