package web.SVG;

import domain.Carport.Carport;
import domain.Queries.Queries;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 15-12-2020 - 11:13
 **/
public class SvgDraw implements SvgFactory {

    // v1 prototype SVG
    //setting default values and creating required ints, strings.
/**
 * current set "rules" of creating carport drawing until we get more accurate information:
 * svg view port is always 10'cm'+ larger than carport length and shed
 * "eaves" (tagudhæng) is the set value from the customer  note: 'should be 15cm'
 * and "inner carport" the "carport structure" is 10'cm' less than the selected carport width x length (x,y)
 * poles: extra poles are only added if the carport length exceeds 480cm else it will always be 4 poles
 * the roof *flat*: (Support beams) is set every 60cm of the carports length and are the same length as the carports width.
 * flat roof sheets not yet calculated
 * angled roof todo.
 ***/
    private int extraPoles;
    private int viewPortWidth = 10;
    private int viewPortLength = 10;
    private int innerCarportWidth = 0;
    private int innerCarportLength = 0;
    private int shedY;
    private int shedX;
    private  String svgString = "";
//god why lol

    @Override //calculation values based on entered infomation and set rules
    public String drawCarport(Queries queries) {
        calculateViewPortSize(queries.getCarport());
        getCarportSize(queries.getCarport());
        extraPoles = getExtraPoles(queries.getCarport());
        calculateShedXY(queries.getCarport(), queries.getShed());
        svgString = svgString + draw(queries.getCarport(), queries.getShed());
        svgString = svgString + drawLeftPoles(extraPoles, queries.getCarport());
        svgString = svgString + drawRightPoles(extraPoles, queries.getCarport());
        svgString = svgString + drawCross(queries.getCarport());
        svgString = svgString + drawRoof(queries.getCarport());
        svgString = svgString + drawText(queries.getCarport());
        return svgString;
    }
    @Override
    public String updateDrawCarport(Carport carport, Shed shed) {
        calculateViewPortSize(carport);
        getCarportSize(carport);
        extraPoles = getExtraPoles(carport);
        calculateShedXY(carport, shed);
        svgString = svgString + draw(carport, shed);
        svgString = svgString + drawLeftPoles(extraPoles, carport);
        svgString = svgString + drawRightPoles(extraPoles, carport);
        svgString = svgString + drawCross(carport);
        svgString = svgString + drawRoof(carport);
        svgString = svgString + drawText(carport);
        return svgString;
    }

    private String drawRoof(Carport carport) {
        StringBuilder supportBeam;
        int x2 = carport.getWidth() + 5;
        int y1;
        int y2;
        supportBeam = new StringBuilder();
        for (int i = 60; i < carport.getLength(); i = i + 60) {
            y1 = i;
            y2 = i; //should be a string builder but aint got time for that
            supportBeam.append("<line x1=\"5\" y1=\"").append(y1).append("\" x2=\"").append(x2).append("\" y2=\"").append(y2).append("\" stroke=\"red\" />\n");
        }
        return supportBeam.toString();
    }

    private String drawText(Carport carport) {
        int length = carport.getLength() / 100;
        int width = carport.getWidth() / 100;
        return "<text x=\"50%\" y=\"5%\" dominant-baseline=\"middle\" text-anchor=\"middle\">-- Brede " + width + "meter --</text>\n" + " <text x=\"5%\" y=\"35%\" style=\"writing-mode: tb; glyph-orientation-vertical: 90;\">\n " + "-- Længde " + length + "meter --" + "\n</text></svg>\n";

    }

    private String drawCross(Carport carport) {
        int line2x2 = carport.getWidth() + 5;
        int line2y1 = carport.getLength() + 5;
        return "<line x1=\"5\" y1=\"5\" x2=\"" + carport.getWidth() + "\" y2=\"" + carport.getLength() + "\" stroke=\"red\" stroke-dasharray=\"10\" />\n" + "<line x1=\"5\" y1=\"" + line2y1 + "\" x2=\"" + line2x2 + "\" y2=\"5\" stroke=\"red\" stroke-dasharray=\"10\" />\n";
    }

    private String drawRightPoles(int extraPoles, Carport carport) {
        StringBuilder rightpole = new StringBuilder();

        if (carport.getLength() >= 760) {
            extraPoles = extraPoles - 1;
        }
        int carportWidth = carport.getWidth() - 5;
        int length = 245;
        int backPolePlacement = carport.getLength() - 5;
        int poleCounter = 0;
        for (int i = 1; i < extraPoles; i++) {
            poleCounter++;
            if (poleCounter > 1) {
                length = length + 245;
            }
            rightpole.append("<rect x=\"").append(carportWidth).append("\" y=\"").append(length).append("\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");

        }
        return rightpole + "<rect x=\"" + carportWidth + "\" y=\"5\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n" + "<rect x=\"" + carportWidth + "\" y=\"" + backPolePlacement + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n";

    }

    private String drawLeftPoles(int extraPoles, Carport carport) {
        StringBuilder leftpole = new StringBuilder("<rect x=\"5\" y=\"5\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");
        if (carport.getLength() >= 760) {
            extraPoles = extraPoles - 1;
        }
        int backPolePlacement = carport.getLength() - 5;
        int poleCounter = 0;
        int length = 245;
        for (int i = 1; i < extraPoles; i++) {
            poleCounter++;
            if (poleCounter > 1) {
                length = length + 245;
            }
            leftpole.append("<rect x=\"5\" y=\"").append(length).append("\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");

        }
        return leftpole + "<rect x=\"5\" y=\"" + backPolePlacement + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n";
    }


    private String draw(Carport carport, Shed shed) {
        return "<svg version=\"1.1\" alignment-baseline=\"central\" width=\"" + viewPortWidth + "\" height=\"" + viewPortLength + "\" xmlns=\"http://www.w3.org/2000/svg\">\n" + "<rect x=\"5\" y=\"5\" width=\"" + carport.getWidth() + "\" height=\"" + carport.getLength() + "\" fill=\"none\" stroke=\"#000000\"/>\n" +
                "<rect x=\"10\" y=\"10\" width=\"" + innerCarportWidth + "\" height=\"" + innerCarportLength + "\" fill=\"none\" stroke=\"#000000\"/>\n" +
                "<rect x=\"" + shedY + "\" y=\"" + shedX + "\"  width=\"" + shed.getWidth() + "\" height=\"" + shed.getLength() + "\" fill=\"none\" stroke=\"#000000\"/>\n";
    }

    private void calculateShedXY(Carport carport, Shed shed) {
        shedX = carport.getLength() - shed.getLength();
        shedY = carport.getWidth() - shed.getWidth();
    }

    private int getExtraPoles(Carport carport) {
        int count = 0;
        if (carport.getLength() >= 480) {
            for (int i = 240; i < carport.getLength(); i = i + 240) {
                count++;
            }
            if (count % 2 == 0) {
                return count;
            } else {
                count = count + 1;
            }
            return count;
        }
        return count;
    }

    private void getCarportSize(Carport carport) {
        innerCarportLength = carport.getLength() - 10;
        innerCarportWidth = carport.getWidth() - 10;
    }

    private void calculateViewPortSize(Carport carport) {
        this.viewPortLength = viewPortLength + carport.getLength();
        this.viewPortWidth = viewPortWidth + carport.getWidth();

    }
}
