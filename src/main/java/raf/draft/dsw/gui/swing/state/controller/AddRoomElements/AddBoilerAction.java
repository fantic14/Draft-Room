package raf.draft.dsw.gui.swing.state.controller.AddRoomElements;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElements.Bed;
import raf.draft.dsw.model.structures.RoomElements.Boiler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddBoilerAction implements IPublisher {

    private final Room room;
    private final RoomView roomView;
    private List<ISubscriber> subs;

    public AddBoilerAction(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        subs = new ArrayList<>();
        addSubscriber(roomView);

        String diameter = JOptionPane.showInputDialog("Enter boiler width/height (cm)");
        int widthHeightCm;

        try {
            if (diameter != null) {
                widthHeightCm = Integer.parseInt(diameter);
            } else return;
        } catch (NumberFormatException e){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
            return;
        } catch (NullPointerException _){
            return;
        }

        if (widthHeightCm > room.getWidthCm() || widthHeightCm > room.getHeightCm()){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_ADD_ELEMENT_GREATER_THAN_ROOM", MessageType.ERROR);
            return;
        }

        double boilerDiameter = widthHeightCm*3.78;
        double boilerWidthRatio = boilerDiameter / room.getWidthInPixels();
        double boilerHeightRatio = boilerDiameter / room.getHeightInPixels();

        Boiler boiler = new Boiler(room, 0.2, 0.2, 0);
        boiler.setWidthRatio(boilerWidthRatio);
        boiler.setHeightRatio(boilerHeightRatio);
        boiler.setWidth(widthHeightCm);
        boiler.setHeight(widthHeightCm);

        boiler.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(boiler));
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
