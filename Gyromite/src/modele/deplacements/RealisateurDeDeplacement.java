package modele.deplacements;

import modele.plateau.EntiteDynamique;

import java.util.ArrayList;

/**
Tous les déplacement sont déclenchés par cette classe (gravité, controle clavier, IA, etc.)
 */
public abstract class RealisateurDeDeplacement {
    protected ArrayList<EntiteDynamique> lstEntitesDynamiques = new ArrayList<EntiteDynamique>();
    protected abstract boolean realiserDeplacement();

    public void addEntiteDynamique(EntiteDynamique ed) {lstEntitesDynamiques.add(ed);};
}

/*
@startuml
+abstract class RealisateurDeDeplacement {
# lstEntitesDynamiques : ArrayList<EntiteDynamique>
--
# realiserDeplacement() : abstract boolean
--
+ addEntiteDynamique(EntiteDynamique ed) : void
}
@enduml
 */