package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.ProjectExplorer;

public interface DraftTree extends IPublisher {

    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    DraftNode addChild(DraftTreeItem parent);
    DraftTreeItem addChild(DraftNode element);
    void removeChild(DraftNode parent, DraftNode selected);
    DraftNode createNode(DraftNode parent);
    DraftTreeItem getSelectedNode();
    DraftTreeView getTreeView();
    @Override
    void addSubscriber(ISubscriber newSub);
    @Override
    void removeSubscriber(ISubscriber sub);
    @Override
    void notifySubscribers(Object data);

}
