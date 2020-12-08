package modele.deplacements;

import modele.plateau.*;

import java.util.ArrayList;


public class IA extends RealisateurDeDeplacement {

    protected boolean realiserDeplacement() {
        boolean ret = false;
        boolean horizontal = true;
        ArrayList<Bot> listBots = new ArrayList<>();
        for (EntiteDynamique e : lstEntitesDynamiques) {
            if (e instanceof Bot) listBots.add((Bot)e);
        }
        for (Bot e : listBots) {
            Direction d = e.getDirection();
            Entite Next = e.regarderDansLaDirection(d); //case vers laquelle on se dirige

            Entite Bas = e.regarderDansLaDirection(Direction.bas);
            Entite[] Adjacents = {null, null}; //cases adjacentes a celle vers laquelle on se dirige
            if (Next != null) {
                switch (d) {
                    case haut: case bas:
                        Adjacents[0] = Next.regarderDansLaDirection(Direction.gauche);
                        Adjacents[1] = Next.regarderDansLaDirection(Direction.droite);
                        horizontal = false;
                        break;
                    case droite: case gauche:
                        Adjacents[0] = Next.regarderDansLaDirection(Direction.haut);
                        Adjacents[1] = Next.regarderDansLaDirection(Direction.bas);
                        horizontal = true;
                        break;
                }
                if (e.tuerEntite(Next)) {
                    //TODO tuer le joueur
                    System.out.println("JOUEUR MORT");
                    if (e.avancerDirectionChoisie(d))
                        ret = true;
                }
                if (horizontal) {
                    if (Next.peutPermettreDeMonterDescendre()) {
                        if (e.avancerDirectionChoisie(d)); {
                            ret = true;
                            if (Adjacents[0].peutPermettreDeMonterDescendre() && Adjacents[1].peutPermettreDeMonterDescendre()) {
                                e.changeDirection(e.getDirection(true));
                            }
                            else if (Adjacents[0].peutPermettreDeMonterDescendre()) {
                                e.changeDirection(Direction.haut);
                            }
                            else if (Adjacents[1].peutPermettreDeMonterDescendre()) {
                                e.changeDirection(Direction.bas);
                            }
                            else ret = false;
                        }
                    }
                    else if (Adjacents[0] == null) {
                        if (e.avancerDirectionChoisie(e.changeDirection(Direction.haut))); {
                            e.changeDirection(d);
                            ret = true;
                        }
                    }
                    else if (Next.peutServirDeSupport()) {
                        if (e.avancerDirectionChoisie(e.changeDirection())); {
                            ret = true;
                        }
                    }
                } else if (!horizontal) {
                    if (Next.peutPermettreDeMonterDescendre()) {
                        if (e.avancerDirectionChoisie(d)); {
                            ret = true;
                        }
                    }
                    else {
                        if (e.avancerDirectionChoisie(d)) {
                            e.changeDirection(e.getDirection(false));
                            ret = true;
                        }
                    }
                }
            }
            else {
                if (d == Direction.haut) e.changeDirection(e.getDirection(false));
                if (e.avancerDirectionChoisie(d))
                    ret = true;
            }

        }

        return ret;
    }
}
