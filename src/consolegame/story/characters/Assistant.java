package consolegame.story.characters;

import consolegame.story.Character;

public class Assistant extends Character {

    @Override
    public String name() {
        return "Assistant";
    }

    @Override
    public void formattedPrint(String message) {
        System.out.println("[" + name() + "] " + message);
    }
}
