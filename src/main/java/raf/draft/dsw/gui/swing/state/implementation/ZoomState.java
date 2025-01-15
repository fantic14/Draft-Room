package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.state.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ZoomState implements State {
    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {
        if(e.getWheelRotation()<0){
            roomView.setZoomFactor(1.1*roomView.getZoomFactor());
            roomView.repaint();
        }
        if(e.getWheelRotation()>0){
            roomView.setZoomFactor(roomView.getZoomFactor()/1.1);
            roomView.repaint();
        }
    }
}
