package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class Project extends DraftNodeComposite {

    private String author;
    private String path;
    private List<DraftNode> projectChildren;

    public Project(String name, DraftNode parent, String author, String path) {
        super(name, parent);
        this.author = author;
        this.path = path;
        this.projectChildren = new ArrayList<>();
    }

    @Override
    public void addChild(DraftNode child) {
        if (child instanceof Building){
            Building building = (Building) child;

            if (!super.getChildren().contains(building))
                super.getChildren().add(building);
        }

        if (child instanceof Room){
            Room room = (Room) child;

            if (!super.getChildren().contains(room))
                super.getChildren().add(room);
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getAuthor() {
        return author;
    }

    public String getPath() {
        return path;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void removeProjectChild(DraftNode child){
        for (DraftNode node : projectChildren) {
            if (node == child) {
                projectChildren.remove(child);
                return;
            }
        }
        System.out.println("No such node");
    }

}
