package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.gui.swing.state.controller.AddRoomElements.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class AddState implements State {

    private String currentElement;

    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        switch (currentElement){
            case "bed":
                new AddBedAction(roomView);
                break;
            case "boiler":
                new AddBoilerAction(roomView);
                break;
            case "closet":
                new AddClosetAction(roomView);
                break;
            case "door":
                new AddDoorAction(roomView);
                break;
            case "sink":
                new AddSinkAction(roomView);
                break;
            case "table":
                new AddTableAction(roomView);
                break;
            case "toilet":
                new AddToiletAction(roomView);
                break;
            case "tub":
                new AddTubAction(roomView);
                break;
            case "washing machine":
                new AddWashingMachineAction(roomView);
                break;
            default:
                break;
        }
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }

    public void setCurrentElement(String element) {
        this.currentElement = element;
    }
}
