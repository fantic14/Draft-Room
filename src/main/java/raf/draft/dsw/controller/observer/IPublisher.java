package raf.draft.dsw.controller.observer;

public interface IPublisher {

    void addSubscriber(ISubscriber newSub);
    void removeSubscriber(ISubscriber sub);
    void notifySubscribers(Object data);

}
