import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.io.*;


class Controller implements ActionListener{
    private Viewer viewer;
    private boolean hasChanges = false;
    private boolean isWrap = false;
    private String fileName;
    private String copyText;
    private File file; //File name
    private JFileChooser fileChooser = new JFileChooser();

    Controller(Viewer viewer){
        this.viewer = viewer;
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if(command.equals("Exit")){
            exit();
        } else if(command.equals("New")) {
            newFile();
        }else if(command.equals("Open")) {
            openFile();
        }else if(command.equals("Save")) {
            saveFile();
        }else if(command.equals("SaveAs")) {
            saveAsFile();
        }else if(command.equals("wordwrap")){
            wordwrap();
        }else if(command.equals("Copy")){
            copy();
        }else if(command.equals("Cut")){
            cut();
        }else if(command.equals("Past")){
            past();
        }else if(command.equals("Select")){
            selectAll();
        }else if(command.equals("Delete")){
            delete();
        }else if(command.equals("Undo")){
            undo();
        }else if(command.equals("Help")){
            help();
        }else if(command.equals("About")){
            about();
        }

//        else if(command.equals("Print")) {
//            printFile();
//        }
        else if(command.equals("PrintD")){
            printDevCit();
        }
    }



    public void selectAll(){
        viewer.textArea.selectAll();

    }

    public void help(){
        viewer.helpWindow.setVisible(true);

    }

    public void about(){
        viewer.aboutWindow.setVisible(true);
    }

    public void undo(){

    }

    public void copy(){
        copyText = viewer.textArea.getSelectedText();
    }

    public void cut(){
        copyText = viewer.textArea.getSelectedText();
        int start = viewer.textArea.getSelectionStart();
        int end = viewer.textArea.getSelectionEnd();
        viewer.textArea.replaceSelection("");
    }

    public void delete(){
        viewer.textArea.replaceSelection("");
    }

    public void past(){
        if(!copyText.equals("")){
            viewer.textArea.insert(copyText, viewer.textArea.getCaretPosition());
        }

    }

    public void wordwrap(){

        if(!isWrap){
            isWrap = true;
            viewer.textArea.setLineWrap(true);
            viewer.textArea.setWrapStyleWord(true);
        }else{
            isWrap = false;
            viewer.textArea.setLineWrap(false);
            viewer.textArea.setWrapStyleWord(false);
        }

    }

    public void newFile(){
//         проверка на внесение изменений в текущем файле
//        if (hasChanges){
//            System.out.println("Файл был изменен!");
//        }
//        System.out.println(hasChanges);
        update("");

    }

    public void openFile(){ //open file
        // проверка на внесение изменений в текущем файле

        String text = "";
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files only", "txt");
        fileChooser.setFileFilter(filter);
        int ret = fileChooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            fileName = fileChooser.getSelectedFile().getName();
            FileInputStream inF;
            try{

                inF = new FileInputStream(file);
                BufferedReader in = new BufferedReader(new InputStreamReader(inF, "UTF8"));
                int words;
                while ((words = in.read()) != -1){
                    text = text + (char)words;
                }
                update(text);

            }catch(IOException e){
                System.out.println("Error");
            }
        }
    }

    public void saveFile() { // Save opened file

        try{
            FileWriter fw = new FileWriter(file);
            fw.write(sendText()); // + .toString()
            fw.close();

        }catch(IOException e){
            // Окно с ошибкой доступа к файлу (not work)
            saveAsFile();
            System.out.println("Error");

        }catch(NullPointerException e){
            // Save new document
            saveAsFile();
            System.out.println("Error");
        }

    }

    public void saveAsFile(){ // Save As
        String text = "test тест";
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files only", "txt");
        fileChooser.setFileFilter(filter);
        int retrival = fileChooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION){
            try{
                FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
                fw.write(sendText().toString());
                fw.close();

            }catch(IOException e){
                System.out.println("Error");
            }
        }

    }

    public void printDevCit(){
        int xStart = 50;
        int yStart = 50;
        int xEnd = 50;
        int yEnd = 100;
        String textToPrint = viewer.textArea.getText();

//        PrintDocument printDocs = new PrintDocument(xStart, yStart, xEnd, yEnd, textToPrint);
//        printDocs.doAction();

        PrintOther newPrintDocs = new PrintOther(xStart, yStart, xEnd, yEnd, textToPrint, fileName);
        newPrintDocs.doAction();


    }

//    public void printFile(){
//        try{
//            boolean complite = viewer.textArea.print();
//            if(complite){
//                JOptionPane.showMessageDialog(null, "Done printing", "Information", JOptionPane.INFORMATION_MESSAGE);
//            } else{
//                JOptionPane.showMessageDialog(null, "Printing", "Printer", JOptionPane.ERROR_MESSAGE);
//            }
//
//        }catch(PrinterException e){
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }

    public void exit(){
        System.out.println("GoodBye!");
        System.exit(1);
    }


    public void update(String text){ //Changes in file
        hasChanges = true;
        viewer.textArea.setText(text);
    }

    public String sendText(){ // Send text to save action
        String text;
        text = viewer.textArea.getText();
        return text;
    }





}