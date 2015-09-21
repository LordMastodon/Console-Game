package consolegame.story.misc;

import consolegame.story.Character;

public class Narrator extends Character {

    @Override
    public String name() {
        return "Narrator";
    }

    @Override
    public void formattedPrint(String message) {
        System.out.println("[" + name() + "] " + message);
    }

}
