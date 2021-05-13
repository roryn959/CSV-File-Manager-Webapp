package rory.model;

public class Item {
    private String value;
    private String type;

    private Item last;
    private Item next;

    public Item(String type, String value){
        this.type = type;
        this.value = value;

        this.last = null;
        this.next = null;
    }

    public void setLast(Item last) {
        this.last = last;
    }

    public void setNext(Item next) {
        this.next = next;
    }

    public Item getLast() {
        return this.last;
    }

    public Item getNext() {
        return this.next;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }
}
