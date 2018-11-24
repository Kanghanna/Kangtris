import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	final int BoardWidth = 10;
	final int BoardHeight = 22;

	Timer timer;
	boolean isFallingFinished = false;// generate new piece if true
	boolean isStarted = false;
	boolean isPaused = false;
	boolean gameover = false;
	int numLinesRemoved = 0;
	int curX = 0;// actual position of falling tetris
	int curY = 0;
	int score_total = 0;
	int level = 1;
	JLabel statusbar;
	JPanel jp;
	Shape curPiece;
	Shape.Tetrominoes[] board;

	public Board(Tetris parent) {
		// jp.add(new game());

		setFocusable(true);
		curPiece = new Shape();
		timer = new Timer(400, this);
		timer.start(); // calls action performed method every 400ms

		addKeyListener(new TAdapter());
		requestFocus();
		setFocusable(true);

		statusbar = parent.getStatusBar();
		board = new Shape.Tetrominoes[BoardWidth * BoardHeight];

		clearBoard();
	}

	public void actionPerformed(ActionEvent e) {
		if (isFallingFinished) {
			isFallingFinished = false;
			newPiece();// if falling finished generate new piece
		} else {
			oneLineDown();// if falling not finished move the shape 1 line down
		}
	}

	int squareWidth() {
		return (int) getSize().getWidth() / BoardWidth;
	}

	int squareHeight() {
		return (int) getSize().getHeight() / BoardHeight;
	}

	Shape.Tetrominoes shapeAt(int x, int y) {
		return board[(y * BoardWidth) + x];
	}

	public void start() {
		if (isPaused)
			return;

		isStarted = true;
		isFallingFinished = false;
		numLinesRemoved = 0;
		score_total = 0;
		clearBoard();

		newPiece();
		timer.start();
	}

	private void pause() {
		if (!isStarted)
			return;

		isPaused = !isPaused;
		if (isPaused) {
			timer.stop();
			statusbar.setText("paused");
		} else {
			timer.start();
			statusbar.setText(String.valueOf(score_total));
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);

		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();

		for (int i = 0; i < BoardHeight; ++i) {
			for (int j = 0; j < BoardWidth; ++j) {
				Shape.Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);// shape at to remember position of fallen
																			// shape
				if (shape != Shape.Tetrominoes.NoShape)
					drawSquare(g, 0 + j * squareWidth(), boardTop + i * squareHeight(), shape);
			}
		}

		if (curPiece.getShape() != Shape.Tetrominoes.NoShape) {
			for (int i = 0; i < 4; ++i) {
				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);
				drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(),
						curPiece.getShape());
			}
		}
	}

	private void dropDown() {
		int newY = curY;// cur X cur Y hold initial coords of piece.....
		while (newY > 0) {
			if (!tryMove(curPiece, curX, newY - 1))
				break;
			--newY;
		}
		pieceDropped();
	}

	private void oneLineDown() {
		if (!tryMove(curPiece, curX, curY - 1))
			pieceDropped();
	}

	private void clearBoard() {
		for (int i = 0; i < BoardHeight * BoardWidth; ++i)
			board[i] = Shape.Tetrominoes.NoShape;
	}

	private void pieceDropped() {
		for (int i = 0; i < 4; ++i) {
			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);
			board[(y * BoardWidth) + x] = curPiece.getShape();
		}

		removeFullLines();

		if (!isFallingFinished)
			newPiece();
	}

	private void newPiece() {
		////////////////////// IF THEY CANT BE MOVED MEANS GAME OVER///////////////
		curPiece.setRandomShape();
		curX = BoardWidth / 2 + 1;// INITIAL COORDS
		curY = BoardHeight - 1 + curPiece.minY();
		score_total += 10;
		statusbar.setText("SCORE : " + String.valueOf(score_total) + "                " + "LEVEL " + level);
		
		switch (level) {
		case 1:
			if (score_total >= 500)
				level++; break;
		case 2:
			if (score_total >= 1000)
				level++; break;
		default :
			
		}
		
		if (!tryMove(curPiece, curX, curY)) {
			curPiece.setShape(Shape.Tetrominoes.NoShape);
			timer.stop();
			isStarted = false;
			statusbar.setText("game over");
			new result();
		}
	}

	private boolean tryMove(Shape newPiece, int newX, int newY) {
		for (int i = 0; i < 4; ++i) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
				return false;
			if (shapeAt(x, y) != Shape.Tetrominoes.NoShape)
				return false;
		}

		curPiece = newPiece;
		curX = newX;
		curY = newY;
		repaint();
		return true;
	}

	private void removeFullLines() { // 한줄이 채워지면 지우는 메소드
		int numFullLines = 0;
		int score = 0;
		for (int i = BoardHeight - 1; i >= 0; --i) {
			// score += 50;
			boolean lineIsFull = true;

			for (int j = 0; j < BoardWidth; ++j) {
				if (shapeAt(j, i) == Shape.Tetrominoes.NoShape) {
					lineIsFull = false;
					break;
				}
			}

			if (lineIsFull) { // 한줄이 꽉 채워졌을 때
				++numFullLines;
				score += 50;
				for (int k = i; k < BoardHeight - 1; ++k) {
					for (int j = 0; j < BoardWidth; ++j)
						board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
				}
			}
		}

		if (numFullLines > 0) {
			numLinesRemoved += numFullLines;
			score_total += score;
			statusbar.setText(String.valueOf(score_total));
			isFallingFinished = true;
			curPiece.setShape(Shape.Tetrominoes.NoShape);
			repaint();
		}
	}

	private void drawSquare(Graphics g, int x, int y, Shape.Tetrominoes shape) {
		Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102),
				new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
				new Color(218, 170, 0) };

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

	class levelup {
		levelup() {
			if (score_total >= 500)
				level++;
		}
	}

	class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {

			if (!isStarted || curPiece.getShape() == Shape.Tetrominoes.NoShape) {
				return;
			}

			int keycode = e.getKeyCode();

			if (keycode == 'p' || keycode == 'P') {
				pause();
				return;
			}

			if (isPaused)
				return;

			switch (keycode) {
			case KeyEvent.VK_LEFT:
				tryMove(curPiece, curX - 1, curY);
				break;
			case KeyEvent.VK_RIGHT:
				tryMove(curPiece, curX + 1, curY);
				break;
			case KeyEvent.VK_DOWN:
				tryMove(curPiece.rotateRight(), curX, curY);
				break;
			case KeyEvent.VK_UP:
				tryMove(curPiece.rotateLeft(), curX, curY);
				break;
			case KeyEvent.VK_SPACE:
				dropDown();
				break;
			case 'd':
				oneLineDown();
				break;
			case 'D':
				oneLineDown();
				break;
			}

		}
	}

	class result extends JFrame {
		result() {
			JPanel result = new JPanel();
			setContentPane(result);
			setSize(300, 200);
			setResizable(false);
			setVisible(true);
			JLabel label = new JLabel("Total Score : " + String.valueOf(score_total));
			add(label);
			pack();
			setVisible(true);
		}
	}
}