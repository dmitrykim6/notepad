import javax.swing.text.View;
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
            System.out.println("GoodBye!");
            System.exit(1);
        } else if(command.equals("New")) {
            viewer.update("");
        }else if(command.equals("Open")) {
            viewer.chooseFile();
        }
    }


}