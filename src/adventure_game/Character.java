package adventure_game;
import java.util.ArrayList;

import adventure_game.items.Consumable;

/** 
 * Abstract class Character is used to create a character
 * and is extended by Class NPC and  Class Player. It 
 * contains all the methods used by the two other classes,
 * and is used for the game.
 */

abstract public class Character{
    private int maxHealth;
    private int health;

    private int maxMana;
    private int mana;

    private int baseDamage;

    private String name;

    /**
     * The list of consumables the player has
     */
    public ArrayList<Consumable> items;

    // Character Conditions:
    private int turnsVulnerable; // number of turns Character is vulnerable
    private int turnsInvincible; // number of turns Character takes no damage
    private int turnsStunned; // number of turns Character gets no actions
    // buffer factor for next attack
    // E.g, if 2.0, the next attack will do double damage
    private double tempDamageBuff;

    /**
     * The Character() constructor is used to create a new Character
     * @param name is the name of the character
     * @param health is the starting health of the character
     * @param mana is the starting mana of the character
     * @param damage is the damage the character can do
     */
    public Character(String name, int health, int mana, int damage){
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.maxMana = mana;
        this.mana = mana;
        this.baseDamage = damage;
        this.tempDamageBuff = 1.0;
        items = new ArrayList<Consumable>();
    }

    /**
     * toString() @return all of the attributes in the Character
     * in String format
     */
    @Override
    public String toString(){
        String output;
        output = "";
        output += "Name " + getName() + "\n";
        output += "hp " + getHealth() + "\n";
        output += "mana " + getMana() + "\n";
        output += "damage " + getBaseDamage() + "\n";
        return output;
    }

    /**
     * Get the name of this Character
     * @return the name of this Character
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the current health of this Character
     * @return the current health of this Character
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Get the max health of this Character
     * @return the max health of this Character
     */
    public int getMaxHealth(){
        return this.maxHealth;
    }

    /**
     * Get the max mana of this Character
     * @return the max mana of this Character
     */
    public int getMaxMana(){
        return this.maxMana;
    }

    /**
     * Get the current mana of this Character
     * @return the current mana of this Character
     */
    public int getMana(){
        return this.mana;
    }

    /**
     * Get the base damage of this Character
     * @return the base damage of this Character
     */
    public int getBaseDamage(){
        return this.baseDamage;
    }

    /**
     * Check if this Character is alive
     * @return true is this Character is alive, if not
     * it returns false
     */
    public boolean isAlive(){
        return this.health > 0;
    }

    /**
     * Get the current damage buff of this Character
     * 
     * @return the current damage buff of this Character
     */
    public double getTempDamageBuff(){
        return this.tempDamageBuff;
    }

    abstract void takeTurn(Character other);

    /**
     * Attack first checks if the other character is invincible,
     * if the other character is invincible, prints a message
     * saying so and ends the method
     * 
     * If the other character is not invincible, creates a new random damage modifier
     * to create  new damage value. 
     * 
     * Checks if the other character is vulnerable, and if so, adds a 50%
     * damage boost
     * 
     * The napplies damage and prints out a message saying how much damage was dealt
     * 
     * @param other is the Character recieving the attack
     */
    public void attack(Character other){
        if(other.isInvincible()){
            System.out.printf("%S is unable to attack %S!\n", 
                                this.getName(), 
                                other.getName());
            other.decreaseTurnsInvincible();
            return;
        }
        double modifier = Game.rand.nextDouble();
        modifier = (modifier*0.4) + 0.8;
        int damage = (int)(this.baseDamage * modifier);
        // apply temporary damage buff, then reset it back to 1.0
        damage *= this.tempDamageBuff;
        this.tempDamageBuff = 1.0;

        if(other.isVulnerable()){
            damage *= 1.5;
            other.decreaseTurnsVulnerable();
        }

        System.out.printf("%s dealt %d damage to %s\n", 
                            this.getName(), 
                            damage, 
                            other.getName());
        other.modifyHealth(-damage);
    }

    /**
     * Randomly decides between giving a turn of invincibility and boosting
     * damage of the main character (75% chance), or becoming vulnerable
     * to the other character's attack.
     * 
     * @param other is the Character attacking the main Character (isn't currently
     * used in the code)
     */
    public void defend(Character other){
        double chance = Game.rand.nextDouble();
        if(chance <=0.75){
            System.out.printf("%s enters a defensive posture and charges up their next attack!\n", this.getName());
            this.setAsInvincible(1);
            this.setTempDamageBuff(2.0);
        } else {
            System.out.printf("%s stumbles. They are vulnerable for the next turn!\n", this.getName());
            this.setAsVulnerable(1);
        }
    }
    
    /**
     * Adds the modifier to current health, then checks if health is less than
     * zero or is above maxHealth. If it's below zero, sets health to zero
     * and if its above maxHealth, sets it to maxHealth.
     * 
     * @param modifier the amount of health that will be modified
     */
    public void modifyHealth(int modifier) {
        this.health += modifier;
        if(this.health < 0){
            this.health = 0;
        }
        if(this.health > this.getMaxHealth()){
            this.health = this.getMaxHealth();
        }
    }

    /* CONDITIONS */

    /**
     * Sets the character to be vulnerable for the number of turns
     * 
     * @param numTurns the number of turns the Character should be set as vulnerable for
     */
    public void setAsVulnerable(int numTurns){
        this.turnsVulnerable = numTurns;
    }

    /**
     * Checks if vulnerable
     * 
     * @return false if not vulnerable, true if vulnerable
     */
    public boolean isVulnerable(){
        return this.turnsVulnerable > 0;
    }

    /**
     * Removes one turn of vulnerability
     */
    public void decreaseTurnsVulnerable(){
        this.turnsVulnerable--;
    }

    /**
     * Sets the character to be invincible for the number of turns
     * 
     * @param numTurns the number of turns the Character should be set as invincible for
     */
    public void setAsInvincible(int numTurns){
        this.turnsInvincible = numTurns;
    }

    /**
     * Checks if invincible
     * 
     * @return false if not invincible, true if invincible
     */
    public boolean isInvincible(){
        return this.turnsInvincible > 0;
    }

    /**
     * Removes one turn of invincibility
     */
    public void decreaseTurnsInvincible(){
        this.turnsInvincible--;
    }

    /**
     * Sets the character to be stunned for the number of turns
     * 
     * @param numTurns the number of turns the Character should be set as stunned for
     */
    public void setAsStunned(int numTurns){
        this.turnsStunned = numTurns;
    }

    /**
     * Checks if stunned
     * 
     * @return false if not stunned, true if stunned
     */
    public boolean isStunned(){
        return this.turnsStunned > 0;
    }

    /**
     * Removes one turn of stun
     */
    public void decreaseTurnsStunned(){
        this.turnsStunned--;
    }

    /**
     * Set the temporary damage buffer. 
     * 
     * This is a multiplicative factor which will modify the damage 
     * for the next attack made by this Character. After the next 
     * attack, it will get reset back to 1.0
     * 
     * @param buff the multiplicative factor for the next attack's
     * damage.
     */
    public void setTempDamageBuff(double buff){
        this.tempDamageBuff = buff;
    }

    /**
     * Adds one consumable to the Character's inventory/storage
     * 
     * @param item the item added to the Character
     */
    public void obtain(Consumable item){
        items.add(item);
    }

    /**
     * 
     * @param owner the owner of the item being used
     * @param other the other character the item effects
     */
    public void useItem(Character owner, Character other){
        int i = 1;
        System.out.printf("  Do you want to use:\n");
        for(Consumable item : items){
            System.out.printf("    %d: %S\n", i, item.getClass().getName());
            i++;
        }
        System.out.print("  Enter your choice: ");
        int choice = Game.in.nextInt();
        items.get(choice-1).consume(owner, other);
        items.remove(choice-1);
    }

    /**
     * Checks if the Character has any items
     * 
     * @return false if no items, true if they have items
     */
    public boolean hasItems(){
        return !items.isEmpty();
    }


    /**
     * Cuts the opponents health in half and 
     * reduces the players mana by 3
     * 
     * @param other is the opponent
     */
    public void castSpell(Character other) {
        int damageMod = other.getHealth() / 2 * -1;
        other.modifyHealth(damageMod);
        this.mana -= 3;
        System.out.printf("\nYou cut %s's health in half, and %s now has $d health left.\n", other.getName(), other.getName(), other.getHealth());
    }

    /**
     *  Increments the player's mana and prints out current mana
     */
    public void chargeMana() {
        this.mana += 1;
        System.out.printf("\nYou currently have %d mana\n", getMana());
    }
}
