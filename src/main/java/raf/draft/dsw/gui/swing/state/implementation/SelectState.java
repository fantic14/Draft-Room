package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.gui.swing.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;

public class SelectState implements State {

    private Rectangle2D selectionRectangle;
    private Point startPoint;
    private boolean dragging = false;

    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {
        startPoint = e.getPoint();
        selectionRectangle = new Rectangle2D.Double();
        
        roomView.refreshView();
    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        if (dragging) {
            dragging = false;
            if (selectionRectangle != null) {
                for (Painter element : roomView.getPainters()) {
                    if (element.getShape().intersects(selectionRectangle)) {
                        element.setSelected(true, roomView);
                    } else {
                        element.setSelected(false, roomView);
                    }
                }
            }
        } else {
            boolean foundSelection = false;

            for (Painter element : roomView.getPainters()) {
                if (element.getShape().contains(startPoint)) {
                    element.setSelected(true, roomView);
                    foundSelection = true;
                } else {
                    element.setSelected(false, roomView);
                }
            }

            if (!foundSelection) {
                for (Painter element : roomView.getPainters()) {
                    element.setSelected(false, roomView);
                }
            }
        }
        selectionRectangle = null;
        roomView.refreshView();
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {
        dragging = true;
        if (startPoint != null) {
            Point endPoint = e.getPoint();

            selectionRectangle = new Rectangle2D.Double(
                    Math.min(startPoint.x, endPoint.x),
                    Math.min(startPoint.y, endPoint.y),
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y)
            );
            selectionRectangle.setFrame(
                    Math.min(startPoint.x, endPoint.x),
                    Math.min(startPoint.y, endPoint.y),
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y)
            );

            roomView.refreshView();
        }
    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }
}
