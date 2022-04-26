package file;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import collection.CollectionValidator;
import collection.CollectionValidatorInterface;
import data.LabWork;
import util.ConsoleWorker;
import util.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static util.ConsoleWorker.print;
import static util.ConsoleWorker.println;
import static util.TextFormat.*;

public class FileWorker implements FileWorkerInterface{
    private final CollectionManagerInterface collectionManger;
    private final CollectionValidatorInterface collectionValidator;
    private final ConsoleWorker console;
    private String path;
    final Logger logger = Logger.getLogger(FileWorker.class.getCanonicalName());

    public FileWorker(CollectionManager collectionManager, ConsoleWorker console) {
        this.collectionManger = collectionManager;
        this.collectionValidator = new CollectionValidator(collectionManager);
        this.console = console;
    }

    @Override
    public Response saveToCSV() {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            ArrayList<LabWork> labWorks = collectionManger.getCollection();
            Collections.sort(labWorks);
            for (LabWork labWork : labWorks) {
                bufferedWriter.write(labWork.toCsv() + "\n");
            }
            logger.info("Collection downloaded!");
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, e.toString(), e);
            return null;
        }
        return new Response(successText("Collection recorded successfully!"));
    }

    @Override
    public boolean getFromCSV(){

        try {
            File file = new File(path);
            if (!file.exists()) {
                return create(file);
            }
            else {
                boolean hasLineErr = false;
                if (!file.canWrite()) changeWritePermissions(file);
                if (!file.canRead()) {
                    if (!changeReadPermissions(file)) {
                        logger.log(Level.WARNING,"Collection is empty!");;
                    }
                }
                FileInputStream fileStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(fileStream);

                char[] buffer = new char[fileStream.available()];
                reader.read(buffer, 0, fileStream.available());
                String[] labs = new String(buffer).split("\n");
                List<data.LabWork> labWorks = new ArrayList<>();
                for (String lab : labs) {
                    try {
                        labWorks.add(new LabWork(lab.split(",")));
                    } catch (IllegalArgumentException e) {
                        hasLineErr = true;
                    }

                }
                println(collectionValidator.validateCollection(labWorks, hasLineErr));
                return true;
            }

        }
        catch (IOException e) {
            logger.log(Level.SEVERE, e.toString(), e);
            return false;
        }
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean create(File file) throws IOException{
        if (file.createNewFile()) {
            logger.log(Level.WARNING, "File created successfully! Collection is empty!");
            return true;
        }
        else {
            logger.log(Level.SEVERE,"Failed to create file!");
            return false;
        }
    }

    @Override
    public boolean changeReadPermissions(File file) {
        print(errText("Cannot read file!\n") + helpText("Try to change permissions? [Y/N] "));
        String input;
        do {
            try {
                input = console.read();
                if (input.equals("Y") || input.equals("Yes")) {
                    if (!file.setReadable(true)) {
                        logger.log(Level.SEVERE,"Failed to change permissions");
                        return false;
                    } else {
                        logger.log(Level.FINE,"Permissions changed successfully!");
                        return true;
                    }
                } else if (input.equals("N") || input.equals("No")) return false;
                else print(errText("Please, write 'Yes' or 'No' [Y/N]: "));
            } catch (NullPointerException e) {
                print(errText("Please, write 'Yes' or 'No' [Y/N]: "));
            }
        } while (true);
    }

    @Override
    public void changeWritePermissions(File file) {
        print(errText("Cannot write file! You can't save data if you will to want!\n") +
                helpText("Try to change permissions? [Y/N] "));
        String input;
        do {
            try {
                input = console.read();
                if (input.equals("Y") || input.equals("Yes")) {
                    if (!file.setWritable(true)) {
                        logger.log(Level.SEVERE,"Failed to change permissions");
                    } else logger.log(Level.FINE,"Permissions changed successfully!");
                } else if (!input.equals("N") && !input.equals("No"))
                    print(errText("Please, write 'Yes' or 'No [Y/N] "));
            } catch (NullPointerException e) {
                input = "";
                print(errText("Please, write 'Yes' or 'No' [Y/N]: "));
            }
        } while (!input.equals("Y") && !input.equals("N") && !input.equals("Yes") && !input.equals("No"));
    }
}
