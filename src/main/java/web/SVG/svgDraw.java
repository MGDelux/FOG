package web.SVG;

import domain.Carport.Carport;
import domain.Queries.Queries;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 15-12-2020 - 11:13
 **/
public class svgDraw implements SvgFactory {

    // v1 prototype SVG
    private int extraPoles;
    private int viewPortWidth = 10;
    private int viewPortLength = 10;
    private int innerCarportWidth = 0;
    private int innerCarportLength = 0;
    private int shedY;
    private int shedX;
    private  String SVGString = "";
//god why lol
    @Override
    public String drawCarport(Queries queries) {
        CalculateViewPortSize(queries.getCarport());
        GetCarportSize(queries.getCarport());
        extraPoles = GetExtraPoles(queries.getCarport());
        calculateShedXY(queries.getCarport(), queries.getShed());
        SVGString = SVGString + draw(queries.getCarport(), queries.getShed());
        SVGString = SVGString + drawLeftPoles(extraPoles, queries.getCarport());
        SVGString = SVGString + drawRightPoles(extraPoles, queries.getCarport());
        SVGString = SVGString + drawCross(queries.getCarport());
        SVGString = SVGString + drawRoof(queries.getCarport());
        SVGString = SVGString + drawText(queries.getCarport());
        return SVGString;
    }

    @Override
    public String updateDrawCarport(Carport carport, Shed shed) {
        CalculateViewPortSize(carport);
        GetCarportSize(carport);
        extraPoles = GetExtraPoles(carport);
        calculateShedXY(carport, shed);
        SVGString = SVGString + draw(carport, shed);
        SVGString = SVGString + drawLeftPoles(extraPoles, carport);
        SVGString = SVGString + drawRightPoles(extraPoles, carport);
        SVGString = SVGString + drawCross(carport);
        SVGString = SVGString + drawRoof(carport);
        SVGString = SVGString + drawText(carport);

        System.out.println(SVGString);
        return SVGString;
    }

    private String drawRoof(Carport carport) {
        String supportBeam = "";
        int x2 = carport.getWidth() + 5;
        int y1;
        int y2;
        supportBeam = "";
        for (int i = 60; i < carport.getLength(); i = i + 60) {
            System.out.println(i);
            y1 = 0 + i;
            y2 = 0 + i;
            supportBeam = supportBeam + "<line x1=\"5\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\" stroke=\"red\" />\n";
        }
        return supportBeam;
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
        String rightpole = "";

        if (carport.getLength() >= 760) {
            System.out.println(extraPoles);
            extraPoles = extraPoles - 1;
            System.out.println(extraPoles);
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
            rightpole = rightpole + "<rect x=\"" + carportWidth + "\" y=\"" + length + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n";

        }
        return rightpole + "<rect x=\"" + carportWidth + "\" y=\"5\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n" + "<rect x=\"" + carportWidth + "\" y=\"" + backPolePlacement + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n";

    }

    private String drawLeftPoles(int extraPoles, Carport carport) {
        String leftpole = "<rect x=\"5\" y=\"5\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n";
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
            leftpole = leftpole + "<rect x=\"5\" y=\" 5 \"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n";

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

    private int GetExtraPoles(Carport carport) {
        int count = 0;
        if (carport.getLength() <= 480) {
            count = 0;
        } else {
            for (int i = 240; i < carport.getLength(); i = i + 240) {
                count++;
            }
            if (count % 2 == 0) {
                return count;
            } else {
                count = count + 1;
            }
        }
        return count;
    }

    private void GetCarportSize(Carport carport) {
        innerCarportLength = carport.getLength() - 10;
        innerCarportWidth = carport.getWidth() - 10;
    }

    private void CalculateViewPortSize(Carport carport) {
        this.viewPortLength = viewPortLength + carport.getLength();
        this.viewPortWidth = viewPortWidth + carport.getWidth();

    }
}
