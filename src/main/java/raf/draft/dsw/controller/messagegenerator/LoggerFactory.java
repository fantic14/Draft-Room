package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.MessageType;

import java.util.ArrayList;

public class LoggerFactory {

    private ArrayList<Logger> loggers = new ArrayList<>();

    public LoggerFactory() {
    }

    public ArrayList<Logger> createLogger (MessageType messageType){
        loggers.add(new ConsoleLogger());
        if (messageType == MessageType.ERROR){
            loggers.add(new FileLogger());
            return loggers;
        }
        return loggers;
    }
}
