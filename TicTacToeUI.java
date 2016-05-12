import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/*
*	Class for the Tic-Tac-Toe User Interface
*/
public class TicTacToeUI extends JFrame {
	JPanel myPanel = new JPanel();
	JPanel optionPanel = new JPanel();
	JButton button[][] = new JButton[3][3];
	TicTacToe game;
	BufferedImage dog = null;
	BufferedImage cat = null;

	public void JavaImageIOTest(){
		try{
			dog = ImageIO.read(getClass().getResource("dog.png"));
			cat = ImageIO.read(getClass().getResource("cat.png"));
		}
		catch(IOException e){}
	}

	public TicTacToeUI(TicTacToe game){
		//initializing panel (frame)
		super("TIC TAC TOE");
		setLayout(new BorderLayout());
		myPanel.setLayout(new GridLayout(3,3));
		myPanel.setSize(350, 350);
		myPanel.setBackground(Color.PINK);
		optionPanel.setLayout(new GridLayout(1,2));
		add(myPanel, BorderLayout.CENTER);
		add(optionPanel, BorderLayout.PAGE_END);

		this.game = game;

		JavaImageIOTest();
		// adding button
		for(int i=0; i<3; i+=1){
			for(int j=0; j<3; j+=1){
				button[i][j] = new JButton();
				button[i][j].setFont(new Font("Arial", Font.BOLD, 100));
				button[i][j].setText("");
				button[i][j].setBackground(new Color(0, 99,128));
				button[i][j].setForeground(Color.WHITE);
				button[i][j].addActionListener(addAction());
				myPanel.add(button[i][j]);
			}
		}
	}

	public void initialize(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public ActionListener addAction(){
		ActionListener click = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				for(int i = 0 ; i < 3; i++){
					for(int j = 0 ; j < 3; j++){
						if(e.getSource() == button[i][j]){
							if (button[i][j].getText() != "X" && button[i][j].getText() != "O") {
								makeHumanMove(new Point(i, j));
								informAI(i, j);
							}
						}
					}
				}
			}
		};
		return click;
	}

	public void informAI(int x, int y){
		this.game.computer.PrintMatrix(this.game.matrix);
		
		// check if winner
		char result = TicTacToe.StillPlaying(this.game.matrix);
		if (result != '-') {
			switch (result) {
				case 'X': System.out.println("You win!"); 
							JOptionPane.showMessageDialog(myPanel, "You win!");
							break;
				case 'O': System.out.println("You lost, ew noob."); 
							JOptionPane.showMessageDialog(myPanel, "You lost, ew noob.");
							break;
				case 'D': System.out.println("Game ends with a draw!"); 
							JOptionPane.showMessageDialog(myPanel, "game ends with a draw!");
							break;
			}
		}
			
		Point move = this.game.computer.AnalyzeMatrix(this.game.matrix);
		if(move!=null) makeComputerMove(move);

		// check if winner		
		result = TicTacToe.StillPlaying(this.game.matrix);
		if (result != '-') {	
			switch (result) {
				case 'X': System.out.println("You win!"); 
							JOptionPane.showMessageDialog(myPanel, "You win!");
							break;
				case 'O': System.out.println("You lost, ew noob."); 
							JOptionPane.showMessageDialog(myPanel, "You lost, ew noob.");
							break;
				case 'D': System.out.println("Game ends with a draw!"); 
							JOptionPane.showMessageDialog(myPanel, "Game ends with a draw!");
							break;
			}	
		}
	}

	public void makeComputerMove(Point p){
		this.button[(int)p.getX()][(int)p.getY()].setIcon(new ImageIcon(cat));
		this.game.matrix[(int)p.getX()][(int)p.getY()] = 'O';
	}
	public void makeHumanMove(Point p){
		this.button[(int)p.getX()][(int)p.getY()].setIcon(new ImageIcon(dog));
		this.game.matrix[(int)p.getX()][(int)p.getY()] = 'X';
	}

	public int getPlayerOption(){
		Object[] options = {"Me, ofcourse!", "Computer (ew, coward!)"};
		int result = JOptionPane.showOptionDialog(myPanel,
										"Who should make the first move? #NoHugot",
										"Things to ponder on",
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										null,
										options,
										options[1]
									);
		return result;
	}

}