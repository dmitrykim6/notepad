import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintDocument implements Printable {
    private PrinterJob job;
    private String textToPrint;
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private int start = 0;
    private int currentPage = -1;
    int[] pageBreaks;
    String[] textLines;

    public PrintDocument(int xStart, int yStart, int xEnd, int yEnd, String textToPrint){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.textToPrint = textToPrint;

        job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

    }

    public int print(Graphics g, PageFormat pf, int page) {

        if (page > 9) {
            return NO_SUCH_PAGE;
        }

        buildToPrint(g, pf);

        return PAGE_EXISTS;
    }



    void buildToPrint(Graphics g, PageFormat pf){
        //1. Вычисляем высоту строки
        //2. Вычисляем длину строки
        //3. Делаем переносы строк
        //4. Определяем высоту всего документа
        //5. Определяем количество страниц
        //6. Вычисляем общее количество строк
        //7. Наполняем массив строками
        //8. Передаем на печать

        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int x = (int)pf.getImageableX() + xStart;
        int y = (int)pf.getImageableY() + yStart;
        int widthPage = (int)pf.getImageableWidth() - xStart - xEnd; // ширина страницы
        int heightPage = (int)pf.getImageableHeight() - yEnd; // высота страницы

        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight(); // высота строки

        int countSymbol = 0;
        int countAllLines = 0;
        String newStr = "";

        int linesPerPage = (int)(pf.getImageableY()/lineHeight); // Количество строк на странице

        // Считаем общее количество строк
        for(int i = 0; i < textToPrint.length(); i++) {
            char symbol = textToPrint.charAt(i);
            int widthAnySymbol = metrics.charWidth(symbol); // ширина символа

            if (symbol == '\n' || widthPage < countSymbol) {
                countAllLines++;
            }
        }

        textLines = new String [countAllLines];// Массив строк

        int numBreaks = (textLines.length-1)/linesPerPage; // Количество страниц
        pageBreaks = new int[numBreaks]; // массив страниц




//        for(int i = 0; i < textToPrint.length(); i++) {
//        char symbol = textToPrint.charAt(i);
//        int widthAnySymbol = metrics.charWidth(symbol); // ширина символа
//            if(symbol == '\n') {
//                y = y + lineHeight;
//                g2d.drawString(newStr, x, y);
//                newStr = "";
//                countSymbol = 0;
//
//            } else if(widthPage < countSymbol){
//                y = y + lineHeight;
//                g2d.drawString(newStr, x, y);
//                i--;
//                newStr = "";
//                countSymbol = 0;
//            } else {
//                countSymbol = countSymbol + widthAnySymbol;
//                newStr = newStr + symbol;
//            }
//
//            if(y > heightPage) {
//
//                break;
//            }
//        }

    }


    public void doAction() {
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