package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.WashingMachine;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class WashingMachinePainter extends PrimalPainter{

    private WashingMachine wmachine;
    private boolean selected;
    private Room room;

    public WashingMachinePainter(WashingMachine wmachine) {
        super(wmachine);
        this.wmachine = wmachine;
        room = (Room)wmachine.getParent();
        this.wmachine.setElementPainter(this);
    }


    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        int squareSize = (int) (wmachine.getWidthRatio()*width);
        int circleDiameter = squareSize/2;

        if (wmachine.getX() == -1 && wmachine.getY() == -1) {
            wmachine.setX(x + width / 2);
            wmachine.setY(y + height / 2);
        }

        setShape(new GeneralPath());
        getShape().moveTo(wmachine.getX() - squareSize / 2, wmachine.getY() - squareSize / 2);
        getShape().lineTo(wmachine.getX() + squareSize / 2, wmachine.getY() - squareSize / 2);
        getShape().lineTo(wmachine.getX() + squareSize / 2, wmachine.getY() + squareSize / 2);
        getShape().lineTo(wmachine.getX() - squareSize / 2, wmachine.getY() + squareSize / 2);
        getShape().closePath();

        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.draw(getShape());

        g2d.setColor(Color.WHITE);
        g2d.fillOval(wmachine.getX() - circleDiameter / 2, wmachine.getY() + squareSize / 2 - circleDiameter - 10, circleDiameter, circleDiameter);

        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.drawOval(wmachine.getX() - circleDiameter / 2, wmachine.getY() + squareSize / 2 - circleDiameter - 10, circleDiameter, circleDiameter);


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(wmachine);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), wmachine.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }

    @Override
    public int getX() {
        return wmachine.getX();
    }

    @Override
    public int getY() {
        return wmachine.getY();
    }

    @Override
    public void setX(int x) {
        wmachine.setX(x);
    }

    @Override
    public void setY(int y) {
        wmachine.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return wmachine.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return wmachine.getHeight();
    }
    @Override
    public RoomElement getElement() {
        return wmachine;
    }

    @Override
    public void setSelected(boolean b, RoomView roomView) {
        this.selected = b;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

}
