package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.model.messages.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger{

    private static final String FILE_PATH = "src/main/resources/log.txt";
    private Message message;

    public FileLogger() {
    }

    @Override
    public void log() {
        File file = new File(FILE_PATH);
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(message.getFinalMessage() + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Object data) {
        if (data instanceof Message)
            this.message = (Message) data;
        log();
    }
}
