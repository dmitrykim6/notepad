import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class NewPrint implements Printable {
    private PrinterJob job;
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private String textToPrint;
    int countAllLines;
    int[] pageBreaks;
    String[] textLines;


    public NewPrint(int xStart, int yStart, int xEnd, int yEnd, String textToPrint){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.textToPrint = textToPrint;


        job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

    }


    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int x = (int)pf.getImageableX() + xStart;
        int y = (int)pf.getImageableY() + yStart;
        int widthPage = (int)pf.getImageableWidth() - xStart - xEnd; // ширина страницы
        int heightPage = (int)pf.getImageableHeight() - yEnd; // высота страницы

        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight(); // высота строки

        if (pageBreaks == null) {

            initTextLines(metrics, widthPage);

            int linesPerPage = (int)(pf.getImageableHeight()/lineHeight);
            int numBreaks = (textLines.length-1)/linesPerPage;
            pageBreaks = new int[numBreaks];

            for (int b = 0; b < numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage;
            }

        }

        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }


        int c = 0;
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length)
                ? textLines.length : pageBreaks[pageIndex];

        for (int line = start; line < end; line++) {
            c += lineHeight;
            g.drawString(textLines[line], 0, c);
        }

        return PAGE_EXISTS;
    }

    private void initTextLines(FontMetrics metrics, int widthPage){
        System.out.println("widthPage = " + widthPage);
        countAllLines = 0;
        int countSymbol = 0;
        char symbol;
        int widthAnySymbol;
        System.out.println("countAllLines = " + countAllLines);
        for(int i = 0; i < textToPrint.length(); i++) {
            symbol = textToPrint.charAt(i);
            widthAnySymbol = metrics.charWidth(symbol) + 2;

            if (symbol == '\n') {
                countAllLines++;
                countSymbol = 0;
            }else if (countSymbol > widthPage){
                countAllLines++;
                countSymbol = 0;
                i--;
            }else {
                countSymbol = countSymbol + widthAnySymbol;
            }
        }


        System.out.println("textToPrint.length() = " + textToPrint.length());
        System.out.println("countAllLines = " + countAllLines);


//        textLines = new String[countAllLines];
//        int countToCut = 0;

//        for (int i = 0; i < countAllLines; i++){
//            for(int j = 0; j < textToPrint.length(); j++){
//                char symbol = textToPrint.charAt(j);
//                int widthAnySymbol = metrics.charWidth(symbol); // ширина символа
//
//                if(symbol == '\n'){
//                    textLines[i] = textToPrint.substring(0, j);
//                    textToPrint = textToPrint.substring(j);
////                    System.out.println("textLines[" + i + "]" + textLines[i]);
//                }else if(widthPage < countSymbol){
//                    textLines[i] = textToPrint.substring(0, j);
//                    textToPrint = textToPrint.substring(j);
////                    System.out.println("textLines[" + i + "]" + textLines[i]);
//                    j--;
//                }else{
//                    countSymbol = countSymbol + widthAnySymbol;
//                }
//            }
//        }
    }


    public void doAction() {
//        PrinterJob job = PrinterJob.getPrinterJob();
//        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }
    }
}
