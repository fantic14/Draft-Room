package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractRoomAction{

    public UndoAction() {
        putValue(SMALL_ICON, loadIcon("/images/undoCommand.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().undoCommand();
    }

    public void enableDisable(boolean b){
        this.setEnabled(b);
    }
}
