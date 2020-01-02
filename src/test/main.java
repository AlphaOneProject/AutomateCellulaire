package test;

import control.Game;

public class main {
    
    public static void main(String[] args) {
        
        int delta = 1;
        System.out.println((((delta-1-(((-1 % 9)) + 9) % 9) % 9) + 9) % 9);
        Game.getInstance().start();
    }
}
