package rory.model;

import java.io.File;

public class Model {
    private LinkedList list;
    private int counter; //Used to iterate through; 'getNext', 'hasNext'

    public String[] getDataFileNames() {
        String x = File.separator;
        String currentPath = System.getProperty("user.dir");
        String dataPath = currentPath + x + "src" + x + "main" + x + "java" + x + "rory" + x + "data";

        File currentDirectory = new File(dataPath);
        return currentDirectory.list();
    }

    public Item getNext() {
        Item item = this.list.getByIndex(this.counter);
        this.counter++;
        return item;
    }

    public boolean hasNext() {
        return (this.counter < this.list.getSize());
    }
}