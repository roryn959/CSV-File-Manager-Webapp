package rory.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {
    private LinkedList list;
    private int counter; //Used to iterate through; 'getNext', 'hasNext'

    private String getDataFilePath(){
        String x = File.separator;
        String currentPath = System.getProperty("user.dir");
        String dataPath = currentPath + x + "src" + x + "main" + x + "java" + x + "rory" + x + "data";

        return dataPath;
    }

    public String[] getDataFileNames() {
        String dataPath = this.getDataFilePath();

        File dataDirectory = new File(dataPath);
        return dataDirectory.list();
    }

    public void loadFile(String filePath) throws IOException {
        this.list = new LinkedList();
        //Takes file name as parameter. Prepend the path to the data files to the name.
        String dataFilePath = this.getDataFilePath();
        filePath = dataFilePath + File.separator + filePath;

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        Block block;
        Item item;

        while ((line = reader.readLine()) != null) {
            block = new Block();
            String[] pairs = line.split("#");

            for (String pair : pairs){
                String[] type_value = pair.split("~");
                block.addItem(new Item(type_value[0], type_value[1]));
            }
            this.list.addBlock(block);
        }

        this.counter = 0;
    }

    public Block getNext(){
        Block block = this.list.getByIndex(this.counter);
        this.counter++;
        return block;
    }

    public boolean hasNext() {
        return (this.counter < this.list.getSize());
    }

    public ArrayList<Block> getBlocks(){
        ArrayList<Block> blocks = new ArrayList<>();
        this.resetCounter();

        while (this.hasNext()){
            blocks.add(this.getNext());
        }

        return blocks;
    }

    public void resetCounter(){
        this.counter = 0;
    }

    public void filterBy(String filter){
        if (filter != null && !filter.equals("none")) {
            this.list.filterBy(filter);
        }
    }
}