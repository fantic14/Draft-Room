package raf.draft.dsw.gui.swing.state.controller.AddRoomElements;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElements.Door;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddDoorAction implements IPublisher {

    private final Room room;
    private final RoomView roomView;
    private List<ISubscriber> subs;

    public AddDoorAction(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        subs = new ArrayList<>();
        addSubscriber(roomView);

        String width = JOptionPane.showInputDialog("Enter door width/height (cm)");

        int widthHeightCm;

        try {
            if (width != null) {
                widthHeightCm = Integer.parseInt(width);
            } else return;
        } catch (NumberFormatException e){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
            return;
        } catch (NullPointerException _){
            return;
        }

        if (widthHeightCm > room.getWidthCm()){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_ADD_ELEMENT_GREATER_THAN_ROOM", MessageType.ERROR);
            return;
        }

        double doorWidth = widthHeightCm *3.78;
        double doorWidthRatio = doorWidth / room.getWidthInPixels();


        Door door = new Door(room, 0.0, 0.1, 0);
        door.setWidthRatio(doorWidthRatio);
        door.setWidth(widthHeightCm);
        door.setHeight(widthHeightCm);


        door.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(door));
        notifySubscribers(null);
    }

    @Override
    public void addSubscriber(ISubscriber newSub) {
        if (!subs.contains(newSub))
            subs.add(newSub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object data) {
        for (ISubscriber sub : subs) {
            sub.update(data);
        }
    }
}
