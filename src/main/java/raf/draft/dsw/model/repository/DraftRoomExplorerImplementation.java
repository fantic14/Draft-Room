package raf.draft.dsw.model.repository;

import raf.draft.dsw.model.structures.ProjectExplorer;

public class DraftRoomExplorerImplementation implements DraftRoomRepository{

    private ProjectExplorer root;

    public DraftRoomExplorerImplementation() {
        this.root = new ProjectExplorer("My project explorer");
        DraftRoomChildrenRepository.getInstance().setRoot(root);
        DraftRoomChildrenRepository.getInstance().getNodes().add(root);
    }

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }
}
