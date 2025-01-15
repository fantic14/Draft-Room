package raf.draft.dsw.gui.swing.commands.implementation;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.*;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements AbstractCommand {

    private Room room;
    private List<RoomElement> roomElements = new ArrayList<>();

    public DeleteCommand(Room room, List<RoomElement> roomElements) {
        this.room = room;
        this.roomElements = roomElements;
    }

    @Override
    public void doCommand() {
        room.getChildren().removeAll(roomElements);
        for (RoomElement roomElement : roomElements) {
            DraftRoomChildrenRepository.getInstance().removeChildFromList(roomElement);
            room.getRoomView().getPainters().remove(roomElement.getElementPainter());
            Room parent = (Room)roomElement.getParent();
            MainFrame.getInstance().getDraftTree().removeChild(parent, roomElement);
        }
        MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
        room.getRoomView().refreshView();
    }

    @Override
    public void undoCommand() {
        for (RoomElement roomElement : roomElements) {
            DraftRoomChildrenRepository.getInstance().addChildToList(roomElement);
            room.getTreeItem().add(roomElement.getTreeItem());
            room.addChild(roomElement);
            counterUpdate(roomElement);
        }
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
