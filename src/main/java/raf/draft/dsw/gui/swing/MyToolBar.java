package raf.draft.dsw.gui.swing;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(MainFrame mf){
        super(HORIZONTAL);
        setFloatable(false);
        add(mf.getActionManager().getNewProjectAction());
        addSeparator();
        add(mf.getActionManager().getRemoveProjectAction());
        addSeparator();
        add(mf.getActionManager().getRenameNodeAction());
        addSeparator();
        add(mf.getActionManager().getUndoAction());
        addSeparator();
        add(mf.getActionManager().getDoAction());
        addSeparator();
        add(mf.getActionManager().getAuthorNamePathAction());
        addSeparator();
        add(mf.getActionManager().getAboutUsAction());
        addSeparator();
        add(mf.getActionManager().getExitAction());
        add(Box.createGlue());
        add(mf.getActionManager().getShuffleOrganisedRoomAction());
        addSeparator();
        add(mf.getActionManager().getOrganiseMyRoomAction());
    }
}
