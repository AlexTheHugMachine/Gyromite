/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

/**
 * Héros du jeu
 */
public class Heros extends EntiteDynamique {
    private int spawn_X;
    private int spawn_Y;

    public Heros(Jeu _jeu, int x, int y) {
        super(_jeu);
        spawn_X = x;
        spawn_Y = y;
    }

    public boolean tuerEntite(Entite e){
        return false;
    }

    public boolean peutEtreEcrase() { return true; }
    public boolean peutServirDeSupport() { return true; }
    public boolean peutPermettreDeMonterDescendre() { return false; };
    public  boolean peutEtreRamasse(Entite e){ return  false;}

    public int getSpawn_X(){
        return spawn_X;
    }

    public void setSpawn_X(int spawn_X){
        this.spawn_X = spawn_X;
    }

    public int getSpawn_Y(){
        return spawn_Y;
    }

    public void setSpawn_Y(int spawn_Y){
        this.spawn_Y = spawn_Y;
    }
}
