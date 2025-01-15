package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.MessageType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageGenerator implements IPublisher {

    private final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Set<ISubscriber> subs;

    public MessageGenerator() {
        subs = new HashSet<>();
    }

    public void generateMessage(String content, MessageType messageType) {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(timeFormatter);
        Message message = new Message("[" + messageType + "] [" + time + "] " + content, messageType, now);
        LoggerFactory loggerFactory = new LoggerFactory();
        List<Logger> loggers = loggerFactory.createLogger(messageType);
        for (Logger logger : loggers) {
            addSubscriber(logger);
        }
        addSubscriber(MainFrame.getInstance());
        notifySubscribers(message);
    }

    @Override
    public void addSubscriber(ISubscriber newSub) {
            subs.add(newSub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object data) {
        for (ISubscriber sub : subs) {
            sub.update(data);
        }
    }


}
