package raf.draft.dsw.model.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class DraftNodeComposite extends DraftNode{

    private List<DraftNode> children;

    public DraftNodeComposite(String name, DraftNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    public abstract void addChild(DraftNode child);

    public void removeChild(DraftNode n){
        for (DraftNode child : children) {
            if (child == n) {
                children.remove(child);
                return;
            }
        }
        System.out.println("Node does not exist" + n);
    }

    public DraftNode getChildByName(String name){
        for (DraftNode child : children) {
            if (child.getName().equals(name))
                return child;
        }
        System.out.println("Child with said name does not exist");
        return null;
    };

    public List<DraftNode> getChildren() {
        return children;
    }
}
