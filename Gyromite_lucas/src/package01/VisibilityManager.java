package package01;

public class VisibilityManager {
	
	UI ui;
	
	public VisibilityManager(UI userInterface) {
		
		ui = userInterface;		
	}
	
	public void showTitleScreen() {
		
		ui.titleNamePanel.setVisible(true);
		ui.startButtonPanel.setVisible(true);
		ui.creditsButtonPanel.setVisible(true);
		ui.rulesButtonPanel.setVisible(true);
		ui.backButtonPanel.setVisible(false);
		
		ui.mainTextPanel.setVisible(false);
		ui.creditTextPanel.setVisible(false);
	}
	
	public void selectRules() {
		
		ui.titleNamePanel.setVisible(false);
		ui.startButtonPanel.setVisible(false);
		ui.creditsButtonPanel.setVisible(false);
		ui.rulesButtonPanel.setVisible(false);
		ui.backButtonPanel.setVisible(true);
		
		ui.mainTextPanel.setVisible(true);
	}
	
	public void selectCredits() {
		
		ui.titleNamePanel.setVisible(false);
		ui.startButtonPanel.setVisible(false);
		ui.creditsButtonPanel.setVisible(false);
		ui.rulesButtonPanel.setVisible(false);
		ui.backButtonPanel.setVisible(true);
		
		ui.creditTextPanel.setVisible(true);
	}
}
