package raf.draft.dsw.gui.swing.commands.implementation;

import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.model.structures.RoomElement;

public class RotateCommand implements AbstractCommand {

    private RoomView roomView;
    private RoomElement roomElement;
    private int newRotation;
    private int oldRotation;

    public RotateCommand(RoomView roomView, RoomElement roomElement, int newRotation) {
        this.roomView = roomView;
        this.roomElement = roomElement;
        this.newRotation = newRotation;
        this.oldRotation = roomElement.getRotateRatio();
    }

    @Override
    public void doCommand() {
        if (roomElement == null || roomView == null) return;
        roomElement.setRotateRatio(newRotation/90);
        roomView.refreshView();
    }

    @Override
    public void undoCommand() {
        if (roomElement == null || roomView == null) return;
        roomElement.setRotateRatio(oldRotation/90);
        roomView.refreshView();
    }
}
