package adventure_game.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adventure_game.Player;
import adventure_game.NPC;
import adventure_game.Game;
import adventure_game.items.HealingPotion;

import org.junit.jupiter.api.BeforeEach;
public class HealingPotionTests {

    private Player p;
    private NPC c;
    @BeforeEach
    void setup(){
        p = new Player("Hero", 100, 9, 7);
        c = new NPC("Test", 100, 9, 7);
        p.obtain(new HealingPotion());
        Game.rand.setSeed(123456);
    }

    @Test
    void testHealingPotion(){
        p.modifyHealth(-20);
        assertTrue(p.getHealth() == 80);
        p.items.get(0).consume(p, c);
        assertTrue(p.getHealth() == 96);
    }
}