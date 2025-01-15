package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.commands.implementation.DeleteCommand;
import raf.draft.dsw.gui.swing.painter.*;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State, IPublisher {

    private List<ISubscriber> subs;

    public DeleteState() {
        this.subs = new ArrayList<>();
    }

    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {
        if (!subs.contains(roomView))
            addSubscriber(roomView);
    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        Point clickPoint = e.getPoint();
        boolean somethingClicked = false;
        Painter elementClicked = null;
        List<RoomElement> roomElements = new ArrayList<>();

        for (int i = 0; i < roomView.getPainters().size(); i++){
            Painter element = roomView.getPainters().get(i);
            if (element.elementAt(clickPoint)){
                elementClicked = element;
                roomElements.add(element.getElement());
                somethingClicked = true;
                counterUpdate(element);
                break;
            }
        }
        if (somethingClicked) {
            for (int i = 0; i < roomView.getPainters().size(); i++) {
                Painter element = roomView.getPainters().get(i);
                if (element.isSelected() && element != elementClicked) {
                    roomElements.add(element.getElement());
                    counterUpdate(element);
                }
            }
        }
        AbstractCommand command = new DeleteCommand(roomView.getRoom(), roomElements);
        MainFrame.getInstance().getCommandManager().addCommand(command);
        notifySubscribers(null);
    }

    private void counterUpdate(Painter element) {
        if (element instanceof BedPainter)
            Bed.setCounter(Bed.getCounter() - 1);
        else if (element instanceof BoilerPainter)
            Boiler.setCounter(Boiler.getCounter() - 1);
        else if (element instanceof ClosetPainter)
            Closet.setCounter(Closet.getCounter() - 1);
        else if (element instanceof DoorPainter)
            Door.setCounter(Door.getCounter() - 1);
        else if (element instanceof SinkPainter)
            Sink.setCounter(Sink.getCounter() - 1);
        else if (element instanceof TablePainter)
            Table.setCounter(Table.getCounter() - 1);
        else if (element instanceof ToiletPainter)
            Toilet.setCounter(Toilet.getCounter() - 1);
        else if (element instanceof TubPainter)
            Tub.setCounter(Tub.getCounter() - 1);
        else if (element instanceof WashingMachinePainter)
            WashingMachine.setCounter(WashingMachine.getCounter() - 1);
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }

    @Override
    public void addSubscriber(ISubscriber newSub) {
        if (!subs.contains(newSub))
            this.subs.add(newSub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        this.subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object data) {
        for (ISubscriber sub : subs) {
            sub.update(data);
        }
    }
}
