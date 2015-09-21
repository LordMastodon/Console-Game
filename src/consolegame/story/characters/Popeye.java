package consolegame.story.characters;

import consolegame.story.Character;

public class Popeye extends Character {

    @Override
    public String name() {
        return "Popeye";
    }

    @Override
    public void formattedPrint(String message) {
        System.out.println("[" + name() + "] " + message);
    }

}
