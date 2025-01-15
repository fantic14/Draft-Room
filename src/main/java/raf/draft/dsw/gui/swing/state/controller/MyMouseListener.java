package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;

import java.awt.event.*;

public class MyMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    private RoomView roomView;

    public MyMouseListener(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getStateManager().getCurrentState().myMousePressed(e, this.roomView);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getStateManager().getCurrentState().myMouseReleased(e, this.roomView);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getStateManager().getCurrentState().myMouseDragged(e, this.roomView);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MainFrame.getInstance().getProjectView().getStateManager().getCurrentState().myMouseScrolled(e, this.roomView);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
