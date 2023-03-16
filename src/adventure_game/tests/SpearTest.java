package adventure_game.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adventure_game.Player;
import adventure_game.NPC;
import adventure_game.items.Spear;
import adventure_game.Game;

import org.junit.jupiter.api.BeforeEach;

public class SpearTest {
  
    private Player p;
    private NPC c;
    @BeforeEach
    void setup(){
        p = new Player("Hero", 100, 9, 7);
        c = new NPC("Test", 100, 9, 7);
        p.obtain(new Spear());
    }

    @Test
    void testSpearHalfHealth() {
        Game.rand.setSeed(123456);
        p.items.get(0).consume(p, c);
        assertTrue(p.getHealth() == 50);
    }

    @Test
    void testSpearInstaDeath() {
        Game.rand.setSeed(123457);
        p.items.get(0).consume(p, c);
        assertTrue(c.getHealth() == 0);
    }
}

