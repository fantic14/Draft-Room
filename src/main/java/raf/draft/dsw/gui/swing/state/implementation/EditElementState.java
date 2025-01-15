package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.commands.implementation.ResizeCommand;
import raf.draft.dsw.gui.swing.painter.*;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class EditElementState implements State {

    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        Point clickPoint = e.getPoint();

        for (Painter element : roomView.getPainters()) {
            if (element.getShape().contains(clickPoint)){
                String width = JOptionPane.showInputDialog("Enter new width (cm)");
                String height = null;
                if (!(element instanceof BoilerPainter || element instanceof WashingMachinePainter || element instanceof DoorPainter))
                    height = JOptionPane.showInputDialog("Enter new length (cm)");
                int widthCm;
                int heightCm = 0;

                try {
                    widthCm = Integer.parseInt(width);
                    if (!(element instanceof BoilerPainter || element instanceof WashingMachinePainter || element instanceof DoorPainter))
                        heightCm = Integer.parseInt(height);
                } catch (NumberFormatException exception){
                    MessageGenerator mg = new MessageGenerator();
                    mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
                    return;
                } catch (NullPointerException _){
                    return;
                }

                if (element.getElement().getRotateRatio() == 0 || element.getElement().getRotateRatio() == 2) {
                    if (widthCm > roomView.getRoom().getWidthCm() || heightCm > roomView.getRoom().getHeightCm()) {
                        MessageGenerator mg = new MessageGenerator();
                        mg.generateMessage("CANNOT_RESIZE_ELEMENT_GREATER_THAN_ROOM", MessageType.ERROR);
                        return;
                    }
                } else {
                    if (widthCm > roomView.getRoom().getHeightCm() || heightCm > roomView.getRoom().getWidthCm()) {
                        MessageGenerator mg = new MessageGenerator();
                        mg.generateMessage("CANNOT_RESIZE_ELEMENT_GREATER_THAN_ROOM", MessageType.ERROR);
                        return;
                    }
                }
                double newWidth = widthCm*3.78;
                double newHeight = heightCm*3.78;

                double newWidthRatio = newWidth / roomView.getRoom().getWidthInPixels();
                double newHeightRatio = newHeight / roomView.getRoom().getHeightInPixels();

                int[] widthCmArr = new int[]{element.getElement().getWidth(), widthCm};
                int[] heightCmArr = new int[]{element.getElement().getHeight(), heightCm};
                double[] widthRatioArr = new double[]{element.getElement().getWidthRatio(), newWidthRatio};
                double[] heightRatioArr = new double[]{element.getElement().getHeightRatio(), newHeightRatio};

                AbstractCommand command = new ResizeCommand(element.getElement(), roomView.getRoom(), widthCmArr, heightCmArr, widthRatioArr, heightRatioArr);
                MainFrame.getInstance().getCommandManager().addCommand(command);
                element.getElement().setWidth(widthCm);
                element.getElement().setHeight(heightCm);
                element.getElement().setRatio(newWidthRatio, newHeightRatio);
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
