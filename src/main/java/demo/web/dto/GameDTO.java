package demo.web.dto;

/**
 * Created by Benoit on 19/03/2015.
 */
public class GameDTO {

    private int id;
    private String name;
    private Type type;

    public enum  Type{
        RPG,FPS;
    }

    public GameDTO(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
