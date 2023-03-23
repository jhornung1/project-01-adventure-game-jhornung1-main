package adventure_game.items;

import adventure_game.Character;
import adventure_game.Game;

/**
 * The class that declares Portkey
 * as an actual object, doesn't serve much
 * of a purpose.
 */
public class Portkey implements Consumable{

    /**
     * This doesn't do anything, as once the player picks up the
     * portkey, the game ends
     */
    public void consume(Character player, Character enemy) {
    }
}
