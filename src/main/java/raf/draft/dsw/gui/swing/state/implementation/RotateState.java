package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.commands.implementation.RotateCommand;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class RotateState implements State {
    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        Point clickPoint = e.getPoint();

        for (int i = 0; i < roomView.getPainters().size(); i++){
            Painter element = roomView.getPainters().get(i);
            if (element.getShape().contains(clickPoint)){
                Integer[] options = {0, 90, 180, 270};
                Object selectionObject = JOptionPane.showInputDialog(roomView, "Choose", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                int selectionNumbers;
                if (selectionObject != null) {
                    selectionNumbers = (Integer) selectionObject;
                    if (selectionNumbers == 0 || selectionNumbers == 180){
                        if (element.getElement().getWidth() > roomView.getRoom().getWidthCm() ||
                        element.getElement().getHeight() > roomView.getRoom().getHeightCm()){
                            MessageGenerator mg = new MessageGenerator();
                            mg.generateMessage("CANNOT_ROTATE_ELEMENT_WILL_NOT_FIT", MessageType.WARNING);
                            return;
                        }
                    } else if (selectionNumbers == 90 || selectionNumbers == 270){
                        if (element.getElement().getWidth() > roomView.getRoom().getHeightCm() ||
                                element.getElement().getHeight() > roomView.getRoom().getWidthCm()){
                            MessageGenerator mg = new MessageGenerator();
                            mg.generateMessage("CANNOT_ROTATE_ELEMENT_WILL_NOT_FIT", MessageType.WARNING);
                            return;
                        }
                    }
                    AbstractCommand command = new RotateCommand(roomView, element.getElement(), selectionNumbers);
                    MainFrame.getInstance().getCommandManager().addCommand(command);
                }
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
