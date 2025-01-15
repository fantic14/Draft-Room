package raf.draft.dsw.controller.actions;

public class ActionManager{

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewNodeAction newProjectAction;
    private RemoveNodeAction removeProjectAction;
    private RenameNodeAction renameNodeAction;
    private AuthorNamePathAction authorNamePathAction;
    private UndoAction undoAction;
    private DoAction doAction;
    private OrganiseMyRoomAction organiseMyRoomAction;
    private ShuffleOrganisedRoomAction shuffleOrganisedRoomAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.newProjectAction = new NewNodeAction();
        this.removeProjectAction = new RemoveNodeAction();
        this.renameNodeAction = new RenameNodeAction();
        this.authorNamePathAction = new AuthorNamePathAction();
        this.undoAction = new UndoAction();
        this.doAction = new DoAction();
        this.organiseMyRoomAction = new OrganiseMyRoomAction();
        this.shuffleOrganisedRoomAction = new ShuffleOrganisedRoomAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public NewNodeAction getNewProjectAction() {
        return newProjectAction;
    }

    public RemoveNodeAction getRemoveProjectAction() {
        return removeProjectAction;
    }

    public RenameNodeAction getRenameNodeAction() {
        return renameNodeAction;
    }

    public AuthorNamePathAction getAuthorNamePathAction() {
        return authorNamePathAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public DoAction getDoAction() {
        return doAction;
    }

    public OrganiseMyRoomAction getOrganiseMyRoomAction() {
        return organiseMyRoomAction;
    }

    public ShuffleOrganisedRoomAction getShuffleOrganisedRoomAction() {
        return shuffleOrganisedRoomAction;
    }
}
