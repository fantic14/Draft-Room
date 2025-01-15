package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class DoAction extends AbstractRoomAction{

    public DoAction() {
        putValue(SMALL_ICON, loadIcon("/images/doCommand.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().doCommand();
    }

    public void enableDisable(boolean b){
        this.setEnabled(b);
    }
}
