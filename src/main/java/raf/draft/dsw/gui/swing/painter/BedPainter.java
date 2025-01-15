package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Bed;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class BedPainter extends PrimalPainter {

    private Bed bed;
    private Room room;
    private GeneralPath pillowShape;
    private boolean selected;
    int bedWidth;
    int bedHeight;


    public BedPainter(Bed bed) {
        super(bed);
        this.bed = bed;
        room = (Room)bed.getParent();
        this.bed.setElementPainter(this);
    }

    @Override
    public void setSelected(boolean b, RoomView roomView) {
        this.selected = b;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        bedWidth = (int) (bed.getWidthRatio() * width);
        bedHeight = (int) (bed.getHeightRatio() * height);

        /// smisliti drugo resenje
        /// ovaj if kvari to kako elementi prate promenu dimenzije sobe prilikom
        /// promene dimenzije prozora, kada se skloni if, prilikom povecavanja
        /// i smanjivanja velicine aplikacije svi elementi se smanjuju i povecavaju
        /// i ostaju na istom mestu ali move state ne radi

        if (bed.getX() == -1 && bed.getY() == -1) {
            //do {
                bed.setX(x + (int) (bed.getXRatio() * width) - bedWidth / 2);
                bed.setY(y + (int) (bed.getYRatio() * height) - bedHeight / 2);

                bed.setX(Math.max(x, Math.min(bed.getX(), x + width - bedWidth)));
                bed.setY(Math.max(y, Math.min(bed.getY(), y + height - bedHeight)));
            //} while(.elementAt(room.getRoomView().getPainters());
        }

        setShape(new GeneralPath());
        if (bed.getRotateRatio() == 1 || bed.getRotateRatio() == 3) {
            if (bed.getParent() instanceof Room room) {
                if (bedHeight > room.getWidthCm()) {
                    MessageGenerator mg = new MessageGenerator();
                    mg.generateMessage("ROTATED_DIMENSIONS_GREATER_THAN_ROOM_WIDTH_OR_HEIGHT", MessageType.WARNING);
                    bed.setRotateRatio(0);
                    return;
                }
            }
            getShape().moveTo(bed.getX(), bed.getY());
            getShape().lineTo(bed.getX() + bedHeight, bed.getY());
            getShape().lineTo(bed.getX() + bedHeight, bed.getY() + bedWidth);
            getShape().lineTo(bed.getX(), bed.getY() + bedWidth);
            getShape().closePath();
        } if (bed.getRotateRatio() == 0 || bed.getRotateRatio() == 2) {
            getShape().moveTo(bed.getX(), bed.getY());
            getShape().lineTo(bed.getX() + bedWidth, bed.getY());
            getShape().lineTo(bed.getX() + bedWidth, bed.getY() + bedHeight);
            getShape().lineTo(bed.getX(), bed.getY() + bedHeight);
            getShape().closePath();
        }

        g2d.setColor(Color.WHITE);
        g2d.fill(getShape());
        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.draw(getShape());

        int pillowWidth = (int) (bedWidth * 0.6);
        int pillowHeight = (int) (bedHeight * 0.2);
        int pillowX;
        int pillowY;

        switch (bed.getRotateRatio()){
            case 0:
                pillowX = bed.getX() + (bedWidth - pillowWidth) / 2;
                pillowY = bed.getY() + (int) (bedHeight * 0.1);
                pillowShape = new GeneralPath();
                pillowShape.moveTo(pillowX, pillowY);
                pillowShape.lineTo(pillowX + pillowWidth, pillowY);
                pillowShape.lineTo(pillowX + pillowWidth, pillowY + pillowHeight);
                pillowShape.lineTo(pillowX, pillowY + pillowHeight);
                pillowShape.closePath();
                break;
            case 1:
                pillowX = bed.getX() + (int) (bedHeight * 0.9);
                pillowY = bed.getY() + (bedWidth - pillowWidth) / 2;
                pillowShape = new GeneralPath();
                pillowShape.moveTo(pillowX, pillowY);
                pillowShape.lineTo(pillowX - pillowHeight, pillowY);
                pillowShape.lineTo(pillowX - pillowHeight, pillowY + pillowWidth);
                pillowShape.lineTo(pillowX, pillowY + pillowWidth);
                pillowShape.closePath();
                break;
            case 2:
                pillowX = bed.getX() + (bedWidth - pillowWidth) / 2;
                pillowY = bed.getY() + (int) (bedHeight * 0.9);
                pillowShape = new GeneralPath();
                pillowShape.moveTo(pillowX, pillowY);
                pillowShape.lineTo(pillowX + pillowWidth, pillowY);
                pillowShape.lineTo(pillowX + pillowWidth, pillowY - pillowHeight);
                pillowShape.lineTo(pillowX, pillowY - pillowHeight);
                pillowShape.closePath();
                break;
            case 3:
                pillowX = bed.getX() + (int) (bedHeight * 0.1);
                pillowY = bed.getY() + (bedWidth - pillowWidth) / 2;
                pillowShape = new GeneralPath();
                pillowShape.moveTo(pillowX, pillowY);
                pillowShape.lineTo(pillowX + pillowHeight, pillowY);
                pillowShape.lineTo(pillowX + pillowHeight, pillowY + pillowWidth);
                pillowShape.lineTo(pillowX, pillowY + pillowWidth);
                pillowShape.closePath();
                break;
            default:
                break;
        }


        g2d.setColor(Color.WHITE);
        g2d.fill(pillowShape);
        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.draw(pillowShape);


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(bed);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), bed.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }



    @Override
    public int getX() {
        return bed.getX();
    }

    @Override
    public int getY() {
        return bed.getY();
    }

    @Override
    public void setX(int x) {
        bed.setX(x);
    }

    @Override
    public void setY(int y) {
        bed.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return bed.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return bed.getHeight();
    }
    @Override
    public RoomElement getElement() {
        return bed;
    }
}

