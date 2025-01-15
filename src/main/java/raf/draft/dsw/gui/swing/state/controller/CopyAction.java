package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class CopyAction extends AbstractRoomAction {

    public CopyAction() {
        putValue(SMALL_ICON, loadIcon("/images/copyState.png"));
        putValue(NAME, "Copy");
        putValue(SHORT_DESCRIPTION, "Copy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startCopyState();
    }
}
