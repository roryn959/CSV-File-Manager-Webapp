package rory.model;

public class Result {
    //Result class intended for storing a result of a search,
    //useful for tracking the exact position of each result.
    private String list;
    private String type;
    private String text;

    private int row;
    private int column;

    public Result(Item item){
        this.type = item.getType();
        this.text = item.getValue();
    }

    public void setList(String list){
        this.list = list;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column){
        this.column = column;
    }

    public String getList(){
        return this.list;
    }

    public String getType(){
        return this.type;
    }

    public String getText(){
        return this.text;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }
}
