/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import modele.deplacements.*;

import java.util.Random;

/**
 * Ennemis (Smicks)
 */
public class Bot extends EntiteDynamique {
    private Random r = new Random();
    private Direction vert;
    private  Direction hor;
    private IA ia;

    public Bot(Jeu _jeu, IA ia) {

        super(_jeu);
        this.ia = ia;
        if (r.nextBoolean())
            this.hor = Direction.droite;
        else
            this.hor = Direction.gauche;
    }

    public Direction getDirection() {
        if (this.vert == null) return this.hor;
        return this.vert;
    }

    public Direction getDirection(boolean _vert){
        if (_vert) {
            if (this.vert== null){
                if (r.nextBoolean())
                    this.vert = Direction.haut;
                else
                    this.vert = Direction.bas;
            }
            return this.vert;
        }
        this.vert = null;
        return this.hor;
    }

    public Direction changeDirection() {
        Direction dir = this.getDirection();
        switch (dir) {
            case haut:
                return changeDirection(Direction.bas);
            case bas:
                return changeDirection(Direction.haut);
            case droite:
                return changeDirection(Direction.gauche);
            case gauche:
                return  changeDirection(Direction.droite);
            default:
                return dir;
        }
    }

    public Direction changeDirection(Direction _dir){
        switch (_dir){
            case haut: case bas:
                this.vert = _dir;
                break;
            case droite: case gauche:
                this.vert = null;
                this.hor = _dir;
        }
        return _dir;
    }

    public boolean tuerEntite(Entite e){
        if(e instanceof Heros){
            jeu.respawn();
            return true;
        }
        return false;
    }

    public IA getIA(){
        return ia;
    }


    public boolean peutEtreEcrase() { return true; }
    public boolean peutServirDeSupport() { return true; }
    public boolean peutPermettreDeMonterDescendre() { return false; };
    public boolean peutEtreRamasse(Entite e){ return false;}
}

/*
@startuml
+class Bot extends EntiteDynamique {
- r : Random
- vert : Direction
- hor : Direction
- ia : IA
--
+ Bot(Jeu _jeu)
+ getDirection() : Direction
+ getDirection(boolean _vert) : Direction
+ changeDirection() : Direction
+ changeDirection(Direction _dir) : Direction
+ tuerEntite(Entite e) : boolean
+ getIA() : IA
+ peutEtreEcrase() : boolean
+ peutServirDeSupport() : boolean
+ peutPermettreDeMonterDescendre() : boolean
+ peutEtreRamasse(Entite e) : boolean
}
@enduml
 */