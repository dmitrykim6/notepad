import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintOther implements Printable {
    private PrinterJob job;
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private String textToPrint;
    String[] textLines;
    int[] pageBreaks;
    int pageNumber;
    String pageNumberText;
    String fileName;
    boolean firstPage;

    StringBuffer textToPrintBuff;

    public PrintOther(int xStart, int yStart, int xEnd, int yEnd, String textToPrint, String fileName){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.textToPrint = textToPrint;
        this.fileName = fileName;
        pageNumber = 0;
        pageNumberText =  "Page ";
        firstPage = true;


        job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
    }

    private void initTextLines(FontMetrics metrics, int widthPage, int linesPerPage){

        // Подготавливаем массив для печати
        char symbol;
        int widthAnySymbol;
        int countSymbols = 0;
        int spacePosition = 0;

        textToPrintBuff = new StringBuffer(textToPrint);

        for (int i = 0; i < textToPrintBuff.length(); i ++){
            symbol = textToPrintBuff.charAt(i);
            widthAnySymbol = metrics.charWidth(symbol);

            //         ищем близжайший слева пробел (v1)
            if (symbol == ' '){
                spacePosition = i;
            }

            if(countSymbols > widthPage){

 //         ищем близжайший слева пробел (v2)
//                for(int j = 0; j < 30; j++){
//                    if(textToPrintBuff.charAt(i - j) == ' '){
//                        textToPrintBuff.insert((i - j), "\n");
//                        i = i - j;
//                        break;
//                    }
//                }


                textToPrintBuff.insert((spacePosition), "\n");
                i = spacePosition;

                countSymbols = 0;
            }
            countSymbols = countSymbols+ widthAnySymbol;
        }
        textLines = textToPrintBuff.toString().split("\n");
    }

    public int print(Graphics g, PageFormat pf, int PageIndex){


        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int x = (int)pf.getImageableX() + xStart;
        int y = (int)pf.getImageableY() + yStart;
        int widthPage = (int)pf.getImageableWidth() - xStart - xEnd; // ширина страницы
        int heightPage = (int)pf.getImageableHeight() - yEnd; // высота страницы

        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight();

        int linesPerPage = heightPage/lineHeight;

        if (pageBreaks == null) {

            initTextLines(metrics, widthPage, linesPerPage);

            int numBreaks = (textLines.length-1)/linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage;
            }
        }

        if (PageIndex > pageBreaks.length) {

            return NO_SUCH_PAGE;
        }

//        int start = (PageIndex == 0) ? 0 : pageBreaks[PageIndex-1];
        int start;
        if (PageIndex == 0){
            start = 0;
        }else {
            start = pageBreaks[PageIndex-1];
        }

//        int end   = (PageIndex == pageBreaks.length) ? textLines.length : pageBreaks[PageIndex];
        int end;
        if (PageIndex == pageBreaks.length){
            end = textLines.length;
        }else {
            end = pageBreaks[PageIndex];
        }

        //Верхний колонтитул
        g.drawString(fileName, widthPage / 2 - fileName.length() / 2, 30);

        //печать документа
        for (int line = start; line < end; line++) {
            y += lineHeight;
            g.drawString(textLines[line], x, y);
        }

        //Нижний колонтитул
        pageNumber++;
        g.drawString(pageNumberText + pageNumber/2, widthPage / 2 - pageNumberText.length() / 2, y + 20);

        return PAGE_EXISTS;
    }


    public void doAction(){
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




