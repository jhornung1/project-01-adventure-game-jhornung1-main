package adventure_game;

/*
 * Project-01: Adventure Game
 * Name: Jacob Hornung
 */

import java.util.Scanner;

import adventure_game.items.HealingPotion;

import java.util.Random;

/**
 * Primary class of the game, and where the main method is ran in.
 * 
 * Iterates over each turn of the game, while also creating the characters,
 * scanner, and Random.
 */

public class Game {
    static Scanner in = new Scanner(System.in);
    // This random variable is currently seeded for testing
    public static Random rand = new Random(123456);
    private Player player;
    
    public static void main(String[] args){

        Game game = new Game();

        game.createPlayer();
        System.out.println(game.player.toString());

        NPC opponent = new NPC("Geoff", 200, 0, 10);
        System.out.println(opponent.toString());
        game.enterCombat(opponent);

        in.close();
    }

    /**
     * Blank Constructor for the game, purely to have the Scanner,
     * Random, and Player.
     */

    public Game() {
        
    }

    /**
     * Creates the Player character
     */

    public void createPlayer(){
        /* TO-DO */
        /* Modify this method to allow the user to create their own player */
        /* The user will specify the player's name and description, and spend */
        /* 20 points on health, mana, and baseDamage as they see fit. */
        player = new Player("The Hero", 100, 9, 7);
        player.obtain(new HealingPotion());
    }

    /**
     * Iterates one turn each (player and opponent), until
     * one of them isn't alive
     * 
     * @param opponent the opposing Character the Player is
     * against.
     */
    
    public void enterCombat(NPC opponent){
        System.out.printf("%s and %s are in a brawl to the bitter end.\n", this.player.getName(), opponent.getName());
        while(true){
            this.player.takeTurn(opponent);
            if(!opponent.isAlive()){
                System.out.printf("%S is SLAIN!!\n",opponent.getName());
                break;
            }

            opponent.takeTurn(this.player);
            if(!this.player.isAlive()){
                System.out.printf("%S is SLAIN!!\n",this.player.getName());
                break;
            }
        }
    }
}
