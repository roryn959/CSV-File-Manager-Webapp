package rory.model;

public class Item {
    private String value;
    private String type;

    public Item(String type, String value){
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }
}
