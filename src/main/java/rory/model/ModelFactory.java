package rory.model;

public class ModelFactory {
    private static Model m;

    public static Model getModel(){
        if (m == null){
            m = new Model();
        }
        return m;
    }
}
