package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class MoveAction extends AbstractRoomAction {

    public MoveAction() {
        putValue(SMALL_ICON, loadIcon("/images/moveState.png"));
        putValue(NAME, "Move element");
        putValue(SHORT_DESCRIPTION, "Move element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startMoveState();
    }
}
