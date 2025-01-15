package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class EditRoomAction extends AbstractRoomAction {

    public EditRoomAction() {
        putValue(SMALL_ICON, loadIcon("/images/editRoomState.png"));
        putValue(NAME, "Edit room");
        putValue(SHORT_DESCRIPTION, "Edit room");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startEditRoomState();
    }
}
