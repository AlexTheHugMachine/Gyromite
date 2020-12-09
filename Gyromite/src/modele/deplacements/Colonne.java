package modele.deplacements;

import modele.plateau.Entite;
import modele.plateau.EntiteDynamique;
import modele.plateau.PillarBlock;
import modele.plateau.PillarRed;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * A la reception d'une commande, toutes les cases (EntitesDynamiques) des colonnes se déplacent dans la direction définie
 * (vérifier "collisions" avec le héros)
 */
public class Colonne extends RealisateurDeDeplacement {
    private static Colonne colonne;
    private Direction directionCouranteR = Direction.haut;
    private boolean deplacementR = false;
    private Direction directionCourante= Direction.haut;

    public static Colonne getInstance(){
        if(colonne ==null){
            colonne = new Colonne();
        }
        return colonne;
    }

    private void setGetDirectionCourante(Direction _directionCourante){
        this.directionCourante = _directionCourante;
    }

    public void setDirectionCouranteR(){
        this.deplacementR = true;
        switch (this.directionCouranteR){
            case bas:
                setGetDirectionCourante(Direction.haut);
                this.directionCouranteR = Direction.haut;
                break;
            case haut:
                setGetDirectionCourante(Direction.bas);
                this.directionCouranteR = Direction.bas;
                break;
        }
    }

    @Override
    public boolean realiserDeplacement() {
        boolean ret = false;
        ArrayList<PillarBlock> listBlocks = new ArrayList<>();
        if (this.directionCourante == Direction.haut){
            ListIterator<EntiteDynamique> iterator = lstEntitesDynamiques.listIterator();
            while (iterator.hasNext()){
                Entite e = iterator.next();
                if (e instanceof PillarBlock) listBlocks.add((PillarBlock)e);
            }
        }
        else {
            ListIterator<EntiteDynamique> iterator = lstEntitesDynamiques.listIterator(lstEntitesDynamiques.size());
            while (iterator.hasPrevious()){
                Entite e = iterator.previous();
                if(e instanceof PillarBlock) listBlocks.add((PillarBlock)e);
            }
        }
        for (PillarBlock e : listBlocks){
            if((deplacementR && e instanceof PillarRed)){
                Entite cible = e.regarderDansLaDirection(directionCourante);
                if (cible==null){
                    ret = deplacement(e,directionCourante);
                } else if(cible.peutEtreEcrase()){
                    e.tuerEntite(cible);
                    ret= deplacement(e,directionCourante);
                }
            }
        }
        if(!ret){
            this.deplacementR=false;
        }
        return ret;
        }

        private boolean deplacement(PillarBlock e, Direction directionCourante){
        boolean ret = false;
        switch (directionCourante){
            case haut:
                if(e.avancerDirectionChoisie(Direction.haut))
                    ret = true;
                break;
            case bas:
                if (e.avancerDirectionChoisie(Direction.bas))
                    ret = true;
                break;
        }
        return ret;
        }

    } // TODO

