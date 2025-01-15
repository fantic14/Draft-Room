package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractRoomAction {

    public SelectAction() {
        putValue(SMALL_ICON, loadIcon("/images/selectState.png"));
        putValue(NAME, "Select element");
        putValue(SHORT_DESCRIPTION, "Select element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startSelectState();
    }
}
