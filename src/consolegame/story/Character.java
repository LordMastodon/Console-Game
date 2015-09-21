package consolegame.story;

public abstract class Character {

    private String name;

    public Character(String name) {
        this.name = name;
    }

    public Character() {
        this("");
    }

    public abstract String name();

    public abstract void formattedPrint(String message);

}
