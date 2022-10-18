package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.Shape.Tetrominoes;


public class Board extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final int BoardWidth = 10;
    private final int BoardHeight = 25;

    private Timer fallTimer;
    private Shape currPiece;
    private JLabel statusbar;
    private Tetrominoes[] board;
    private int curX = 0, curY = 0;
    private int numLinesRemoved = 0;
    private boolean isPaused = false;
    private boolean isStarted = false;
    private boolean isFallingFinished = false;
    
    public Board(Tetris parent) {
       setFocusable(true);
       currPiece = new Shape();
       fallTimer = new Timer(500, this);
       fallTimer.start(); 

       statusbar =  parent.getStatusBar();
       board = new Tetrominoes[BoardWidth * BoardHeight];
       addKeyListener(new TetrisKeyAdapter());
       clearBoard();  
    }
    
    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            if (!isStarted || currPiece.getShape() == Tetrominoes.NoShape) return;

            int keycode = e.getKeyCode();
            if (keycode == 'p' || keycode == 'P') {
                pause();
                return;
            }

            if (isPaused) return;

            switch (keycode) {
            case KeyEvent.VK_LEFT:
                tryMove(currPiece, curX - 1, curY);
                break;
            case KeyEvent.VK_RIGHT:
                tryMove(currPiece, curX + 1, curY);
                break;
            case KeyEvent.VK_UP:
           	 tryMove(currPiece.rotateRight(), curX, curY);
                break;
            case KeyEvent.VK_SPACE:
                dropDown();
                break;
            case KeyEvent.VK_DOWN:
                oneLineDown();
                break;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else oneLineDown();
    }

    int squareWidth() { return (int) getSize().getWidth() / BoardWidth; }
    int squareHeight() { return (int) getSize().getHeight() / BoardHeight; }
    Tetrominoes shapeAt(int x, int y) { return board[(y * BoardWidth) + x]; }

    public void start()
    {
        if (isPaused) return;

        isStarted = true;
        numLinesRemoved = 0;
        isFallingFinished = false;
        clearBoard();

        newPiece();
        fallTimer.start();
    }

    private void pause()
    {
        if (!isStarted) return;

        isPaused = !isPaused;
        if (isPaused) {
            fallTimer.stop();
            statusbar.setText("Paused");
        } else {
            fallTimer.start();
            statusbar.setText("Score"+String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    public void paint(Graphics g)
    { 
        super.paint(g);

        int i, j;
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();
        
        setBackground(Color.BLACK);

        // Draw grid
        g.setColor(Color.DARK_GRAY);
        final int gridWidth = squareWidth();
        final int gridHeight = squareHeight();
        final int windowWidth = (int)getSize().getWidth();
        final int windowHeight = (int)getSize().getHeight();
        for (i=0; i<=BoardWidth; i++) g.drawLine(gridWidth*i, 0, gridWidth*i, windowHeight);
        for (i=0; i<=BoardHeight; i++) g.drawLine(0, gridHeight*i+7, windowWidth, gridHeight*i+7);
        
        // Draw blocks
        g.setColor(Color.BLACK);
        for (i = 0; i < BoardHeight; ++i) {
            for (j = 0; j < BoardWidth; ++j) {
                Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != Tetrominoes.NoShape) drawSquare(g, 0 + j * squareWidth(), boardTop + i * squareHeight(), shape);
            }
        }

        if (currPiece.getShape() != Tetrominoes.NoShape) {
            for (i = 0; i < 4; ++i) {
                int x = curX + currPiece.x(i);
                int y = curY - currPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(), currPiece.getShape());
            }
        }
    }

    private void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(currPiece, curX, newY - 1)) break;
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown() { if (!tryMove(currPiece, curX, curY - 1)) pieceDropped(); }


    private void clearBoard()
    {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i) board[i] = Tetrominoes.NoShape;
    }

    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curX + currPiece.x(i);
            int y = curY - currPiece.y(i);
            board[(y * BoardWidth) + x] = currPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) newPiece();
    }

    private void newPiece()
    {
        currPiece.setRandomShape();
        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + currPiece.minY();

        if (!tryMove(currPiece, curX, curY)) {
            currPiece.setShape(Tetrominoes.NoShape);
            fallTimer.stop();
            isStarted = false;
            statusbar.setText("Game Over");
        }
    }

    private boolean tryMove(Shape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight) return false;
            if (shapeAt(x, y) != Tetrominoes.NoShape) return false;
        }

        currPiece = newPiece;
        curX = newX; curY = newY;
        repaint();
        return true;
    }

    private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k)
                    for (int j = 0; j < BoardWidth; ++j)
                    	board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            currPiece.setShape(Tetrominoes.NoShape);
            repaint();
        }
     }

    private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)
    {
        Color colors[] = { 
        	new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
    }
}