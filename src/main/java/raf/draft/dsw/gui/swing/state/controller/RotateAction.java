package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class RotateAction extends AbstractRoomAction {

    public RotateAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateState.png"));
        putValue(NAME, "Rotate element");
        putValue(SHORT_DESCRIPTION, "Rotate element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startRotateState();
    }
}
