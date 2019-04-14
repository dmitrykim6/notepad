import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.security.Key;


class Viewer {

    JTextArea textArea;

    Viewer(){
        Controller controller = new Controller (this);
        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);

        JMenuBar jMenuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");

        ImageIcon createMenuItemIcon = new ImageIcon("icons/new.png");
        JMenuItem createMenuItem = new JMenuItem("New", createMenuItemIcon);
        createMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        createMenuItem.addActionListener(controller);
        createMenuItem.setActionCommand("New");



        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openMenuItem.addActionListener(controller);
        openMenuItem.setActionCommand("Open");


        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveMenuItem.addActionListener(controller);
        saveMenuItem.setActionCommand("Save");


        JMenuItem saveAsMenuItem = new JMenuItem("Save As ...");
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ InputEvent.ALT_MASK));
        saveAsMenuItem.addActionListener(controller);
        saveAsMenuItem.setActionCommand("SaveAs");

        JMenuItem pageSetupMenuItem = new JMenuItem("Page Setup ...");


        JMenuItem printMenuItem = new JMenuItem("Print ...");
        printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printMenuItem.addActionListener(controller);
        printMenuItem.setActionCommand("Print");

        JMenuItem closeMenuItem = new JMenuItem("Exit");
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        closeMenuItem.addActionListener(controller);
        closeMenuItem.setActionCommand("Exit");


        menuFile.add(createMenuItem);
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(saveAsMenuItem);
        menuFile.add(new JSeparator());
        menuFile.add(pageSetupMenuItem);
        menuFile.add(printMenuItem);
        menuFile.add(new JSeparator());
        menuFile.add(closeMenuItem);

        JMenu menuEdit = new JMenu("Edit");

        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoMenuItem.addActionListener(controller);
        undoMenuItem.setActionCommand("Undo");

//        JMenuItem redoMenuItem = new JMenuItem("Redo");
//        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK+ InputEvent.ALT_MASK));
//        closeMenuItem.addActionListener(controller);
//        closeMenuItem.setActionCommand("Redo");

        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutMenuItem.addActionListener(controller);
        cutMenuItem.setActionCommand("Cut");

        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyMenuItem.addActionListener(controller);
        copyMenuItem.setActionCommand("Copy");

        JMenuItem pastMenuItem = new JMenuItem("Past");
        pastMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pastMenuItem.addActionListener(controller);
        pastMenuItem.setActionCommand("Past");

        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        deleteMenuItem.addActionListener(controller);
        deleteMenuItem.setActionCommand("Delete");

        JMenuItem findMenuItem = new JMenuItem("Find");
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        findMenuItem.addActionListener(controller);
        findMenuItem.setActionCommand("Find");

        JMenuItem findNextMenuItem = new JMenuItem("Find Next");
        findNextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        findNextMenuItem.addActionListener(controller);
        findNextMenuItem.setActionCommand("FindNext");

        JMenuItem replaceMenuItem = new JMenuItem("Replace ...");
        replaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        replaceMenuItem.addActionListener(controller);
        replaceMenuItem.setActionCommand("Replace");

        JMenuItem goToMenuItem = new JMenuItem("Go To...");
        goToMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        goToMenuItem.addActionListener(controller);
        goToMenuItem.setActionCommand("GoTo");

        JMenuItem selectAllMenuItem = new JMenuItem("Select All");
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAllMenuItem.addActionListener(controller);
        selectAllMenuItem.setActionCommand("Select");

        JMenuItem timeDateMenuItem = new JMenuItem("Time/Date");
        timeDateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        timeDateMenuItem.addActionListener(controller);
        timeDateMenuItem.setActionCommand("Time/Date");


        menuEdit.add(undoMenuItem);
        menuEdit.add(new JSeparator());
        menuEdit.add(cutMenuItem);
        menuEdit.add(copyMenuItem);
        menuEdit.add(pastMenuItem);
        menuEdit.add(deleteMenuItem);
        menuEdit.add(new JSeparator());
        menuEdit.add(findMenuItem);
        menuEdit.add(findNextMenuItem);
        menuEdit.add(replaceMenuItem);
        menuEdit.add(goToMenuItem);
        menuEdit.add(new JSeparator());
        menuEdit.add(selectAllMenuItem);
        menuEdit.add(timeDateMenuItem);

        JMenu menuFormat = new JMenu("Format");

        JMenuItem wordWrapMenuItem = new JMenuItem("Word wrap");
        JMenuItem fontMenuItem = new JMenuItem("Font");
        menuFormat.add(wordWrapMenuItem);
        menuFormat.add(fontMenuItem);


        JMenu menuView = new JMenu("View");

        JMenuItem statusBarMenuItem = new JMenuItem("Status Bar");
        menuView.add(statusBarMenuItem);

        JMenu menuHelp = new JMenu("Help");

        JMenuItem viewHelpMenuItem = new JMenuItem("View help");
        JMenuItem aboutTheProgramMenuItem = new JMenuItem("About the program");
        menuHelp.add(viewHelpMenuItem);
        menuHelp.add(new JSeparator());
        menuHelp.add(aboutTheProgramMenuItem);


        jMenuBar.add(menuFile);
        jMenuBar.add(menuEdit);
        jMenuBar.add(menuFormat);
        jMenuBar.add(menuView);
        jMenuBar.add(menuHelp);


        JFrame frame = new JFrame("Notepad AUCA");
        frame.setSize(500, 500);
        frame.setLocation(100,100);
        frame.add("Center", scroll);
        frame.setJMenuBar(jMenuBar);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program after exit

    }
    public void update(String text){
        textArea.setText(text);

    }
}