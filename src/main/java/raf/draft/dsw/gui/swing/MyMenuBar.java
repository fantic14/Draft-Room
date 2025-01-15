package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar(MainFrame mf){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(mf.getActionManager().getExitAction());

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(mf.getActionManager().getNewProjectAction());
        editMenu.add(mf.getActionManager().getRemoveProjectAction());
        editMenu.add(mf.getActionManager().getRenameNodeAction());
        editMenu.add(mf.getActionManager().getUndoAction());
        editMenu.add(mf.getActionManager().getDoAction());
        editMenu.add(mf.getActionManager().getAuthorNamePathAction());

        JMenu infoMenu = new JMenu("Info");
        infoMenu.add(mf.getActionManager().getAboutUsAction());
        add(fileMenu);
        add(editMenu);
        add(infoMenu);
    }
}
