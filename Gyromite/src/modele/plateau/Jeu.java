/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import modele.deplacements.*;

import java.awt.Point;
import java.util.HashMap;

/** Actuellement, cette classe gère les postions
 * (ajouter conditions de victoire, chargement du plateau, etc.)
 */
public class Jeu {

    public static final int SIZE_X = 60;
    public static final int SIZE_Y = 20;

    // compteur de déplacements horizontal et vertical (1 max par défaut, à chaque pas de temps)
    private HashMap<Entite, Integer> cmptDeplH = new HashMap<Entite, Integer>();
    private HashMap<Entite, Integer> cmptDeplV = new HashMap<Entite, Integer>();

    private Heros hector;

    private HashMap<Entite, Point> map = new  HashMap<Entite, Point>(); // permet de récupérer la position d'une entité à partir de sa référence
    private Entite[][][] grilleEntites = new Entite[SIZE_X][SIZE_Y][2]; // permet de récupérer une entité à partir de ses coordonnées

    private Ordonnanceur ordonnanceur = new Ordonnanceur(this);

    private int NbrBombes = 4;
    private int NbrVies = 3;
    private int score = 0;

    public Jeu() {
        initialisationDesEntites();
    }

    public void resetCmptDepl() {
        cmptDeplH.clear();
        cmptDeplV.clear();
    }

    public void start(long _pause) {
        ordonnanceur.start(_pause);
    }

    public Entite[][][] getGrille() {
        return grilleEntites;
    }

    public Heros getHector() {
        return hector;
    }

    public boolean respawn(){
        Point p = map.get(hector);
        if (NbrVies > 0) {
            supprimeEntite(p.x, p.y, 1);
            addEntite(hector, hector.getSpawn_X(), hector.getSpawn_Y(), 1);
            NbrVies--;
        }
        if (NbrVies == 0) {
            supprimeEntite(p.x, p.y, 1);
        }
        score = score - 100;
        return true;
    }

    public boolean killSmick(Bot e){
        Point positionSmick = map.get(e);
        supprimeEntite(positionSmick.x,positionSmick.y,1);
        //e.getIA().removeEntiteDynamique(e);
        //Gravite.getInstance().removeEntiteDynamique(e);
        score += 20;
        return true;
    }

    public void generatePillar(Pilliar pilliar, int x , int y){
        for(PillarBlock p: pilliar.getListBlock()){
            addEntite(p,x,y++,1);
            Colonne.getInstance().addEntiteDynamique(p);
        }
    }

    private void initialisationDesEntites() {
        hector = new Heros(this,2,1);
        addEntite(hector, hector.getSpawn_X(), hector.getSpawn_Y(), 1);

        Controle4Directions.getInstance().addEntiteDynamique(hector);
        ordonnanceur.add(Controle4Directions.getInstance());

        // murs extérieurs horizontaux
        for (int x = 0; x < 60; x++) {
            addEntite(new Mur_Horizontal(this), x, 0,0);
            addEntite(new Brick(this), x, 19,0);
            addEntite(new Brick(this), x, 18,0);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < 18; y++) {
            addEntite(new Mur_Horizontal(this), 0, y,0);
            addEntite(new Mur_Horizontal(this), 59, y,0);
        }

        for (int x = 1; x < 15; x++) {
            addEntite(new Mur_Horizontal(this), x, 6,0);
            addEntite(new Mur_Horizontal(this), x, 9,0);
            addEntite(new Mur_Horizontal(this), x, 12,0);
            addEntite(new Mur_Horizontal(this), x, 15,0);
        }

        for (int x = 16; x < 59; x++) {
            addEntite(new Mur_Horizontal(this), x, 6,0);
            addEntite(new Mur_Horizontal(this), x, 9,0);
            addEntite(new Mur_Horizontal(this), x, 12,0);
            addEntite(new Mur_Horizontal(this), x, 15,0);
        }

        /*addEntite(new Mur_Horizontal(this), 2, 6,0);
        addEntite(new Mur_Horizontal(this), 3, 6,0);
        addEntite(new Mur_Horizontal(this), 10, 8,0);
        addEntite(new Mur_Horizontal(this), 5, 6,0);
        addEntite(new Mur_Horizontal(this), 4, 8,0);
        addEntite(new Mur_Horizontal(this), 5, 8,0);
        addEntite(new Mur_Horizontal(this), 6, 8,0);*/


        Bot smick1 = new Bot(this);
        addEntite(smick1, 17, 8,1);
        Bot smick2 = new Bot(this);
        addEntite(smick2, 17, 11,1);
        Bot smick3 = new Bot(this);
        addEntite(smick3, 17, 14,1);

        Pilliar pilliar1 = new Pilliar(this,3,"rouge");
        generatePillar(pilliar1,15,6);
        ordonnanceur.add(Colonne.getInstance());

        addEntite(new Corde(this), 4, 6,0);
        addEntite(new Corde(this), 4, 7,0);
        addEntite(new Corde(this), 4, 8,0);

        addEntite(new Corde(this), 48, 9,0);
        addEntite(new Corde(this), 48, 10,0);
        addEntite(new Corde(this), 48, 11,0);

        addEntite(new Corde(this), 36, 12,0);
        addEntite(new Corde(this), 36, 13,0);
        addEntite(new Corde(this), 36, 14,0);

        addEntite(new Corde(this), 20, 15,0);
        addEntite(new Corde(this), 20, 16,0);
        addEntite(new Corde(this), 20, 17,0);
        /*addEntite(new Corde(this), 4, 18,0);
        addEntite(new Corde(this), 4, 17,0);
        addEntite(new Corde(this), 4, 16,0);*/

        IA ia = new IA();
        ia.addEntiteDynamique(smick1);
        ia.addEntiteDynamique(smick2);
        ia.addEntiteDynamique(smick3);
        ordonnanceur.add(ia);

        /*Colonne colonne = new Colonne();
        colonne.addEntiteDynamique(pilliar1);
        colonne.addEntiteDynamique(pilliar2);
        ordonnanceur.add(colonne);*/

        Gravite g = new Gravite();
        g.addEntiteDynamique(hector);
        g.addEntiteDynamique(smick1);
        g.addEntiteDynamique(smick2);
        g.addEntiteDynamique(smick3);
        ordonnanceur.add(g);

        addEntite(new Bombe(this),8,5,1);
        addEntite(new Bombe(this),9,5,1);
        addEntite(new Bombe(this),10,5,1);
        addEntite(new Bombe(this),11,5,1);
    }

    private void addEntite(Entite e, int x, int y,int dynamique) {
        grilleEntites[x][y][dynamique] = e;
        map.put(e, new Point(x, y));
    }

    private void supprimeEntite(int x , int y , int dynamique){
        grilleEntites[x][y] [dynamique] =null;
    }
    /** Permet par exemple a une entité  de percevoir sont environnement proche et de définir sa stratégie de déplacement
     *
     */
    public Entite regarderDansLaDirection(Entite e, Direction d) {
        Point positionEntite = map.get(e);
        return objetALaPosition(calculerPointCible(positionEntite, d));
    }

    /** Si le déplacement de l'entité est autorisé (pas de mur ou autre entité), il est réalisé
     * Sinon, rien n'est fait.
     */
    public boolean deplacerEntite(EntiteDynamique e, Direction d) {
        boolean retour = false;

        Point pCourant = map.get(e);



        Point pCible = calculerPointCible(pCourant, d);
        Entite eCible = objetALaPosition(pCible);
        if (contenuDansGrille(pCible) && (
                eCible == null  ||
                        !eCible.peutServirDeSupport() || //mur
                        eCible.peutPermettreDeMonterDescendre() ||
                        eCible.peutEtreRamasse(e)//corde
        )) { // a adapter (collisions murs, etc.)
            // compter le déplacement : 1 deplacement horizontal et vertical max par pas de temps par entité
            if (cmptDeplV.get(e) == null || e.tuerEntite(eCible)) {
                switch (d) {
                    case bas: case haut:
                        if (!e.tuerEntite(eCible) || eCible.tuerEntite(e)) {
                            cmptDeplV.put(e, 1);
                            retour = true;
                        }
                        break;
                    case gauche: case droite:
                        cmptDeplH.put(e, 1);
                        retour = true;
                        break;
                }
            }
        }

        if (retour) {
            deplacerEntite(pCourant, pCible, e);
            if(eCible!= null && eCible.peutEtreRamasse(e)){
                supprimeEntite(pCible.x,pCible.y,0);
                NbrBombes = NbrBombes -1;
                score = score + 100;
            }
        }

        return retour;
    }


    private Point calculerPointCible(Point pCourant, Direction d) {
        Point pCible = null;

        switch(d) {
            case haut: pCible = new Point(pCourant.x, pCourant.y - 1); break;
            case bas : pCible = new Point(pCourant.x, pCourant.y + 1); break;
            case gauche : pCible = new Point(pCourant.x - 1, pCourant.y); break;
            case droite : pCible = new Point(pCourant.x + 1, pCourant.y); break;

        }

        return pCible;
    }

    private void deplacerEntite(Point pCourant, Point pCible, Entite e) {
        grilleEntites[pCourant.x][pCourant.y] [1]= null;
        grilleEntites[pCible.x][pCible.y] [1] = e;
        map.put(e, pCible);
    }

    /** Indique si p est contenu dans la grille
     */
    private boolean contenuDansGrille(Point p) {
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }

    private Entite objetALaPosition(Point p) {
        Entite retour = null;

        if (contenuDansGrille(p)) {
            if (grilleEntites[p.x][p.y][1]== null)
                retour = grilleEntites[p.x][p.y][0];
            else retour = grilleEntites[p.x][p.y][1];
        }

        return retour;
    }

    public Ordonnanceur getOrdonnanceur() {
        return ordonnanceur;
    }

    public String[][] getScore(){
        String[][] score = {{
            "Points: ", String.valueOf(this.score),
            "Vies: ", String.valueOf(this.NbrVies),
            "Bombes: ", String.valueOf(this.NbrBombes),
        }};
        return score;
    }
}

/*
@startuml
+class Jeu{
+ SIZE_X : static final int
+ SIZE_Y : static final int
- cmptDeplH : HashMap<Entite, Integer>
- cmptDeplV : HashMap<Entite, Integer>
- hector : Heros
- map : HashMap<Entite, Point>
- grilleEntites : Entite[][][]
- ordonnanceur : Ordonnanceur
- NbrBombes : int
- NbrVies : int
- score : int
--
+ Jeu()
+ resetCmptDepl() : void
+ start(long _pause) : void
+ getGrille() : Entite[][][]
+ getHector() : Heros
+ respawn() : boolean
+ killSmick() : boolean
+ generatePillar(Pilliar pilliar, int x , int y) : void
+ regarderDansLaDirection(Entite e, Direction d) : Entite
+ deplacerEntite(EntiteDynamique e, Direction d) : boolean
+ getOrdonnanceur() : Ordonnanceur
+ getScore() : String[][]
--
- initialisationDesEntites() : void
- addEntite(Entite e, int x, int y,int dynamique) : void
- supprimeEntite(int x , int y , int dynamique) : void
- calculerPointCible(Point pCourant, Direction d) : Point
- deplacerEntite(Point pCourant, Point pCible, Entite e) : void
- contenuDansGrille(Point p) : boolean
- objetALaPosition(Point p) : Entite
}
@enduml
 */