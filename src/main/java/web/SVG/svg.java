package web.SVG;

import domain.Carport.Carport;
import domain.Queries.Queries;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 07-12-2020 - 14:13
 **/
public class svg {
    private Queries queries;
    private int width;
    private int length;

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
