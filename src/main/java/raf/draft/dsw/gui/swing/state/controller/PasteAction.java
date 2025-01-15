package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class PasteAction extends AbstractRoomAction {

    public PasteAction() {
        putValue(SMALL_ICON, loadIcon("/images/pasteState.png"));
        putValue(NAME, "Paste");
        putValue(SHORT_DESCRIPTION, "Paste");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startPasteState();
    }
}
