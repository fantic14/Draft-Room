package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.ProjectView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.commands.implementation.AddCommand;
import raf.draft.dsw.gui.swing.tree.controller.DraftNodeFactory;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.DraftNodeLeaf;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.HashSet;
import java.util.Set;

public class DraftTreeImplementation implements DraftTree {

    private DraftTreeView treeView;
    private DefaultTreeModel treeModel;
    private DraftNodeFactory draftNodeFactory;
    private ProjectView projectView;
    private Set<ISubscriber> subs = new HashSet<>();

    public DraftTreeImplementation(ProjectView projectView) {
        this.projectView = projectView;
        addSubscriber(this.projectView);
    }

    @Override
    public DraftTreeView generateTree(ProjectExplorer projectExplorer) {
        DraftTreeItem root = new DraftTreeItem(projectExplorer, null);
        projectExplorer.setTreeItem(root);
        this.treeModel = new DefaultTreeModel(root);
        this.treeView = new DraftTreeView(treeModel);
        this.draftNodeFactory = new DraftNodeFactory();
        return treeView;
    }

    @Override
    public DraftTreeItem addChild(DraftNode element){
        Room parent = (Room) element.getParent();
        AbstractCommand command = new AddCommand((RoomElement) element, parent);
        MainFrame.getInstance().getCommandManager().addCommand(command);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        notifySubscribers(null);
        return element.getTreeItem();
    }

    @Override
    public DraftNode addChild(DraftTreeItem parent) {

        if (!(parent.getDraftNode() instanceof DraftNodeComposite))
            return null;

        DraftNode child = createNode(parent.getDraftNode());
        if (child == null)
            return null;
        DraftRoomChildrenRepository.getInstance().addChildToList(child);
        DraftTreeItem elementItem = new DraftTreeItem(child, parent);
        child.setTreeItem(elementItem);
        parent.add(elementItem);
        ((DraftNodeComposite) parent.getDraftNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        notifySubscribers(null);
        return child;
    }

    @Override
    public void removeChild(DraftNode parent, DraftNode selected) {

        if (!(parent instanceof DraftNodeComposite))
            return;

        if (selected instanceof DraftNodeComposite){
            DraftRoomChildrenRepository.getInstance().removeChildFromList(((DraftNodeComposite) selected).getChildren());
            ((DraftNodeComposite) selected).getChildren().clear();
            ((DraftNodeComposite) parent).getChildren().remove(selected);
            DraftRoomChildrenRepository.getInstance().removeChildFromList(selected);
            parent.getTreeItem().remove(selected.getTreeItem());
            //if (selected instanceof Room && parent instanceof Project){
              //  ((Project) parent).removeChild(selected);
            //}
        } else if (selected instanceof DraftNodeLeaf){
            ((DraftNodeComposite) parent).getChildren().remove(selected);
            DraftRoomChildrenRepository.getInstance().removeChildFromList(selected);
            parent.getTreeItem().remove(selected.getTreeItem());
        }
        SwingUtilities.updateComponentTreeUI(treeView);
        notifySubscribers(null);
    }

    @Override
    public DraftTreeItem getSelectedNode() {
        return (DraftTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public DraftNode createNode(DraftNode parent) {
        return this.draftNodeFactory.createNode(parent);
    }

    @Override
    public DraftTreeView getTreeView() {
        return treeView;
    }

    @Override
    public void addSubscriber(ISubscriber newSub) {
        this.subs.add(newSub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        this.subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object data) {
        for (ISubscriber sub : subs) {
            sub.update(data);
        }
    }

}
