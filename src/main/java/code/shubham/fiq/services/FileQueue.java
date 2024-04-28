package code.shubham.fiq.services;

import code.shubham.commons.utils.FileUtils;
import code.shubham.commons.utils.ShutdownUtils;
import code.shubham.fiq.models.Message;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileQueue implements Queue {

    private final Path path;
    private BufferedReader reader;

    public FileQueue(final String filePath) {
        this.path = FileUtils.createFileIfNotExists(filePath);
        try {
            this.reader = new BufferedReader(new FileReader(this.path.toFile()));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        ShutdownUtils.defer(this::close);
    }

    @Override
    public void offer(final Message message) throws Exception {
        File lockFile = null;
        try {
            lockFile = this.lock();
            Files.write(this.path, (message.toString() + "\n").getBytes(), StandardOpenOption.APPEND);
        } finally {
            if (lockFile != null)
                this.unLock(lockFile);
        }
    }

    @Override
    public Message poll() {
        String line;
        try {
            while (true) {
                line = reader.readLine();
                if (line != null) {
                    return Message.of(line);
                }
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private File lock() {
        return FileUtils.lockFile(this.path.toFile().getAbsolutePath() + ".lock");
    }
    private void unLock(final File lockFile) {
        FileUtils.delete(lockFile);
    }

    public void close() {

    }



}
