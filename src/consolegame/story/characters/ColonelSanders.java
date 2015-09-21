package consolegame.story.characters;

import consolegame.story.Character;

public class ColonelSanders extends Character {

    @Override
    public String name() {
        return "Colonel Sanders";
    }

    @Override
    public void formattedPrint(String message) {
        System.out.println("[" + name() + "] " + message);
    }

}
