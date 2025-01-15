package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.AboutUsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractRoomAction {
    public AboutUsAction () {
        putValue(SMALL_ICON, loadIcon("/images/aboutus.png"));
        putValue(NAME, "About Us");
        putValue(SHORT_DESCRIPTION, "About Us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame aboutUsFrame = AboutUsFrame.getInstance();
        aboutUsFrame.setVisible(true);
    }

}