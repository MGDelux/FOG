package web.SVG;

import domain.Carport.Carport;
import domain.Shed.Shed;

/**
 * CREATED BY mathias @ 15-12-2020 - 11:13
 **/
public class rect implements SvgFactory {
    public rect(Shed shed, Carport carport) {
    }

    // v1 prototype SVG
    private int extraPoles;
    private final int standartPoles = 4;
    private int viewPortWidth = 10;
    private int viewPortLength = 10;
    private int innerCarportWidth = 0;
    private int innerCarportLength = 0;
    private int shedX;
    private int shedY;

    @Override
    public void drawCarport(Carport carport, Shed shed) {
        CalculateViewPortSize(carport);
        GetCarportSize(carport);
        extraPoles = GetExtraPoles(carport);
        calculateShedXY(carport, shed);
        draw(carport, shed);
        drawLeftPoles(extraPoles,carport);
        drawRightPoles(extraPoles,carport);
        drawCross(carport);
        drawText(carport);
    }

    private void drawText(Carport carport) {
        int length = carport.getLength() / 100;
        int width = carport.getWidth() / 100;
        System.out.println("<text x=\"50%\" y=\"5%\" dominant-baseline=\"middle\" text-anchor=\"middle\">-- Brede "+width+"meter --</text>\n");
        System.out.println(" <text x=\"5%\" y=\"35%\" style=\"writing-mode: tb; glyph-orientation-vertical: 90;\">\n "+"-- Længde "+length+"meter --"+"\n</text></svg>\n");

    }

    private void drawCross(Carport carport) {
        int line2x2 = carport.getWidth() +5;
        int line2y1 = carport.getLength() +5;
        System.out.println("<line x1=\"5\" y1=\"5\" x2=\""+carport.getWidth()+ "\" y2=\""+carport.getLength()+ "\" stroke=\"red\" stroke-dasharray=\"10\" />\n");
        System.out.println("<line x1=\"5\" y1=\""+line2y1+"\" x2=\""+line2x2+ "\" y2=\"5\" stroke=\"red\" stroke-dasharray=\"10\" />\n");
    }

    private void drawRightPoles(int extraPoles, Carport carport) {
        if (carport.getLength() >= 780) {
            extraPoles = extraPoles - 1;
        }
        int carportWidth = carport.getWidth() - 5;
        int length = 245;
        int backPolePlacement = carport.getLength() - 5;
        int poleCounter = 0;
        for (int i = 1; i < extraPoles; i++) {
            poleCounter++;
            if (poleCounter > 1) {
                length = length + 240;
            }
            System.out.println("<rect x=\"" + carportWidth + "\" y=\"" + length + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");

        }
        System.out.println("<rect x=\"" + carportWidth + "\" y=\"5\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");
        System.out.println("<rect x=\"" + carportWidth + "\" y=\"" + backPolePlacement + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");

    }

    private void drawLeftPoles(int extraPoles, Carport carport) {
        if (carport.getLength() >= 780) {
            extraPoles = extraPoles - 1;
        }
        int backPolePlacement = carport.getLength() - 5;
        int poleCounter = 0;
        int length = 245;
        for (int i = 1; i < extraPoles; i++) {
            poleCounter++;
            if (poleCounter > 1) {
                length = length + 240;
            }

            System.out.println("<rect x=\"5\" y=\"" + length + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");

        }
        System.out.println("<rect x=\"5\" y=\"5\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");
        System.out.println("<rect x=\"5\" y=\"" + backPolePlacement + "\"  width=\"10\" height=\"10\" fill=\"#000000\" stroke=\"#000000\"/>\n");

    }


    private void draw(Carport carport, Shed shed) {
        System.out.println("<svg version=\"1.1\" width=\"" + viewPortWidth + "\" height=\"" + viewPortLength + "\" xmlns=\"http://www.w3.org/2000/svg\">\n"); //viewport
        System.out.println("<rect x=\"5\" y=\"5\" width=\"" + carport.getWidth() + "\" height=\"" + carport.getLength() + "\" fill=\"none\" stroke=\"#000000\"/>\n"); //outer carport
        System.out.println("<rect x=\"10\" y=\"10\" width=\"" + innerCarportWidth + "\" height=\"" + innerCarportLength + "\" fill=\"none\" stroke=\"#000000\"/>\n"); //innercarport
        System.out.println("<rect x=\"" + shedY + "\" y=\"" + shedX + "\"  width=\"" + shed.getWidth() + "\" height=\"" + shed.getLength() + "\" fill=\"none\" stroke=\"#000000\"/>\n");
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
