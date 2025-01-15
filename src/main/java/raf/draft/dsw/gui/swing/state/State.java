package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.gui.swing.RoomView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State{

    void myMousePressed(MouseEvent e, RoomView roomView);
    void myMouseReleased(MouseEvent e, RoomView roomView);
    void myMouseDragged(MouseEvent e, RoomView roomView);
    void myMouseScrolled(MouseWheelEvent e, RoomView roomView);

}
