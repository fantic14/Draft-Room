package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class EditRoomState implements State {


    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        if (roomView.getRoom().getChildren().isEmpty()){
            String widthInput = JOptionPane.showInputDialog("Type in a room width (cm)");
            if (widthInput == null)
                return;
            String heightInput = JOptionPane.showInputDialog("Type in a room height (cm)");
            if (heightInput == null)
                return;
            if (widthInput.isBlank() || heightInput.isBlank()) {
                MessageGenerator mg = new MessageGenerator();
                mg.generateMessage("CANNOT_CHANGE_ROOM_TO_BLANK_DIMENSIONS", MessageType.WARNING);
                return;
            }

            try {
                Double width = Double.valueOf(widthInput);
                Double height = Double.valueOf(heightInput);
                roomView.getRoom().setWidthCm(width);
                roomView.getRoom().setHeightCm(height);
            } catch (NumberFormatException exception) {
                MessageGenerator mg = new MessageGenerator();
                mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
            }
        } else {
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_CHANGE_DIMENSIONS_WHILE_ELEMENTS_ARE_IN_ROOM", MessageType.WARNING);
        }
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }
}
