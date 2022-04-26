package util;

import file.FileWorker;

import java.util.logging.Logger;

public class ExitSaver implements Runnable{

    private final FileWorker fileWorker;
    final Logger logger = Logger.getLogger(ExitSaver.class.getCanonicalName());

    public ExitSaver(FileWorker fileWorker) {this.fileWorker = fileWorker;}

    @Override
    public void run() {
        logger.info("Server was turned off!");
        fileWorker.saveToCSV();
    }
}
