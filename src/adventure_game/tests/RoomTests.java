package adventure_game.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import adventure_game.Room;
import adventure_game.NPC;
import adventure_game.items.*;

import org.junit.jupiter.api.BeforeEach;

public class RoomTests{
    private Room c;

    @BeforeEach 
    void setup() {
        c = new Room(0, "Typ", "hola");
    }

    @Test
    void testHasItem() {
        assertTrue(c.hasItem() == false);
        c.setItems(new HealingPotion());
        assertTrue(c.hasItem());
    }

    @Test
    void testHasEnemy() {
        assertTrue(c.hasEnemy() == false);
        c.setEnemy(new NPC("Barry", 200, 200, 200));
        assertTrue(c.hasEnemy());
    }
}