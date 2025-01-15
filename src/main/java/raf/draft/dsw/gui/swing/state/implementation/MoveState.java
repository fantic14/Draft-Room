package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.commands.implementation.MoveCommand;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.gui.swing.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveState implements State {

    int lastMouseX;
    int lastMouseY;
    boolean overAnother;
    boolean leastOneSelected = false;

    private List<Painter> movingPainters = new ArrayList<>();
    private Map<Painter, int[]> originalLocations = new HashMap<>();

    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {
       Point clickPoint = e.getPoint();

        for (Painter painter : roomView.getPainters()) {
            if (painter.elementAt(clickPoint) && !movingPainters.contains(painter)) {
                this.movingPainters.add(painter);
                originalLocations.put(painter, new int[]{painter.getX(), painter.getY()});
                this.leastOneSelected = true;
            }
            if (painter.isSelected() && !movingPainters.contains(painter)) {
                this.movingPainters.add(painter);
                originalLocations.put(painter, new int[]{painter.getX(), painter.getY()});
            }
        }
        lastMouseX = e.getX();
        lastMouseY = e.getY();
    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        if (overAnother){
            for (Painter movingPainter : movingPainters) {
                int[] originalCoordinates = originalLocations.get(movingPainter);
                movingPainter.setX(originalCoordinates[0]);
                movingPainter.setY(originalCoordinates[1]);
            }
            roomView.refreshView();
        }
        AbstractCommand command = new MoveCommand(movingPainters, roomView.getRoom(), originalLocations);
        MainFrame.getInstance().getCommandManager().addCommand(command);
        this.movingPainters.clear();
        this.leastOneSelected = false;
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {
        int leftRightRoom = (roomView.getWidth() - roomView.getRealRoomWidthInPx())/2;
        int topBottomRoom = (roomView.getHeight() - roomView.getRealRoomHeightInPx())/2;
        if (!this.movingPainters.isEmpty()) {
            int dx = e.getX() - lastMouseX;
            int dy = e.getY() - lastMouseY;
            for (Painter movingPainter : movingPainters) {
                double pixelsPerCmWidth = roomView.getRealRoomWidthInPx() / roomView.getRoom().getWidthCm();
                double pixelsPerCmHeight = roomView.getRealRoomHeightInPx() / roomView.getRoom().getHeightCm();
                movingPainter.setX(movingPainter.getX() + dx);
                movingPainter.setY(movingPainter.getY() + dy);
                if (movingPainter.getElement().getRotateRatio() == 0 || movingPainter.getElement().getRotateRatio() == 2) {
                    if (movingPainter.getX() <= leftRightRoom + 5) {
                        movingPainter.setX(leftRightRoom);
                    }
                    if (movingPainter.getX() + (int) (movingPainter.getElement().getWidth() * pixelsPerCmWidth) >= roomView.getWidth() - leftRightRoom - 5) {
                        movingPainter.setX(roomView.getWidth() - leftRightRoom - (int) (movingPainter.getElement().getWidth() * pixelsPerCmWidth));
                    }
                    if (movingPainter.getY() < topBottomRoom + 5) {
                        movingPainter.setY(topBottomRoom);
                    }
                    if (movingPainter.getY() + (int) (movingPainter.getElement().getHeight() * pixelsPerCmHeight) >= roomView.getHeight() - topBottomRoom - 5) {
                        movingPainter.setY(roomView.getHeight() - topBottomRoom - (int) (movingPainter.getElement().getHeight() * pixelsPerCmHeight));
                    }
                } else {
                    if (movingPainter.getX() <= leftRightRoom + 5) {
                        movingPainter.setX(leftRightRoom);
                    }
                    if (movingPainter.getX() + (int) (movingPainter.getElement().getHeight() * pixelsPerCmHeight) >= roomView.getWidth() - leftRightRoom - 5) {
                        movingPainter.setX(roomView.getWidth() - leftRightRoom - (int) (movingPainter.getElement().getHeight() * pixelsPerCmHeight));
                    }
                    if (movingPainter.getY() < topBottomRoom + 5) {
                        movingPainter.setY(topBottomRoom);
                    }
                    if (movingPainter.getY() + (int) (movingPainter.getElement().getWidth() * pixelsPerCmWidth) >= roomView.getHeight() - topBottomRoom - 5) {
                        movingPainter.setY(roomView.getHeight() - topBottomRoom - (int) (movingPainter.getElement().getWidth() * pixelsPerCmWidth));
                    }
                }
                overAnother = movingPainter.elementAt(roomView.getPainters());
                //movingPainter.getElement().setElementTreeItem(movingPainter.getElement().getElementTreeItem());
            }
            lastMouseX = e.getX();
            lastMouseY = e.getY();
            roomView.refreshView();
        } else {
            JComponent parent = (JComponent) roomView.getParent();
            Point point = e.getPoint();

            if (parent.getLayout() != null) {
                parent.setLayout(null);
            }

            Point location = roomView.getLocation();
            int newX = location.x + point.x - roomView.getWidth() / 2;
            int newY = location.y + point.y - roomView.getHeight() / 2;

            newX = Math.max(-leftRightRoom, Math.min(newX, roomView.getWidth() - roomView.getRealRoomWidthInPx() - leftRightRoom));
            newY = Math.max(-topBottomRoom, Math.min(newY, roomView.getHeight() - roomView.getRealRoomHeightInPx() - topBottomRoom));

            roomView.setLocation(newX, newY);
            parent.repaint();
        }
    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }
}
