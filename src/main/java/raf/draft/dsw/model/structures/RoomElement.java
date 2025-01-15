package raf.draft.dsw.model.structures;

import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeLeaf;

public abstract class RoomElement extends DraftNodeLeaf implements Prototype {

    private double xRatio;
    private double yRatio;
    private int width;
    private int height;
    private double widthRatio;
    private double heightRatio;
    private int rotateRatio;
    private DraftTreeItem elementTreeItem;
    private Painter elementPainter;

    public RoomElement(String name, DraftNode parent, double xRatio, double yRatio, int rotateRatio) {
        super(name,parent);
        this.xRatio = xRatio;
        this.yRatio = yRatio;
        this.rotateRatio = rotateRatio;
    }


    public double getxRatio() {
        return xRatio;
    }

    public void setxRatio(double xRatio) {
        this.xRatio = xRatio;
    }

    public double getyRatio() {
        return yRatio;
    }

    public void setyRatio(double yRatio) {
        this.yRatio = yRatio;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidthRatio(double widthRatio) {
        this.widthRatio = widthRatio;
    }

    public void setHeightRatio(double heightRatio) {
        this.heightRatio = heightRatio;
    }

    @Override
    public double getWidthRatio() {
        return widthRatio;
    }

    public double getHeightRatio() {
        return heightRatio;
    }

    public int getRotateRatio() {
        return rotateRatio;
    }

    public void setRotateRatio(int rotateRatio) {
        this.rotateRatio = rotateRatio;
    }


    public abstract String getName();

    @Override
    public abstract RoomElement clone();

    public DraftTreeItem getElementTreeItem() {
        return elementTreeItem;
    }

    public void setElementTreeItem(DraftTreeItem elementTreeItem) {
        this.elementTreeItem = elementTreeItem;
    }

    public abstract int getX();

    public abstract void setX(int x);

    public abstract int getY();

    public abstract void setY(int y);

    public void setElementPainter(Painter elementPainter) {
        this.elementPainter = elementPainter;
    }

    public Painter getElementPainter() {
        return elementPainter;
    }
}
