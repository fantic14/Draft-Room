package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class ResizeAction extends AbstractRoomAction {

    public ResizeAction() {
        putValue(SMALL_ICON, loadIcon("/images/resizeState.png"));
        putValue(NAME, "Resize element");
        putValue(SHORT_DESCRIPTION, "Resize element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startResizeState();
    }
}
