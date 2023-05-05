import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Visualization of the head-to-head tournaments for the Final Projects
 */


public class GraderGUI extends JFrame implements ActionListener{
    
    // Fields
    GraderH2H grader;
	private DSArrayList<FinalProject> projects;
	private DSArrayList<String> names;
    private int numPlayers;
    JLabel[][] resultLabels;
	private JButton oneGameButton;
	private JButton manyGamesButton;
    private JRadioButton bShort;
    private JRadioButton bLong;
    private String longOrShort; // Which game to play
    
    public static void main(String[] args){
        new GraderGUI();
    }

	public GraderGUI() {
		this.grader = new GraderH2H();
        this.grader.init();
        this.projects = this.grader.getAllFinalProjects();
        this.names = this.grader.getAllFinalProjectNames();
        this.numPlayers = this.names.length();

        this.setLayout(new BorderLayout());

        // Create a panel to hold the H2H results
        JPanel h2hpanel = new JPanel();
        h2hpanel.setBackground( Color.BLACK );
        h2hpanel.setBorder( new MatteBorder(2, 2, 2, 2, Color.BLACK) );
        h2hpanel.setLayout(new GridLayout(1 + this.numPlayers, 1 + this.numPlayers, 2, 2));
        this.resultLabels = new JLabel[1 + this.numPlayers][1 + this.numPlayers];
        for(int i = 0; i <= numPlayers; i++){
            for(int j = 0; j <= numPlayers; j++){
                this.resultLabels[i][j] = new JLabel("---");
                this.resultLabels[i][j].setHorizontalAlignment(JLabel.CENTER);
                this.resultLabels[i][j].setVerticalAlignment(JLabel.CENTER);
                this.resultLabels[i][j].setOpaque(true);
                if(i == 0 && j > 0){
                    this.resultLabels[i][j].setText(this.names.get(j-1));
                    this.resultLabels[i][j].setBackground(new Color(1.0f, 0.8f, 0.8f));
                }
                if(j == 0 && i > 0){
                    this.resultLabels[i][j].setText(this.names.get(i-1));
                    this.resultLabels[i][j].setBackground(new Color(0.8f, 0.8f, 1.0f));
                }
                h2hpanel.add(this.resultLabels[i][j]);
            }
        }
        this.add(h2hpanel, BorderLayout.CENTER);

        // Create the radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        bShort = new JRadioButton("Short Game");
        bShort.setActionCommand("short");
        bShort.addActionListener(this);
        bShort.setSelected(true);
        this.longOrShort = "short";
        this.grader.setLongOrShort("short");
        buttonGroup.add(bShort);
        bLong = new JRadioButton("Long Game");
        bLong.setActionCommand("long");
        bLong.addActionListener(this);
        buttonGroup.add(bLong);

        // Add control buttons to the top of the grader
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(bShort);
        buttonPanel.add(bLong);
        oneGameButton = new JButton("One Game");
        oneGameButton.addActionListener(this);
        buttonPanel.add(oneGameButton);
        manyGamesButton = new JButton("Many Games");
        manyGamesButton.addActionListener(this);
        buttonPanel.add(manyGamesButton);

        this.add(buttonPanel, BorderLayout.NORTH);


        this.setPreferredSize(new Dimension(600, 600));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

        // Play many games head-to-head
        if(e.getSource() == this.manyGamesButton){
            this.grader.playManyH2H(longOrShort);
            int[][][] results = this.grader.getManyResults();
            for(int i = 0; i < numPlayers; i++){
                for(int j = 0; j < numPlayers; j++){
                    int[] record = results[i][j];
                    String t = "<html><table><tr align=\"center\"><td style=\"color:white; background-color:black\">"
                        + record[3] + "</td><td>" 
                        + record[2] + "</td></tr><tr align=\"center\"><td>"
                        + record[1] + "</td><td>" + record[0] + "</td></tr></table></html>"; 
                    this.resultLabels[i+1][j+1].setText(t);
                    this.resultLabels[i+1][j+1].setFont(new Font("Serif", Font.PLAIN, 15));
                    Color c = Color.white;
                    if(record[2] > record[1])  c = new Color(1.0f, 0.8f, 0.8f);
                    if(record[1] > record[2])  c = new Color(0.8f, 0.8f, 1.0f);
                    if(record[2] == record[1]) c = new Color(0.8f, 1.0f, 0.8f);
                    this.resultLabels[i+1][j+1].setBackground(c);
                }
            }            
        }

        // Play one game per pair
        if(e.getSource() == this.oneGameButton){
            this.grader.playOneGameH2H(longOrShort);
            String[][] results = this.grader.getOneResults();
            for(int i = 0; i < numPlayers; i++){
                for(int j = 0; j < numPlayers; j++){
                    String board = results[i][j];
                    String t = "<html>" + board.replace("\n", "<br>") + "</html>"; 
                    this.resultLabels[i+1][j+1].setText(t);
                    this.resultLabels[i+1][j+1].setFont(new Font("Monospaced", Font.PLAIN, 5));
                    Color c = Color.white;
                    if(board.contains("Player 2")) c = new Color(1.0f, 0.8f, 0.8f);
                    if(board.contains("Player 1")) c = new Color(0.8f, 0.8f, 1.0f);
                    if(board.contains("Tie"))      c = new Color(0.8f, 1.0f, 0.8f);
                    this.resultLabels[i+1][j+1].setBackground(c);
                }
            }            
        }

        // If the long or short radio button was selected
        if(e.getSource() == this.bShort || e.getSource() == this.bLong){
            this.longOrShort = e.getActionCommand();
            this.grader.setLongOrShort(this.longOrShort);
        }
	}

    // Constructor

}
