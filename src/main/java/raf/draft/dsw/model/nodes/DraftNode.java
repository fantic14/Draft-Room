package raf.draft.dsw.model.nodes;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;

import java.util.Objects;

public abstract class DraftNode {

    private String name;
    private DraftNode parent;
    private DraftTreeItem treeItem;

    public DraftNode(String name, DraftNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DraftNode getParent() {
        return parent;
    }

    public void setParent(DraftNode parent) {
        this.parent = parent;
    }

    public DraftTreeItem getTreeItem() {
        return treeItem;
    }

    public void setTreeItem(DraftTreeItem treeItem) {
        this.treeItem = treeItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DraftNode draftNode = (DraftNode) o;
        return Objects.equals(name, draftNode.name) && Objects.equals(parent, draftNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}
