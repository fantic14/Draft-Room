package raf.draft.dsw.gui.swing.commands.implementation;

import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;

import java.util.*;

public class MoveCommand implements AbstractCommand {

    private List<RoomElement> elements;
    private Room room;
    private Map<RoomElement, int[]> originalCoordinates;
    private Map<RoomElement, int[]> newCoordinates;

    public MoveCommand(List<Painter> elements, Room room, Map<Painter, int[]> originalCoordinates) {
        this.elements = new ArrayList<>();
        for (Painter element : elements) {
            this.elements.add(element.getElement());
        }
        this.room = room;
        this.originalCoordinates = new HashMap<>();
        for (Painter painter : originalCoordinates.keySet()) {
            this.originalCoordinates.put(painter.getElement(), originalCoordinates.get(painter));
        }
        this.newCoordinates = new HashMap<>();
        for (RoomElement element : this.elements) {
            newCoordinates.put(element, new int[]{element.getX(), element.getY()});
        }
    }

    @Override
    public void doCommand() {
        for (RoomElement element : elements) {
            int[] newCoordinatesForElement = newCoordinates.get(element);
            element.setX(newCoordinatesForElement[0]);
            element.setY(newCoordinatesForElement[1]);
        }
        room.getRoomView().refreshView();
    }

    @Override
    public void undoCommand() {
        for (RoomElement element : elements) {
            int[] originalCoordinatesForElement = originalCoordinates.get(element);
            element.setX(originalCoordinatesForElement[0]);
            element.setY(originalCoordinatesForElement[1]);
        }
        room.getRoomView().refreshView();
    }
}
