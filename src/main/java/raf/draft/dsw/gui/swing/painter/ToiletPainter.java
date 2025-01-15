package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Toilet;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ToiletPainter extends PrimalPainter{

    private Toilet toilet;
    private boolean selected;
    private Room room;

    public ToiletPainter(Toilet toilet) {
        super(toilet);
        this.toilet = toilet;
        room = (Room)toilet.getParent();
        this.toilet.setElementPainter(this);
    }


    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        int rectWidth = (int) (toilet.getWidthRatio()*width);
        int rectHeight = (int) (toilet.getHeightRatio()*height/4);
        int uWidth = (int) (toilet.getWidthRatio()*width);
        int uHeight = (int) (toilet.getHeightRatio()*height*3/4);
        int uShapeWidth = (int) (toilet.getWidthRatio() * width * 0.3);
        int uShapeHeight = (int) (toilet.getHeightRatio() * height * 0.25);

        if (toilet.getX() == -1 && toilet.getY() == -1) {
            toilet.setX(x + width / 2);
            toilet.setY(y + height / 2);
        }

        setShape(new GeneralPath());
        GeneralPath blackUShape = new GeneralPath();

        switch (toilet.getRotateRatio()) {
            case 0:
                getShape().moveTo(toilet.getX() - rectWidth / 2, toilet.getY() - uHeight / 2 - rectHeight);
                getShape().lineTo(toilet.getX() + rectWidth / 2, toilet.getY() - uHeight / 2 - rectHeight);
                getShape().lineTo(toilet.getX() + rectWidth / 2, toilet.getY() - uHeight / 2);
                getShape().lineTo(toilet.getX() - rectWidth / 2, toilet.getY() - uHeight / 2);
                getShape().closePath();

                getShape().moveTo(toilet.getX() - uWidth / 2, toilet.getY() - uHeight / 2);
                getShape().quadTo(toilet.getX() - uWidth / 2, toilet.getY() + uHeight / 2, toilet.getX(), toilet.getY() + uHeight / 2);
                getShape().quadTo(toilet.getX() + uWidth / 2, toilet.getY() + uHeight / 2, toilet.getX() + uWidth / 2, toilet.getY() - uHeight / 2);
                getShape().closePath();

                blackUShape.moveTo(toilet.getX() - uShapeWidth / 2, toilet.getY() - uShapeHeight);
                blackUShape.quadTo(toilet.getX() - uShapeWidth / 2, toilet.getY() + uShapeHeight / 2, toilet.getX(), toilet.getY() + uShapeHeight / 2);
                blackUShape.quadTo(toilet.getX() + uShapeWidth / 2, toilet.getY() + uShapeHeight / 2, toilet.getX() + uShapeWidth / 2, toilet.getY() - uShapeHeight);
                blackUShape.closePath();
                break;
            case 1:
                getShape().moveTo(toilet.getX() + rectHeight + uHeight, toilet.getY() - rectWidth/2);
                getShape().lineTo(toilet.getX() + rectHeight + uHeight, toilet.getY() + rectWidth/2);
                getShape().lineTo(toilet.getX() + uHeight, toilet.getY() + rectWidth/2);
                getShape().lineTo(toilet.getX() + uHeight, toilet.getY() - rectWidth/2);
                getShape().closePath();

                getShape().moveTo(toilet.getX() + uHeight, toilet.getY() - uWidth / 2);
                getShape().quadTo(toilet.getX(), toilet.getY() - uWidth / 2, toilet.getX(), toilet.getY());
                getShape().quadTo(toilet.getX(), toilet.getY() + rectWidth / 2, toilet.getX() + uHeight, toilet.getY() + rectWidth / 2);
                getShape().closePath();

                blackUShape.moveTo(toilet.getX() + uHeight - uShapeHeight/2, toilet.getY() - uShapeWidth / 2);
                blackUShape.quadTo(toilet.getX() + uHeight - uShapeHeight*2, toilet.getY() - uShapeWidth / 2, toilet.getX() + uHeight - uShapeHeight*2, toilet.getY());
                blackUShape.quadTo(toilet.getX() + uHeight - uShapeHeight*2, toilet.getY() + uShapeWidth / 2, toilet.getX() + uHeight - uShapeHeight/2, toilet.getY() + uShapeWidth / 2);
                blackUShape.closePath();
                break;
            case 2:
                getShape().moveTo(toilet.getX() - rectWidth / 2, toilet.getY() + uHeight / 2 + rectHeight);
                getShape().lineTo(toilet.getX() + rectWidth / 2, toilet.getY() + uHeight / 2 + rectHeight);
                getShape().lineTo(toilet.getX() + rectWidth / 2, toilet.getY() + uHeight / 2);
                getShape().lineTo(toilet.getX() - rectWidth / 2, toilet.getY() + uHeight / 2);
                getShape().closePath();

                getShape().moveTo(toilet.getX() - uWidth / 2, toilet.getY() + uHeight / 2);
                getShape().quadTo(toilet.getX() - uWidth / 2, toilet.getY() - uHeight / 2, toilet.getX(), toilet.getY() - uHeight / 2);
                getShape().quadTo(toilet.getX() + uWidth / 2, toilet.getY() - uHeight / 2, toilet.getX() + uWidth / 2, toilet.getY() + uHeight / 2);
                getShape().closePath();

                blackUShape.moveTo(toilet.getX() - uShapeWidth / 2, toilet.getY() + uShapeHeight);
                blackUShape.quadTo(toilet.getX() - uShapeWidth / 2, toilet.getY() - uShapeHeight / 2, toilet.getX(), toilet.getY() - uShapeHeight / 2);
                blackUShape.quadTo(toilet.getX() + uShapeWidth / 2, toilet.getY() - uShapeHeight / 2, toilet.getX() + uShapeWidth / 2, toilet.getY() + uShapeHeight);
                blackUShape.closePath();
                break;
            case 3:
                getShape().moveTo(toilet.getX(), toilet.getY() - rectWidth/2);
                getShape().lineTo(toilet.getX(), toilet.getY() + rectWidth/2);
                getShape().lineTo(toilet.getX() + rectHeight, toilet.getY() + rectWidth/2);
                getShape().lineTo(toilet.getX() + rectHeight, toilet.getY() - rectWidth/2);
                getShape().closePath();

                getShape().moveTo(toilet.getX() + rectHeight, toilet.getY() - uWidth / 2);
                getShape().quadTo(toilet.getX() + uHeight + rectHeight, toilet.getY() - uWidth / 2, toilet.getX() + uHeight + rectHeight, toilet.getY());
                getShape().quadTo(toilet.getX() + uHeight + rectHeight, toilet.getY() + rectWidth / 2, toilet.getX() + rectHeight, toilet.getY() + rectWidth / 2);
                getShape().closePath();

                blackUShape.moveTo(toilet.getX() + rectHeight + uShapeHeight/2, toilet.getY() - uShapeWidth / 2);
                blackUShape.quadTo(toilet.getX() + rectHeight + uShapeHeight*2, toilet.getY() - uShapeWidth / 2, toilet.getX() + rectHeight + uShapeHeight*2, toilet.getY());
                blackUShape.quadTo(toilet.getX() + rectHeight + uShapeHeight*2, toilet.getY() + uShapeWidth / 2, toilet.getX() + rectHeight + uShapeHeight / 2, toilet.getY() + uShapeWidth / 2);
                blackUShape.closePath();
                break;
            default:
                break;
        }

        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.draw(getShape());
        g2d.setColor(Color.BLACK);
        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.draw(getShape());
        g2d.fill(blackUShape);
        g2d.draw(blackUShape);


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(toilet);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), toilet.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }

    @Override
    public int getX() {
        return toilet.getX();
    }

    @Override
    public int getY() {
        return toilet.getY();
    }

    @Override
    public void setX(int x) {
        toilet.setX(x);
    }

    @Override
    public void setY(int y) {
        toilet.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return toilet.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return toilet.getHeight();
    }
    @Override
    public RoomElement getElement() {
        return toilet;
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
