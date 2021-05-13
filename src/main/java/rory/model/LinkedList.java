package rory.model;

public class LinkedList {
    private Item root;
    private int size;

    public LinkedList(){
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private Item getLast(){
        if (this.root == null){
            return null;
        }

        Item currentItem = this.root;
        while (currentItem.getNext() != null){
            currentItem = currentItem.getNext();
        }
        return currentItem;
    }

    public void addItem(Item item){
        if (this.root == null){
            this.root = item;
        } else{
            Item lastItem = this.getLast();
            lastItem.setNext(item);
            item.setLast(lastItem);
        }
        this.size++;
    }

    public Item getByIndex(int index){
        Item currentItem = this.root;

        while (index>0 && currentItem != null){
            currentItem = currentItem.getNext();
            index--;
        }

        return currentItem; //Works by decrementing index until it's 0, meaning indices above 0 should return null
    }

    public void deleteByIndex(int index){
        assert index>=0 && index<this.size;

        Item itemToDelete = this.getByIndex(index);

        Item lastItem = itemToDelete.getLast();
        Item nextItem = itemToDelete.getNext();

        //By having no pointers to the item at index, it's effectively deleted
        //Must check if nextItem is not null before calling to avoid error.
        //We also check if last is null, but this means we are deleting the root, so handle appropriately

        if (lastItem == null){
            this.root = this.root.getNext();
            this.root.setLast(null);
        } else {
            lastItem.setNext(nextItem);
        }

        if (nextItem != null) {
            nextItem.setLast(lastItem);
        }

        this.size--;
    }

    private void displayList(){ //*** Used for testing, delete ***
        Item currentItem = this.root;

        while (currentItem != null){
            System.out.println(currentItem.getType() + " | " + currentItem.getValue());
            currentItem = currentItem.getNext();
        }

        System.out.println("Size: " + this.size);
    }

    public static void main(String[] args) {
        LinkedList a = new LinkedList();

        Item item1 = new Item("Type One", "Value 1");
        Item item2 = new Item("Type Two", "Value 2");
        Item item3 = new Item("Type Three", "Value 3");
        Item item4 = new Item("Type Four", "Value 4");

        a.addItem(item1);
        a.addItem(item2);
        a.addItem(item3);
        a.addItem(item4);

        a.displayList();

        a.deleteByIndex(1);

        a.displayList();
    }
}
