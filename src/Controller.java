import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.io.*;


class Controller implements ActionListener{
    Viewer viewer;
    boolean hasChanges = false;
    JFileChooser fileChooser = new JFileChooser();;
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
        }else if(command.equals("Print")) {
            printFile();
        }
    }

    File file; //File name

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

        String text = null;
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files only", "txt");
        fileChooser.setFileFilter(filter);
        int ret = fileChooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
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
            fw.write(sendText().toString());
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

    public void printFile(){
        try{

            boolean complite = viewer.textArea.print();
            if(complite){
                JOptionPane.showMessageDialog(null, "Done printing", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null, "Printing", "Printer", JOptionPane.ERROR_MESSAGE);
            }

        }catch(PrinterException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

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