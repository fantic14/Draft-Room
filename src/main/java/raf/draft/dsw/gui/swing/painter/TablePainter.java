package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Table;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class TablePainter extends PrimalPainter{

    private Table table;
    private boolean selected;
    private Room room;

    public TablePainter(Table table) {
        super(table);
        this.table = table;
        room = (Room)table.getParent();
        this.table.setElementPainter(this);
    }


    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        int tableWidth = (int) (table.getWidthRatio()*width);
        int tableHeight = (int) (table.getHeightRatio()*height);

        if (table.getX() == -1 && table.getY() == -1) {
            table.setX(x + (width - tableWidth) / 2);
            table.setY(y + (height - tableHeight) / 2);
        }

        setShape(new GeneralPath());

        switch (table.getRotateRatio()) {
            case 1,3:
                if (table.getParent() instanceof Room room) {
                    if (tableHeight > room.getWidthCm()) {
                        MessageGenerator mg = new MessageGenerator();
                        mg.generateMessage("ROTATED_DIMENSIONS_GREATER_THAN_ROOM_WIDTH_OR_HEIGHT", MessageType.WARNING);
                        table.setRotateRatio(0);
                        return;
                    }
                }
                tableWidth = (int) (table.getHeightRatio()*height);
                tableHeight = (int) (table.getWidthRatio()*width);
                break;
            default:
                break;
        }

        getShape().moveTo(table.getX(), table.getY());
        getShape().lineTo(table.getX() + tableWidth, table.getY());
        getShape().lineTo(table.getX() + tableWidth, table.getY() + tableHeight);
        getShape().lineTo(table.getX(), table.getY() + tableHeight);
        getShape().closePath();

        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.draw(getShape());
        g2d.setColor(Color.WHITE);
        g2d.fill(getShape());

        int dotSize = 6;
        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.fillOval(table.getX() - dotSize / 2, table.getY() - dotSize / 2, dotSize, dotSize);
        g2d.fillOval(table.getX() + tableWidth - dotSize / 2, table.getY() - dotSize / 2, dotSize, dotSize);
        g2d.fillOval(table.getX() + tableWidth - dotSize / 2, table.getY() + tableHeight - dotSize / 2, dotSize, dotSize);
        g2d.fillOval(table.getX() - dotSize / 2, table.getY() + tableHeight - dotSize / 2, dotSize, dotSize);


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(table);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), table.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }

        AffineTransform start = g2d.getTransform();
        AffineTransform rotation = new AffineTransform();

//        Point mousePosition = getRoomElement().getMousePosition();
//        if (mousePosition == null) {
//            mousePosition = new Point(0, 0);
//        }
//        if (ProjectView.getInstance().strat == 1) {
//            double translateX = mousePosition.x * (1 - getRoomElement().getZoomRatio());
//            double translateY = mousePosition.y * (1 - getRoomElement().getZoomRatio());
//            rotation.translate(translateX, translateY);
//            rotation.scale(getRoomElement().getZoomRatio(), getRoomElement().getZoomRatio());
//        }
//        Point center = new Point(table.getX() + tableHeight/2, table.getY() + tableWidth/2);
//
//
//        rotation.rotate(Math.toRadians(table.getRotateRatio() * 90), center.x, center.y);
//        g2d.transform(rotation);
//        g2d.draw(getShape());
//        g2d.setTransform(start);

    }

    @Override
    public int getX() {
        return table.getX();
    }

    @Override
    public int getY() {
        return table.getY();
    }

    @Override
    public void setX(int x) {
        table.setX(x);
    }

    @Override
    public void setY(int y) {
        table.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return table.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return table.getHeight();
    }

    @Override
    public RoomElement getElement() {
        return table;
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
