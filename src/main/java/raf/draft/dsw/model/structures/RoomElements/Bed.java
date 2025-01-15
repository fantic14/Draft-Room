package raf.draft.dsw.model.structures.RoomElements;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.model.structures.Prototype;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.nodes.DraftNode;

public class Bed extends RoomElement implements Prototype {

    private final String name;
    private double xRatio;
    private double yRatio;
    private static int counter = 1;
    private int x = -1;
    private int y = -1;


    public Bed(DraftNode parent, double xRatio, double yRatio, int rotateRatio) {
        super("Bed" + counter, parent, xRatio, yRatio, rotateRatio);
        this.name = "Bed" + counter;
        ++counter;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }

    public double getXRatio() {
        return xRatio;
    }

    public void setXRatio(double xRatio) {
        this.xRatio = xRatio;
    }

    public double getYRatio() {
        return yRatio;
    }

    public void setYRatio(double yRatio) {
        this.yRatio = yRatio;
    }


    @Override
    public double getWidthRatio() {
        return super.getWidthRatio();
    }

    @Override
    public double getHeightRatio() {
        return super.getHeightRatio();
    }

    @Override
    public void setWidthRatio(double widthRatio){
        super.setWidthRatio(widthRatio);
    }

    @Override
    public void setHeightRatio(double heightRatio) {
        super.setHeightRatio(heightRatio);
    }

    @Override
    public void setRatio(double widthRatio, double heightRatio) {
        setWidthRatio(widthRatio);
        setHeightRatio(heightRatio);
    }

    @Override
    public Bed clone() {
        Bed cloned = new Bed(getParent(), getXRatio(), getYRatio(), getRotateRatio());
        cloned.setY(getY());
        cloned.setX(getX() + 20);
        cloned.setWidthRatio(getWidthRatio());
        cloned.setHeightRatio(getHeightRatio());
        cloned.setHeight(getHeight());
        cloned.setWidth(getWidth());
        //cloned.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(cloned));
        return cloned;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Bed.counter = counter;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
