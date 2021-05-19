package rory.model;

import java.io.*;
import java.util.ArrayList;

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
        this.list = new LinkedList(filePath);
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
                if (!pair.isEmpty()) {
                    String[] type_value = pair.split("~");
                    block.addItem(new Item(type_value[0], type_value[1]));
                }
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

    public void deleteItem(String hashCode){
        if (this.list.deleteItem(hashCode)){
            try {
                this.writeFile();
            } catch (IOException e) {
                System.out.println("An error occurred in the writing of the file.");
            }
        } else {
            System.out.println("Failed to find item to delete");
        }
    }

    public void editItem(String hashCode, String newType, String newValue){
        if (this.list.editItem(hashCode, newType, newValue)){
            try {
                this.writeFile();
            } catch (IOException e){
                System.out.println("An error occurred in the writing of the file.");
            }
        } else {
            System.out.println("Failed to find item to edit.");
        }
    }

    public void addToBlock(String hashCode, String newType, String newValue){
        if (this.list.addToBlock(hashCode, newType, newValue)) {
            try {
                this.writeFile();
            } catch (IOException e) {
                System.out.println("An error occurred in the writing of the file.");
            }
        } else {
            System.out.println("Failed to add to block.");
        }
    }

    public void writeFile() throws IOException {
        String path = this.getDataFilePath();
        path = path + File.separator + this.list.getName();

        FileWriter fileWriter = new FileWriter(path, false);

        for (Block block : this.getBlocks()) {
            for (Item item : block.getItems()){
                fileWriter.write(item.getType() + '~' + item.getValue() + '#');
            }

            fileWriter.write('\n');
        }
        fileWriter.close();
    }

    public void createNewList(String name) throws IOException {
        String path = this.getDataFilePath();
        path = path + File.separator + name;

        FileWriter fileWriter = new FileWriter(path, false);
        fileWriter.close();
    }
}