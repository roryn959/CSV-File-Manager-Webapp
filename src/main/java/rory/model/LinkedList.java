package rory.model;

public class LinkedList {
    private Block root;
    private int size;
    private String name;

    public LinkedList(String name){
        this.root = null;
        this.size = 0;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName(){
        return this.name;
    }

    private Block getLast(){
        if (this.root == null){
            return null;
        }

        Block currentBlock = this.root;
        while (currentBlock.getNext() != null){
            currentBlock = currentBlock.getNext();
        }
        return currentBlock;
    }

    public void addBlock(Block block){
        if (this.root == null){
            this.root = block;
        } else{
            Block lastBlock = this.getLast();
            lastBlock.setNext(block);
            block.setLast(lastBlock);
        }
        this.size++;
    }

    public Block getByIndex(int index){
        Block currentBlock = this.root;

        while (index>0 && currentBlock != null){
            currentBlock = currentBlock.getNext();
            index--;
        }

        return currentBlock; //Works by decrementing index until it's 0, meaning indices above 0 should return null
    }

    public void deleteByIndex(int index){
        assert index>=0 && index<this.size;

        Block blockToDelete = this.getByIndex(index);

        Block lastBlock = blockToDelete.getLast();
        Block nextBlock = blockToDelete.getNext();

        //By having no pointers to the item at index, it's effectively deleted
        //Must check if nextItem is not null before calling to avoid error.
        //We also check if last is null, but this means we are deleting the root, so handle appropriately

        if (lastBlock == null){
            this.root = this.root.getNext();

            //If we are deleting the last item in the list, root will be null so setLast does not need to be used.
            if (this.root != null) {
                this.root.setLast(null);
            }
        } else {
            lastBlock.setNext(nextBlock);
        }

        if (nextBlock != null) {
            nextBlock.setLast(lastBlock);
        }

        this.size--;
    }

    public void filterBy(String filter){
        Block currentBlock = this.root;

        while (currentBlock != null){
            currentBlock.filterBy(filter);
            currentBlock = currentBlock.getNext();
        }
    }

    public boolean deleteItem(String hashCode){
        Block currentBlock = this.root;

        while (currentBlock != null){
            if (currentBlock.deleteItem(hashCode)){
                return true;
            }
            currentBlock = currentBlock.getNext();
        }
        return false;
    }

    public boolean editItem(String hashCode, String newType, String newValue){
        Block currentBlock = this.root;

        while (currentBlock != null){
            if (currentBlock.editItem(hashCode, newType, newValue)){
                return true;
            }
            currentBlock = currentBlock.getNext();
        }
        return false;
    }

    public boolean addToBlock(String hashCode, String newType, String newValue){
        Block currentBlock = this.root;

        while (currentBlock != null){
            String currentHashCode = currentBlock.toString();
            if (currentHashCode.equals(hashCode)){
                Item newItem = new Item(newType, newValue);
                currentBlock.addItem(newItem);
                return true;
            }
            currentBlock = currentBlock.getNext();
        }
        return false;
    }

    public void deleteBlock(String blockHashCode){
        Block currentBlock = this.root;

        for (int i = 0; i<this.size; i++){
            String currentBlockHashCode = currentBlock.toString();
            if (currentBlockHashCode.equals(blockHashCode)){
                this.deleteByIndex(i);
                return;
            }
            currentBlock = currentBlock.getNext();
        }
    }
}
