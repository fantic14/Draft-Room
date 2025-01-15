package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class EditElementAction extends AbstractRoomAction {

    public EditElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/editElementState.png"));
        putValue(NAME, "Edit element");
        putValue(SHORT_DESCRIPTION, "Edit element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startEditElementState();
    }
}
