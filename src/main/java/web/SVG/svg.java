package web.SVG;

import domain.Queries.Queries;

/**
 * CREATED BY mathias @ 07-12-2020 - 14:13
 **/
public class svg {
    private final Queries queries;
    private final int width;
    private final int length;

    public svg(Queries queries) {
        this.queries = queries;
        this.length = queries.getCarport().getLength() +10;
        this.width = queries.getCarport().getLength() + 10;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Queries getQueries() {
        return queries;
    }
}
