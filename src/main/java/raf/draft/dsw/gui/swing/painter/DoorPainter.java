package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Door;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;

public class DoorPainter extends PrimalPainter{

    private Door door;
    private boolean selected;
    private Room room;
    private int width;

    public DoorPainter(Door door) {
        super(door);
        this.door = door;
        room = (Room)door.getParent();
        this.door.setElementPainter(this);
    }



    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        int doorWidth = (int) (door.getWidthRatio()*width);
        this.width = doorWidth;

        if (door.getX() == -1 && door.getY() == -1) {
            door.setX(x + (doorWidth));
            door.setY(y + (doorWidth));
        }

        setShape(new GeneralPath());

        double arcRadius;
        int arcX;
        int arcY;
        int arcWidth;
        int arcHeight;
        int startAngle;
        int arcAngle;
        Arc2D.Double arc;

        switch (door.getRotateRatio()){
            case 0:
                getShape().moveTo(door.getX(), door.getY());
                getShape().lineTo(door.getX(), door.getY());
                arcRadius = doorWidth;
                arcX = (int) (door.getX()-arcRadius);
                arcY = (int) (door.getY()-arcRadius);
                arcWidth = (int) (arcRadius*2);
                arcHeight = (int) (arcRadius*2);
                startAngle = 0;
                arcAngle = 90;
                arc = new Arc2D.Double(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle, Arc2D.OPEN);
                getShape().append(arc, true);
                break;
            case 1:
                getShape().moveTo(door.getX(), door.getY());
                getShape().lineTo(door.getX(), door.getY());
                arcRadius = doorWidth;
                arcX = (int) (door.getX()-arcRadius);
                arcY = (int) (door.getY()-arcRadius);
                arcWidth = (int) (arcRadius*2);
                arcHeight = (int) (arcRadius*2);
                startAngle = 90;
                arcAngle = 90;
                arc = new Arc2D.Double(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle, Arc2D.OPEN);
                getShape().append(arc, true);
                break;
            case 2:
                getShape().moveTo(door.getX(), door.getY());
                getShape().lineTo(door.getX(), door.getY());
                arcRadius = doorWidth;
                arcX = (int) (door.getX()-arcRadius);
                arcY = (int) (door.getY()-arcRadius);
                arcWidth = (int) (arcRadius*2);
                arcHeight = (int) (arcRadius*2);
                startAngle = 180;
                arcAngle = 90;
                arc = new Arc2D.Double(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle, Arc2D.OPEN);
                getShape().append(arc, true);
                break;
            case 3:
                getShape().moveTo(door.getX(), door.getY());
                getShape().lineTo(door.getX(), door.getY());
                arcRadius = doorWidth;
                arcX = (int) (door.getX()-arcRadius);
                arcY = (int) (door.getY()-arcRadius);
                arcWidth = (int) (arcRadius*2);
                arcHeight = (int) (arcRadius*2);
                startAngle = 270;
                arcAngle = 90;
                arc = new Arc2D.Double(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle, Arc2D.OPEN);
                getShape().append(arc, true);
                break;
            default:
                break;
        }

        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.setStroke(new BasicStroke(2f));
        g2d.draw(getShape());


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(door);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), door.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }

    @Override
    public int getX() {
        return door.getX();
    }

    @Override
    public int getY() {
        return door.getY();
    }

    @Override
    public void setX(int x) {
        door.setX(x);
    }

    @Override
    public void setY(int y) {
        door.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return door.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return door.getHeight();
    }
    @Override
    public RoomElement getElement() {
        return door;
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
