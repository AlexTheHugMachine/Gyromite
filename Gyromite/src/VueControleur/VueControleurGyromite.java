package VueControleur;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import modele.deplacements.Colonne;
import modele.deplacements.Controle4Directions;
import modele.deplacements.Direction;
import modele.plateau.*;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction Pacman, etc.))
 *
 */
public class VueControleurGyromite extends JFrame implements Observer {
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int bestScore = 0;
    private int sizeX; // taille de la grille affichée
    private int sizeY;

    private int resY;
    private int resX;

    // icones affichées dans la grille
    private ImageIcon icoHero;
    private ImageIcon icoVide;
    private ImageIcon icoMurHorizontal;
    private ImageIcon icoMurVertical;
    private ImageIcon icoBrick;
    private ImageIcon icoColonne;
    private ImageIcon icoBombe;
    private ImageIcon icoSmick;
    private ImageIcon icoCorde;


    private  JTable score;
    private JComponent grilleJLabels;
    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)
    private JLabel Status;


    public VueControleurGyromite(Jeu _jeu) {
        sizeX = jeu.SIZE_X;
        sizeY = _jeu.SIZE_Y;
        jeu = _jeu;

        resX = 915;
        resY =375;
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                resX = getWidth();
                resY = getHeight();
                chargerLesIcones();
            }
        });

        setContentPane(placerLesComposantsGraphiques());
        ajouterEcouteurClavier();
    }

    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : Controle4Directions.getInstance().setDirectionCourante(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : Controle4Directions.getInstance().setDirectionCourante(Direction.droite); break;
                    case KeyEvent.VK_DOWN : Controle4Directions.getInstance().setDirectionCourante(Direction.bas); break;
                    case KeyEvent.VK_UP : Controle4Directions.getInstance().setDirectionCourante(Direction.haut); break;
                    case KeyEvent.VK_R:  Colonne.getInstance().setDirectionCouranteR();break;
                }
            }
        });
    }


    private void chargerLesIcones() {
        icoHero = chargerIcone("Images/sprites_prof/prof_look_left.png");
        icoVide = chargerIcone("Images/sprites_tiles/vide.png");
        icoColonne = chargerIcone("Images/sprites_tiles/red_tube_1.png");
        icoMurHorizontal = chargerIcone("Images/sprites_tiles/platform1.png");
        icoMurVertical = chargerIcone("Images/sprites_tiles/platform2.png");
        icoBrick = chargerIcone("Images/sprites_tiles/brick1.png");
        icoBombe = chargerIcone("Images/sprites_dynamite/dyanmite_eteinte.png");
        icoSmick = chargerIcone("Images/sprites_ennemis/ennemis_run_1_left.png");
        icoCorde = chargerIcone("Images/sprites_tiles/rope.png");


    }

    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleurGyromite.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        Image stretched = image.getScaledInstance(resX/sizeX,(resY-resY/10)/sizeY,Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }

    private JPanel placerLesComposantsGraphiques() {
        JPanel game = new JPanel(new BorderLayout());
        setTitle("Gyromite");
        setSize(resX,resY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille
        grilleJLabels.setBackground(Color.black);

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        String data[][] = this.jeu.getScore();
        String columns[] = {"","","","","","",}; // Les 4 meilleures scores
        score = new JTable(data,columns);
        score.setFocusable(false);
        score.setRowHeight(resY/10);
        grilleJLabels.setBackground(Color.black);
        score.setBackground(Color.red);
        game.add(grilleJLabels,BorderLayout.CENTER);
        game.add(score,BorderLayout.NORTH);
        return game;
    }

    private JPanel endGame() {
        JPanel nav = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,10);
        nav.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Score" +jeu.getScore()[0][1]));
        Status = new JLabel(jeu.Status, JLabel.CENTER);
        JButton restart = new JButton("Recommencer");
        JButton quit = new JButton("Quitter");

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jeu.reset();
                setContentPane(placerLesComposantsGraphiques());
                revalidate();
                requestFocus();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        constraints.gridy = 0;
        nav.add(Status, constraints);
        constraints.gridy = 1;
        nav.add(restart, constraints);
        constraints.gridy = 2;
        nav.add(quit,constraints);
        return nav;

    }


    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {
        if(jeu.gameOver()){
            setContentPane(endGame());
            if (jeu.Status=="YOU WIN" && jeu.getNbscore()> bestScore){
                bestScore = jeu.getNbscore();
            }
            revalidate();
        }
        else {
            for (int i = 1; i < 6; i += 2) {
                score.getModel().setValueAt(jeu.getScore()[0][i], 0, i);
            }

            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    if (jeu.getGrille()[x][y][1] instanceof Heros) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue
                        // System.out.println("Héros !");
                        tabJLabel[x][y].setIcon(icoHero);
                    } else if (jeu.getGrille()[x][y][0] instanceof Mur_Horizontal) {
                        tabJLabel[x][y].setIcon(icoMurHorizontal);
                    } else if (jeu.getGrille()[x][y][0] instanceof Mur_Vertical) {
                        tabJLabel[x][y].setIcon(icoMurVertical);
                    } else if (jeu.getGrille()[x][y][0] instanceof Brick) {
                        tabJLabel[x][y].setIcon(icoBrick);
                    } else if (jeu.getGrille()[x][y][1] instanceof PillarRed) {
                        tabJLabel[x][y].setIcon(icoColonne);
                    } else if (jeu.getGrille()[x][y][1] instanceof Bombe) {
                        tabJLabel[x][y].setIcon(icoBombe);
                    } else if (jeu.getGrille()[x][y][1] instanceof Bot) {
                        tabJLabel[x][y].setIcon(icoSmick);
                    } else if (jeu.getGrille()[x][y][0] instanceof Corde) {
                        tabJLabel[x][y].setIcon(icoCorde);
                    } else {
                        tabJLabel[x][y].setIcon(icoVide);
                    }
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }
}

/*
@startuml
+class VueControleurGyromite extends JFrame implements Observer {
- jeu : Jeu
- sizeX : int
- sizeY : int
- icoHero : ImageIcon
- icoVide : ImageIcon
- icoMurHorizontal : ImageIcon
- icoMurVertical : ImageIcon
- icoBrick : ImageIcon
- icoColonne : ImageIcon
- icoBombe : ImageIcon
- icoSmick : ImageIcon
- icoCorde : ImageIcon
- score : JTable
- tabJLabel : JLabel[][]
--
+ VueControleurGyromite(Jeu _jeu)
+ update(Observable o, Object arg) : void
--
- ajouterEcouteurClavier() : void
- chargerLesIcones() : void
- chargerIcone(String urlIcone) : ImageIcon
- placerLesComposantsGraphiques() : void
- mettreAJourAffichage() : void
}
@enduml
 */