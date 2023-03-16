package adventure_game.items;

import adventure_game.Character;
import adventure_game.Game;

/**
 * Class Spear implements Consumable and is used
 * to instantly kill a single enemy and use up the item
 */

public class Spear implements Consumable{

    /**
     * Consume method uses the spear, and has a 50%
     * chance of either taking half of the user's current health
     * away, or instantly killing the enemy.
     * 
     * @param owner is the character using the spear
     * @param other is the current target of the player
     */

    public void consume(Character owner, Character other) {
        int chance = Game.rand.nextInt();
        if (chance % 2 == 1) {
            owner.modifyHealth(-(owner.getHealth() / 2));
            System.out.printf("\nYou tried to use the Spear, but failed and lost half of your health. You now have %d health remaining.\n", owner.getHealth());
        } else {
            other.modifyHealth(-(other.getHealth()));
            System.out.printf("\nYou used the Spear and instantly killed your enemy.\n");
        }
    }
}
