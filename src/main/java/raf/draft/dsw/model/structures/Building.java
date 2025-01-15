package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;

public class Building extends DraftNodeComposite {

    Color tabColor;

    public Building(String name, DraftNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(DraftNode child) {
        if (child instanceof Room){
            Room room = (Room) child;

            if (!this.getChildren().contains(room))
                super.getChildren().add(room);
        }
    }

    public Color getTabColor() {
        return tabColor;
    }

    public void setTabColor(Color tabColor) {
        this.tabColor = tabColor;
    }
}
