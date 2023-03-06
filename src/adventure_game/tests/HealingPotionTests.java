
package adventure_game.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adventure_game.Player;
import adventure_game.items.HealingPotion;

import org.junit.jupiter.api.BeforeEach;
public class HealingPotionTests {

    private Player p;
    @BeforeEach
    void setup(){
        p = new Player("Hero", 100, 9, 7);
        p.obtain(new HealingPotion());
    }

    @Test
    void testHealingPotion(){
        p.modifyHealth(-20);
        assertTrue(p.getHealth() == 80);
        p.items.get(0).consume(p);
        assertTrue(p.getHealth() == 96);
    }
}