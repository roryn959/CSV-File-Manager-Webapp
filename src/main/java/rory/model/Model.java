package rory.model;

import java.io.*;
import java.lang.reflect.Array;
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
        //File format is as follows:
        //Item -> Type~Value
        //Line -> Item#Line | null
        //Data -> Line\nData | null

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
            String[] pairs = line.split(";");

            for (String pair : pairs){
                if (!pair.isEmpty()) {
                    String[] type_value = pair.split("~");
                    block.addItem(new Item(type_value[0], type_value[1]));
                }
            }
            this.list.addBlock(block);
        }

        this.counter = 0;

        reader.close();
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
                fileWriter.write(item.getType() + '~' + item.getValue() + ';');
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

    public void addRow() throws IOException {
        String path = this.getDataFilePath();
        path = path + File.separator + this.list.getName();

        FileWriter fileWriter = new FileWriter(path, true);

        fileWriter.write('\n');
        fileWriter.close();
    }

    public void deleteBlock(String blockHashCode) throws IOException {
        this.list.deleteBlock(blockHashCode);
        this.writeFile();
    }

    public void deleteList(String listName) {
        String path = this.getDataFilePath();
        path = path + File.separator + listName;

        File file = new File(path);

        if (!file.delete()){
            System.out.println("Failed to delete list.");
        }
    }

    //Search for items in a particular list, or in all
    public ArrayList<Result> search(String listName, String type, String text) throws IOException {
        //Null list name means search in all files
        if (listName == null){
            ArrayList<Result> results = new ArrayList<>();

            for (String name : this.getDataFileNames()){
                this.loadFile(name);

                ArrayList<Result> blockResults = this.list.search(type, text);
                for (Result r : blockResults){
                    results.add(r);
                }
            }
            return results;
        } else {
            this.loadFile(listName);
            return this.list.search(type, text);
        }
    }

    //Search for list names, not items
    public ArrayList<String> searchListNames(String query){
        String[] listNames = this.getDataFileNames();
        ArrayList<String> results = new ArrayList<>();

        for (String name : listNames){
            //If there are fewer than 3 disorders between query and list name
            // or query is included in list name:
            if (Model.levenshteinDistance(query, name)<3 || name.contains(query)){
                results.add(name);
            }
        }
        return results;
    }

    //*** This method is referenced from my submission for coursework 1 ***
    public static int levenshteinDistance(String s1, String s2){ //Levenshtein algorithm for difference between two words using 2D matrix.
        int length1 = s1.length();
        int length2 = s2.length();

        int cost;
        int i;
        int j;

        //Create matrix with the lengths+1, because we need empty strings for a row and column in order to build the first distances of the table.
        int[][] table = new int[length1+1][length2+1];

        //Initialise the first rows of the table with difference between an empty string.
        for (i=0; i<=length1; i++){
            table[i][0] = i;
        }
        for (j=0; j<=length2; j++){
            table[0][j] = j;
        }

        //Building a matrix of distances for each substring of the words.
        for (j=1; j<=length2; j++){
            for (i=1; i<=length1; i++){

                //Setting the cost of substitution. It's 0 if the characters in a place are equal, 1 otherwise
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    cost = 0;
                }
                else{
                    cost = 1;
                }

                //For particular place in table, the distance between them is the lowest of the distances of the substrings before them,
                //plus one type of edit.
                table[i][j] = Math.min(table[i-1][j]+1, //A character is deleted. Adds 1 to distance.
                        Math.min(table[i][j-1]+1, //A character is inserted. Adds 1 to distance
                                table[i-1][j-1] + cost)); //A character is substituted. If characters are the same, adds noting. Else, 1.
            }
        }
        return table[length1][length2]; //The bottom-right of the matrix is the distance of the entire words.
    }
}