package rory.model;

import java.util.ArrayList;

public class Block {
    private ArrayList<Item> items;

    //Pointers for linked list
    private Block last;
    private Block next;

    public Block(){
        this.items = new ArrayList<>();

        this.last = null;
        this.next = null;
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void setLast(Block last) {
        this.last = last;
    }

    public void setNext(Block next) {
        this.next = next;
    }

    public Block getLast() {
        return this.last;
    }

    public Block getNext() {
        return this.next;
    }

    //Removes all items which don't match the given type
    public void filterBy(String filter){
        ArrayList<Item> newItems = new ArrayList<>();

        for (Item item : this.items){
            if (item.getType().equals(filter)){
                newItems.add(item);
            }
        }

        this.items = newItems;
    }

    //Deletes an item by its hashcode
    public boolean deleteItem(String hashCode){
        for (int i=0; i<this.items.size(); i++){
            Item currentItem = this.items.get(i);
            String currentHashCode = currentItem.toString();

            if (currentHashCode.equals(hashCode)){
                this.items.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean editItem(String hashCode, String newType, String newValue){
        for (int i=0; i<this.items.size(); i++){
            Item currentItem = this.items.get(i);
            String currentHashCode = currentItem.toString();

            if (currentHashCode.equals(hashCode)){
                Item newItem = new Item(newType, newValue);
                this.items.set(i, newItem);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Result> search(String type, String text){
        ArrayList<Result> results = new ArrayList<>();

        for (int i=0; i<this.items.size(); i++){
            Item item = this.items.get(i);

            if (type.equals("none") || item.getType().equals(type)){
                String itemText = item.getValue();

                //If there are fewer than 3 disorders between texts or
                // the request text is contained in the item text
                if (Model.levenshteinDistance(itemText, text) < 3 || itemText.contains(text)){
                    Result r = new Result(item);
                    r.setColumn(i+1); //Most people count from 1, so add 1.
                    results.add(r);
                }
            }
        }

        return results;
    }
}
