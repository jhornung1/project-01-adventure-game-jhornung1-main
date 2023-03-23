package adventure_game.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import adventure_game.Character;
import adventure_game.Player;
import adventure_game.NPC;
import adventure_game.items.*;

import org.junit.jupiter.api.BeforeEach;

public class PlayerTests{

    private Player c;
    private Character other;

    @BeforeEach
    void setup(){
        c = new Player("Hero", 100, 9, 7);
        other = new NPC("Enemy", 200, 8, 8);
    }

    @Test
    void testHasPortkey() {
        c.obtain(new Portkey());
        assertTrue(c.hasPortkey());
    }
}
