package raf.draft.dsw.model.repository;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.ProjectExplorer;

import java.util.ArrayList;
import java.util.List;

public class DraftRoomChildrenRepository {

    private static DraftRoomChildrenRepository instance;
    private static ProjectExplorer root;
    private static List<DraftNode> nodes;

    private DraftRoomChildrenRepository() {
        nodes = new ArrayList<>();
    }

    public void addChildToList(DraftNode child){
        if (!nodes.contains(child))
            nodes.add(child);
    }

    public void removeChildFromList(DraftNode child){
        nodes.remove(child);
    }

    public void removeChildFromList(List<DraftNode> children){
        for (DraftNode child : children) {
            nodes.remove(child);
        }
    }

    public List<DraftNode> getNodes() {
        return nodes;
    }

    public void setRoot(ProjectExplorer exp) {
        if (root == null)
            root = exp;
    }

    public static DraftRoomChildrenRepository getInstance() {
        if (instance == null)
            instance = new DraftRoomChildrenRepository();
        return instance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DraftNode node : nodes) {
            sb.append(node.getName()).append(", ");
        }
        return sb.toString();
    }
}
