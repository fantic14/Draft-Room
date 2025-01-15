package raf.draft.dsw.gui.swing.commands.implementation;

import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;

public class ResizeCommand implements AbstractCommand {

    private RoomElement element;
    private Room room;
    private int[] widthCm;
    private int[] heightCm;
    private double[] widthRatio;
    private double[] heightRatio;

    public ResizeCommand(RoomElement element, Room room, int[] widthCm, int[] heightCm, double[] widthRatio, double[] heightRatio) {
        this.element = element;
        this.room = room;
        this.widthCm = widthCm;
        this.heightCm = heightCm;
        this.widthRatio = widthRatio;
        this.heightRatio = heightRatio;
    }

    @Override
    public void doCommand() {
        element.setWidth(widthCm[1]);
        element.setHeight(heightCm[1]);
        element.setRatio(widthRatio[1], heightRatio[1]);
        room.getRoomView().refreshView();
    }

    @Override
    public void undoCommand() {
        element.setWidth(widthCm[0]);
        element.setHeight(heightCm[0]);
        element.setRatio(widthRatio[0], heightRatio[0]);
        room.getRoomView().refreshView();
    }
}
