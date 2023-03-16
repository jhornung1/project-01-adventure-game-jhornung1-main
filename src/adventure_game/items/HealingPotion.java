package adventure_game.items;

import adventure_game.Character;
import adventure_game.Game;

/** 
 * Class Healing Potion implements Consumabale and
 * is used to consume health potions to restore health.
 */

public class HealingPotion implements Consumable {
    /**
     * Method consume() uses up a health potion to restore health
     * using the priavte method calculateHealing() to determine how 
     * much health it restores.
     * 
     * It then outputs how much the player is healed.
     * @param owner is the Character consuming the potion.
     * @param other is the current target of the player (not used)
     */
    public void consume(Character owner, Character other){
        int hitPoints = calculateHealing();
        int hitPointsFromMax = owner.getMaxHealth() - owner.getHealth();

        if(hitPoints > hitPointsFromMax){
            hitPoints = hitPointsFromMax;
        }
        System.out.printf("You heal for %d points, back up to %d/%d.\n", hitPoints, owner.getHealth(), owner.getMaxHealth());
        owner.modifyHealth(hitPoints);
    }

    private int calculateHealing(){
        // Equivalent to rolling 4d4 + 4
        // sum up four random values in the range [1,4] and
        // add 4 to that.
        int points = Game.rand.nextInt(4)+1;
        points += Game.rand.nextInt(4)+1;
        points += Game.rand.nextInt(4)+1;
        points += Game.rand.nextInt(4)+1;
        return points + 4;
    }
}
