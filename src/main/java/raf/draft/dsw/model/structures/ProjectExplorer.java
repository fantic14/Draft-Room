package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class ProjectExplorer extends DraftNodeComposite {
    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(DraftNode child) {
        if (child instanceof Project){
            Project project = (Project) child;

            if (!this.getChildren().contains(project))
                super.getChildren().add(project);
        }
    }
}
