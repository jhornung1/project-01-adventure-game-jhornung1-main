package adventure_game;

/**
 * The main class for the creation and the taking of the turn
 * for the player Character
 */

public class Player extends Character{

    /**
     * Constructor for the player character
     * 
     * @param name the name of the player character
     * @param health the max health of the player character
     * @param mana the max mana of the player character
     * @param baseDamage the base damage of the player character
     */

    public Player(String name, int health, int mana, int baseDamage){
        super(name, health, mana, baseDamage);
    }

    @Override

    /**
     * First checks if the player is stunned, and ends the turn if they are.
     * 
     * If they aren't stunned, it prints out the health stats and name of the
     * player character, and the health stats of the target. It then asks if the player
     * wants to attack or defend. It also checks if the player has any items, and if the
     * player has items, asks if they want to use said items.
     * 
     * The calls methods, attack(), defend(), or useItems() depending on what the player chose.
     * 
     * @param other the target of the player character
     */

    public void takeTurn(Character other){
        if(this.isStunned()){
            this.decreaseTurnsStunned();
            System.out.printf("%S is unable to take any actions this turn!", this.getName());
            return;
        }
        System.out.println();
        System.out.printf("%s has %d of %d health.\n", this.getName(), this.getHealth(), this.getMaxHealth());
        System.out.printf("%s has %d health.\n", other.getName(), other.getHealth());
        System.out.printf("Do you want to...\n");
        System.out.printf("  1: Attack?\n");
        System.out.printf("  2: Defend?\n");
        if(this.hasItems())
            System.out.printf("  3: Use an item?\n");
        System.out.printf("Enter your choice: ");

        int choice = Game.in.nextInt();
        switch(choice){
            case 1:
                this.attack(other);
                break;
            case 2:
                this.defend(other);
                break;
            case 3:
                if(hasItems()){
                    this.useItem(this, other);
                } else {
                    System.out.println("You dig through your bag but find no items. You lose a turn!!");
                }
                break;
        }
    }
}