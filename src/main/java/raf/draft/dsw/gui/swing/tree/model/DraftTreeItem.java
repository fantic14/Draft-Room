package raf.draft.dsw.gui.swing.tree.model;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;

import javax.swing.tree.DefaultMutableTreeNode;

public class DraftTreeItem extends DefaultMutableTreeNode {

    private DraftNode draftNode;
    private DraftTreeItem parentItem;

    public DraftTreeItem(DraftNode nodeModel, DraftTreeItem parentItem) {
        this.draftNode = nodeModel;
        this.parentItem = parentItem;
        if (draftNode instanceof Room)
            ((Room) draftNode).setRoomTreeItem(this);
    }

    @Override
    public String toString() {
        return draftNode.getName();
    }

    public void setName(String name) {
        this.draftNode.setName(name);
    }

    public DraftNode getDraftNode() {
        return draftNode;
    }

    public void setDraftNode(DraftNode draftNode) {
        this.draftNode = draftNode;
    }

    public DraftTreeItem getParentItem() {
        return parentItem;
    }
}
