package rory.model;

public class LinkedList {
    private Block root;
    private int size;

    public LinkedList(){
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
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
            this.root.setLast(null);
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
}
