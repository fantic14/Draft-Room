package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.model.structures.RoomElement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CopyState implements State {


    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        Point clickPoint = e.getPoint();
        roomView.getCopiedElements().clear();
        for (Painter element : roomView.getPainters()) {
            if (element.isSelected()){
                roomView.getCopiedElements().add(element.getElement());
            } else if (element.getShape().contains(clickPoint)){
                roomView.getCopiedElements().add(element.getElement());
            }
        }
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }


}
