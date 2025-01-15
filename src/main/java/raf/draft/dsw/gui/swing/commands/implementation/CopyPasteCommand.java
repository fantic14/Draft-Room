package raf.draft.dsw.gui.swing.commands.implementation;

import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.*;

import java.util.List;

public class CopyPasteCommand implements AbstractCommand {

    private List<RoomElement> elements;
    private Room room;

    public CopyPasteCommand(List<RoomElement> elements, Room room) {
        this.elements = elements;
        this.room = room;
    }

    @Override
    public void doCommand() {
        for (RoomElement element : elements) {
            DraftRoomChildrenRepository.getInstance().addChildToList(element);
            DraftTreeItem elementItem = new DraftTreeItem(element, room.getTreeItem());
            element.setTreeItem(elementItem);
            room.getRoomTreeItem().add(elementItem);
            room.addChild(element);
        }
        room.getRoomView().refreshView();
    }

    @Override
    public void undoCommand() {
        for (RoomElement element : elements) {
            DraftRoomChildrenRepository.getInstance().removeChildFromList(element);
            room.getRoomTreeItem().remove(element.getTreeItem());
            counterUpdate(element);
        }
        room.getChildren().removeAll(elements);
        room.getRoomView().refreshView();
    }

    private void counterUpdate(RoomElement element) {
        if (element instanceof Bed)
            Bed.setCounter(Bed.getCounter() - 1);
        else if (element instanceof Boiler)
            Boiler.setCounter(Boiler.getCounter() - 1);
        else if (element instanceof Closet)
            Closet.setCounter(Closet.getCounter() - 1);
        else if (element instanceof Door)
            Door.setCounter(Door.getCounter() - 1);
        else if (element instanceof Sink)
            Sink.setCounter(Sink.getCounter() - 1);
        else if (element instanceof Table)
            Table.setCounter(Table.getCounter() - 1);
        else if (element instanceof Toilet)
            Toilet.setCounter(Toilet.getCounter() - 1);
        else if (element instanceof Tub)
            Tub.setCounter(Tub.getCounter() - 1);
        else if (element instanceof WashingMachine)
            WashingMachine.setCounter(WashingMachine.getCounter() - 1);
    }
}
