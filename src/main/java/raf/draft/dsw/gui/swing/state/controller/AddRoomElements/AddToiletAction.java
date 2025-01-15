package raf.draft.dsw.gui.swing.state.controller.AddRoomElements;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElements.Toilet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddToiletAction implements IPublisher {

    private final Room room;
    private final RoomView roomView;
    private List<ISubscriber> subs;

    public AddToiletAction(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        subs = new ArrayList<>();
        addSubscriber(roomView);

        String width = JOptionPane.showInputDialog("Enter toilet width (cm)");
        String height = JOptionPane.showInputDialog("Enter toilet lenght (cm)");

        int widthCm;
        int heightCm;

        try {
            if (width != null && height != null) {
                widthCm = Integer.parseInt(width);
                heightCm = Integer.parseInt(height);
            } else return;

        } catch (NumberFormatException e){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
            return;
        } catch (NullPointerException _){
            return;
        }

        if (widthCm > room.getWidthCm() || heightCm > room.getHeightCm()){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_ADD_ELEMENT_GREATER_THAN_ROOM", MessageType.ERROR);
            return;
        }

        double toiletWidth = widthCm *3.78;
        double toiletHeight = heightCm*3.78;
        double toiletWidthRatio = toiletWidth / room.getWidthInPixels();
        double toiletHeightRatio = toiletHeight / room.getHeightInPixels();

        Toilet toilet= new Toilet(room, 0.4, 0.4, 0);
        toilet.setWidthRatio(toiletWidthRatio);
        toilet.setHeightRatio(toiletHeightRatio);
        toilet.setWidth(widthCm);
        toilet.setHeight(heightCm);

        toilet.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(toilet));
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
