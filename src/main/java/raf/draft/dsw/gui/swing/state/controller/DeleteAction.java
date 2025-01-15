package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractRoomAction {

    public DeleteAction() {
        putValue(SMALL_ICON, loadIcon("/images/deleteState.png"));
        putValue(NAME, "Delete element");
        putValue(SHORT_DESCRIPTION, "Delete element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startDeleteState();
    }
}
