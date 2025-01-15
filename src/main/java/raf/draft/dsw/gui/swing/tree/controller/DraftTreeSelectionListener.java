package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.structures.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class DraftTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DraftTreeItem selectedItem = (DraftTreeItem) e.getPath().getLastPathComponent();
        TreePath path = e.getPath();
        DraftTreeItem treeItemSelected = (DraftTreeItem)path.getLastPathComponent();
        System.out.println("Selektovan cvor:"+ treeItemSelected.getDraftNode().getName());
        System.out.println("getPath: "+e.getPath());
        if (selectedItem.getDraftNode() instanceof Project) {
            Project selectedProject = (Project) selectedItem.getDraftNode();
            MainFrame.getInstance().setProjectView(selectedProject);
            MainFrame.getInstance().getProjectView().setLastDisplayedProject(selectedProject);
        }
    }
}


