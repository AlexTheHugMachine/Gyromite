package package01;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import package01.Game.ChoiceHandler;

public class UI {
	
		JFrame window;
		JPanel titleNamePanel, startButtonPanel,rulesButtonPanel,creditsButtonPanel, mainTextPanel,
			   backButtonPanel,creditTextPanel;
		JLabel titleNameLabel;
		JButton startButton,rulesButton,creditsButton,backButton;
		JTextArea mainTextArea,creditTextArea;
		Font titleFont = new Font("Times New Roman", Font.PLAIN,90);
		Font normalFont = new Font("Times New Roman", Font.PLAIN,26);
		
		public void createUI(ChoiceHandler cHandler) {
			
			//Window
			window = new JFrame("Cyromite");
			window.setSize(800,600);
			window.setLocationRelativeTo(null);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.getContentPane().setBackground(Color.black);
			window.setLayout(null);
			window.setVisible(true);
			
			//TitleScreen
			titleNamePanel = new JPanel();
			titleNamePanel.setBounds(100,0,650,500);
			titleNamePanel.setBackground(Color.black);
			titleNameLabel = new JLabel("SELECT MODE");
			titleNameLabel.setForeground(Color.white);
			titleNameLabel.setFont(titleFont);
			titleNamePanel.add(titleNameLabel);
			
			startButtonPanel = new JPanel();
			startButtonPanel.setBounds(300,200,200,100);
			startButtonPanel.setBackground(Color.black);
			startButton = new JButton("Start");
			startButton.setBackground(Color.black);
			startButton.setForeground(Color.white);
			startButton.setFont(normalFont);
			startButton.setFocusPainted(false);
			startButton.addActionListener(cHandler);
			startButton.setActionCommand("start");
			startButtonPanel.add(startButton);
			
			rulesButtonPanel = new JPanel();
			rulesButtonPanel.setBounds(300,300,200,100);
			rulesButtonPanel.setBackground(Color.black);
			rulesButton = new JButton("Rules");
			rulesButton.setBackground(Color.black);
			rulesButton.setForeground(Color.white);
			rulesButton.setFont(normalFont);
			rulesButton.setFocusPainted(false);
			rulesButton.addActionListener(cHandler);
			rulesButton.setActionCommand("rules");
			rulesButtonPanel.add(rulesButton);
			
			creditsButtonPanel = new JPanel();
			creditsButtonPanel.setBounds(300,400,200,100);
			creditsButtonPanel.setBackground(Color.black);
			creditsButton = new JButton("Credits");
			creditsButton.setBackground(Color.black);
			creditsButton.setForeground(Color.white);
			creditsButton.setFont(normalFont);
			creditsButton.setFocusPainted(false);
			creditsButton.addActionListener(cHandler);
			creditsButton.setActionCommand("credits");
			creditsButtonPanel.add(creditsButton);
			
			backButtonPanel = new JPanel();
			backButtonPanel.setBounds(0,5,70,60);
			backButtonPanel.setBackground(Color.black);
			backButton = new JButton("◄");
			backButton.setBackground(Color.black);
			backButton.setForeground(Color.white);
			backButton.setFont(normalFont);
			backButton.setFocusPainted(false);
			backButton.addActionListener(cHandler);
			backButton.setActionCommand("back");
			backButtonPanel.add(backButton);
			
			window.add(titleNamePanel);
			window.add(startButtonPanel);
			window.add(rulesButtonPanel);
			window.add(creditsButtonPanel);
			window.add(backButtonPanel);
			
			//RulesScreen
			mainTextPanel = new JPanel();
			mainTextPanel.setBounds(75, 75, 650, 750);
			mainTextPanel.setBackground(Color.black);
			window.add(mainTextPanel);
			
			mainTextArea = new JTextArea("Vous contrôlez le Prof. Hector qui se déplace pour ramasser les bombes "
					+ "dans son laboratoire, vu en 2D de côté. Le laboratoire est composé d’un"
					+ "ensemble de niveaux. Le scientifique peut se déplacer à gauche, à droite,"
					+ "et grimper ou descendre à l’aide de cordes. Un niveau peut contenir des"
					+ "piliers (bleus et rouges) qui peuvent monter ou descendre lorsque le"
					+ "joueur appuie sur une touche spécifique. Enfin, le professeur doit éviter"
					+ "les ennemis, les Smicks. L’objectif est donc de récupérer toutes les"
					+ "bombes, en évitant les ennemis, tout en manipulant les piliers pour créer"
					+ "des chemins si nécessaire.");
			mainTextArea.setBounds(100, 100, 650, 750);
			mainTextArea.setBackground(Color.black);
			mainTextArea.setForeground(Color.white);
			mainTextArea.setFont(normalFont);
			mainTextArea.setLineWrap(true);
			mainTextArea.setWrapStyleWord(true);
			mainTextArea.setEditable(false);
			mainTextPanel.add(mainTextArea);
			
			//CreditsScreen
			creditTextPanel = new JPanel();
			creditTextPanel.setBounds(75, 75, 650, 750);
			creditTextPanel.setBackground(Color.black);
			window.add(creditTextPanel);
			
			creditTextArea = new JTextArea("Vous contrôlez le Prof. Hector qui se déplace pour ramasser les bombes "
					+ "dans son laboratoire, vu en 2D de côté. Le laboratoire est composé d’un"
					+ "ensemble de niveaux. Le scientifique peut se déplacer à gauche, à droite,"
					+ "et grimper ou descendre à l’aide de cordes. Un niveau peut contenir des"
					+ "piliers (bleus et rouges) qui peuvent monter ou descendre lorsque le"
					+ "joueur appuie sur une touche spécifique. Enfin, le professeur doit éviter"
					+ "les ennemis, les Smicks. L’objectif est donc de récupérer toutes les"
					+ "bombes, en évitant les ennemis, tout en manipulant les piliers pour créer"
					+ "des chemins si nécessaire.");
			creditTextArea.setBounds(100, 100, 650, 750);
			creditTextArea.setBackground(Color.black);
			creditTextArea.setForeground(Color.white);
			creditTextArea.setFont(normalFont);
			creditTextArea.setLineWrap(true);
			creditTextArea.setWrapStyleWord(true);
			creditTextArea.setEditable(false);
			creditTextPanel.add(creditTextArea);
			
			window.setVisible(true);
			
			
		}
		
}
