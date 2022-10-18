package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tetris extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel statusbar, score;

	public static void main(String[] args) {
		Tetris game = new Tetris();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
	}
	
	public Tetris() {
		score = new JLabel("Score:");
		statusbar = new JLabel(" 0");

		add(score, BorderLayout.SOUTH);
		add(statusbar, BorderLayout.SOUTH);
		Board board = new Board(this);
		JOptionPane.showMessageDialog(board,
				"< Tetris > \n "
				+ "Move: LEFT(ก็) or RIGHT(กๆ)\n"
				+ "Rotation: UP(ก่)\n"
				+ "Increase fall speed: DOWN(ก้)\n"
				+ "Pause: Press [P]");
		add(board);
		board.start();

		setSize(200, 400);
		setTitle("Tetris");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit the program?",
						"Exit Program", JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) dispose();
			}
		});
	}

	public JLabel getStatusBar() { return statusbar; }
}