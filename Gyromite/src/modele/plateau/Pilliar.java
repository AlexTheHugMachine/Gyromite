package modele.plateau;

import modele.deplacements.Colonne;
import modele.deplacements.Direction;

import java.util.ArrayList;
import java.util.Random;

public class Pilliar {

    private int taille;
    private String couleur;
    private ArrayList<PillarBlock> listBlock = new ArrayList<PillarBlock>();
    private Jeu jeu;

    public Pilliar (Jeu _jeu, int taille,String couleur){
        this.jeu = _jeu;
        this.taille = taille;
        for (int i =0; i < taille; i++){
            if(couleur=="rouge") this.listBlock.add(new PillarRed(_jeu));
        }
        this.couleur= couleur;
    }

    public int getTaille(){
        return taille;
    }

    public void setTaille(int taille){
        this.taille = taille;
    }

    public String getCouleur(){
        return couleur;
    }

    public void setCouleur(String couleur){
        this.couleur = couleur;
    }

    public ArrayList<PillarBlock> getListBlock(){
        return listBlock;
    }



}

/*
@startuml
+class Pilliar{
- taille : int
- couleur : String
- listBlock : ArrayList<PillarBlock>
- jeu : Jeu
--
+ Pilliar(Jeu _jeu, int taille,String couleur)
+ getTaille() : int
+ setTaille(int taille) : void
+ getCouleur() : String
+ setCouleur(String couleur) : void
+ getListBlock() : ArrayList<PillarBlock>
}
@enduml
 */