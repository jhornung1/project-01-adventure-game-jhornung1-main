package adventure_game;
/**
 * Public class NPC extends Character and allows the creator of the
 * game to create Character's that aren't the playable character.
 * 
 * Basically allows for the creation of Non Player Characters (hence, NPC)
 * and deciding how they take their turns
 */
public class NPC extends Character{

    /**
     * Contructor to make a new NPC
     * 
     * @param name the name of the NPC
     * @param health the max health of the NPC
     * @param mana the max mana of the NPC
     * @param baseDamage the base damage of the NPC
     */

    public NPC(String name, int health, int mana, int baseDamage){
        super(name, health, mana, baseDamage);
    }
    
    @Override
    /**
     * Checks if the NPC is stunned, and ends the turn if so.
     * 
     * If the NPC isn't stunned, they attack the other Character
     * 
     * @param other the target Character of the NPC
     */
    public void takeTurn(Character other){
        if(this.isStunned()){
            this.decreaseTurnsStunned();
            System.out.printf("%S is unable to take any actions this turn!", this.getName());
            return;
        }
        this.attack(other);
    }
}