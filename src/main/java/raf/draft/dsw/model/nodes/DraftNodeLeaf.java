package raf.draft.dsw.model.nodes;

public abstract class DraftNodeLeaf extends DraftNode{
    public DraftNodeLeaf(String name, DraftNode parent) {
        super(name, parent);
    }
    public abstract void setRatio(double widthRatio, double heightRatio);
    public abstract double getWidthRatio();
    public abstract double getHeightRatio();
}
