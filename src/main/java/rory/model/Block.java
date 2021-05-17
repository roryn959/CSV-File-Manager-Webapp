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
            }
        }

        this.items = newItems;
    }
}
