package print;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.io.File;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;


public class PrintDocument implements Printable {
    private PrinterJob job;
    private Image img;

    PrintDocument(){

        File file = new File("src/calc.png");
        try{
            img = ImageIO.read(file);
        }catch (IOException e) {
            System.out.println("Error");
        }

        System.out.println("TEST");
        job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        System.out.println("End action");
    }

    public int print(Graphics g, PageFormat pf, int page){

        if(page > 0){
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.drawString("Kim Dmitry", 100,100);
        g2d.drawImage(img, 100, 200, 150, 150, null);

        return PAGE_EXISTS;
    }

    public void doAction(){
        System.out.println("Start printing...");

        boolean ok = job.printDialog();
        if(ok){
            try{
                job.print();
            } catch (PrinterException e){
                System.out.println("File not found");
            }
        }
    }

    public static void main(String[] args) {
        PrintDocument ob = new PrintDocument();
        ob.doAction();
    }
}
