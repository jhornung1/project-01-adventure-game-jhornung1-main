package adventure_game.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import adventure_game.Character;
import adventure_game.Player;
import adventure_game.NPC;
import adventure_game.items.*;

import org.junit.jupiter.api.BeforeEach;

public class CharacterTests{

    private Character c;
    private Character other;
    @BeforeEach
    void setup(){
        c = new Player("Hero", 100, 9, 7);
        other = new NPC("Enemy", 200, 8, 8);
    }

    @Test
    void testModifyHealth(){
        assertTrue(c.getHealth() == 100);
        c.modifyHealth(-10);
        assertTrue(c.getHealth() == 90);
    }

    @Test
    void testToString(){
        assertTrue(c.toString().equals("Name Hero\nhp 100\nmana 9\ndamage 7\n"));
        c.modifyHealth(-10);
        assertTrue(c.toString().equals("Name Hero\nhp 90\nmana 9\ndamage 7\n"));
    }

    @Test
    void testSetInvincible(){
        assertTrue(!(c.isInvincible()));
        c.setAsInvincible(1);
        assertTrue(c.isInvincible());
    }

    @Test
    void testDecreaseTurnsInvincible(){
        c.setAsInvincible(1);
        assertTrue(c.isInvincible());
        c.decreaseTurnsInvincible();
        assertTrue(!(c.isInvincible()));
    }

    @Test
    void testSetVulnerable(){
        assertTrue(!(c.isVulnerable()));
        c.setAsVulnerable(1);
        assertTrue(c.isVulnerable());
    }

    @Test
    void testDecreaseTurnsVulnerable(){
        c.setAsVulnerable(1);
        assertTrue(c.isVulnerable());
        c.decreaseTurnsVulnerable();
        assertTrue(!(c.isVulnerable()));
    }

    @Test
    void testSetStunned(){
        assertTrue(!(c.isStunned()));
        c.setAsStunned(1);
        assertTrue(c.isStunned());
    }

    @Test
    void testDecreaseTurnsStunned(){
        c.setAsStunned(1);
        assertTrue(c.isStunned());
        c.decreaseTurnsStunned();
        assertTrue(!(c.isStunned()));
    }

    @Test
    void testSetTempDamageBuff(){
        c.setTempDamageBuff(10);
        assertTrue(c.getTempDamageBuff() == 10);
    }

    @Test
    void testAttack(){
        c.attack(other);
        assertTrue(other.getHealth() == 194);
    }

    @Test
    void testDefend() {
        c.defend(other);
        assertTrue(c.isInvincible());
        assertTrue(c.getTempDamageBuff() == 2.0);
    }

    @Test
    void testCastSpell() {
        c.castSpell(other);
        assertTrue(c.getMana() == 6);
        assertTrue(other.getHealth() == 100);
    }

    @Test
    void testChargeMana() {
        c.castSpell(other);
        c.chargeMana();
        assertTrue(c.getMana() == 7);
    }
    
}