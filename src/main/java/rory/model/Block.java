package rory.model;

import java.util.ArrayList;

public class Block {
    private ArrayList<Item> items;

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

    public void filterBy(String filter){
        ArrayList<Item> newItems = new ArrayList<>();

        for (Item item : this.items){
            if (item.getType().equals(filter)){
                newItems.add(item);
            } else{
                System.out.println("Not including " + item.getValue());
            }
        }

        this.items = newItems;
    }

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
}
