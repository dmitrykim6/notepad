import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



class Controller implements ActionListener{
    Viewer viewer;

    Controller(Viewer viewer){
        this.viewer = viewer;
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if(command.equals("Exit")){
            viewer.exit();
        } else if(command.equals("New")) {
            viewer.newFile();
        }else if(command.equals("Open")) {
            viewer.openFile();
        }else if(command.equals("Save")) {
            viewer.saveFile();
        }else if(command.equals("SaveAs")) {
            viewer.saveAsFile();
        }else if(command.equals("Print")) {
            viewer.printFile();
        }
    }

}