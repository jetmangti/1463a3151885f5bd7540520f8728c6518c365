import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;

import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imports.Enum.Calc;
import imports.Enum.Team;

public class Game extends JFrame implements Runnable {

	private JLayeredPane contentPane;
	private static int counter = 0;
	private JFrame win;
	private Cell[][] board;
	private Cell[][] undoBoard;
	private int cellWidth = 80;
	private boolean widthIsLower = false;
	private gameSetting setting;
	private int offset;
	private int clickk;
	private int topOffset = 80;
	private WindowManager wm;
	private SpriteHolder sprite;
	private boolean undoEnabled = false;
	private Game currentGame;

	/**
	 * Launch the application.
	 */
	private int min(int a, int b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}

	private int computeCellSize(int margin) {
		int size = 0;
		size = this.min(win.getWidth(), win.getHeight() - 37 - this.topOffset) / this.setting.getSize();
		if (win.getWidth() < win.getHeight()) {
			this.widthIsLower = true;
		} else {
			this.widthIsLower = false;
		}
		return (size - margin);
	}

	public void recordAll() {
		for (int i = 0; i < setting.getSize(); i++) {
			for (int j = 0; j < setting.getSize(); j++) {
				// board[i][j].resetHistory();
				board[i][j].recordStatus();
			}
		}
	}

	public void saveGame() {

		System.out.println("Saving...");

		try {

			XStream xstream = new XStream();

			// Save settings

			File file = new File("../othello test1/savedgames/save_settings.xml");
			file.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(file);

			String xml = xstream.toXML(this.setting);

			fw.write(xml, 0, xml.length());
			fw.close();

			// Save cells

			file = new File("../othello test1/savedgames/save_board.xml");
			fw = new FileWriter(file);

			xml = xstream.toXML(this.board);

			fw.write(xml, 0, xml.length());
			fw.close();

			System.out.println("Game saved");
		} catch (Exception e) {
			System.err.println("Error in XML Write: " + e.getMessage());
		}

	}

	public Game(gameSetting s, WindowManager w, Cell[][] boardd) {
		
		
		this.setting = s;
		this.board = boardd;
		win = new JFrame();
		Stack<Runnable> undoStck = new Stack<Runnable>();
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try {
		 * win.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
		 * } });
		 */

		// win.setVisible(true);
		this.currentGame = this;
		this.clickk = 0;
		contentPane = new JLayeredPane();
		counter++;
		win.setContentPane(contentPane);
		win.setForeground(Color.WHITE);
		win.setBackground(Color.WHITE);
		win.setTitle("Othello");
		win.setResizable(true);
		win.setBounds(0, 0, 1024, 720);// 622 388
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		win.setContentPane(contentPane);
		this.cellWidth = this.computeCellSize(0);
		if (!this.widthIsLower) {
			this.offset = win.getWidth() - this.cellWidth * this.setting.getSize();
		} else {
			this.offset = win.getHeight() - this.cellWidth * this.setting.getSize();
		}
		this.offset = this.offset / 2;
		this.sprite = new SpriteHolder(cellWidth);
		Runnable rnbl = new Runnable() {
			GameController gc = new GameController(undoStck);
			CellFinder cf = new CellFinder(setting.getSize(), board);
			JLabel blackScore;
			JLabel whiteScore;
			// AIPlay1 ai2 = new AIPlay1(cf,gc, setting.getSize(), 1, board);
			AIInterface ai;
			int state = 1;
			JButton undo = new JButton("Undo");

			public void toDo(Cell temp, MouseEvent e) {
				recordAll();
				clickk++;
				if (state == 1) {
					// undoStck.push(this);
					cf.resetEmpty();
					gc.placeStone(temp);
					Cell placedStone = (Cell) e.getSource(); // gets ID of cell
																// where stone
																// was added
					cf.turnStones(placedStone.getXPos(), placedStone.getYPos(), gc.getActualPlayer()); // turns
																										// opponents
																										// stones
																										// and
																										// gc.getActualPlayer
					if (setting.getGameMode() == 0) {
						cf.setPadsVisibility(false);
					}
					cf.recalculateAndMark(gc.getTeamID());
					if (setting.getGameMode() == 0) {
						state = 2;
						cf.resetEmpty();
					} else {
						state = 1;
					}
				} else if (setting.getGameMode() != 1) {
					state = 2;
					cf.resetEmpty();
					Cell aiStone = ai.doJob();
					cf.turnStones(aiStone.getXPos(), aiStone.getYPos(), gc.getActualPlayer());
					cf.setPadsVisibility(true);
					cf.recalculateAndMark(gc.getTeamID());
					state = 1;
				}
				actualizeScore(whiteScore, blackScore);
			}

			public void actualizeScore(JLabel whiteLabel, JLabel blackLabel) {
				int black = 0;
				int white = 0;
				for (int i = 0; i < setting.getSize(); i++) {
					for (int j = 0; j < setting.getSize(); j++) {
						if (board[i][j].getTeam() == Team.BLACK) {
							black++;
						} else if (board[i][j].getTeam() == Team.WHITE) {
							white++;
						}
						// board[i][j].recordStatus();
					}
				}
				whiteLabel.setText("White :" + white);
				blackLabel.setText("Black :" + black);

			}

			public void run() {
				blackScore = new JLabel("Black :2");
				blackScore.setBounds(100, 20, 100, 20);
				contentPane.add(blackScore, 1);

				whiteScore = new JLabel("White :2");
				whiteScore.setBounds(220, 20, 100, 20);
				contentPane.add(whiteScore, 1);

				if (setting.getGameMode() == 0) {
					if (setting.getAiMode() == 1) {
						ai = new AIPlay1(cf, gc, setting.getSize(), 1, board);
					} else {
						ai = new AIPlay2(cf, gc, setting.getSize(), 1, board);
					}
				}
				
				for (int i = 0; i < setting.getSize(); ++i) {
					for (int j = 0; j < setting.getSize(); ++j) {
						
						board[i][j].setHistory();
						Cell temp = board[i][j];

						board[i][j].addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								if (temp.isEnabled()) {
									toDo(temp, e);
									if (setting.getGameMode() == 0) {
										EventQueue.invokeLater(new Runnable() {
											public void run() {
												try {
													Thread.sleep(750);
													toDo(temp, e);
												} catch (InterruptedException e1) {
													// TODO Auto-generated catch
													// block
													e1.printStackTrace();
												}
											}
										});
									}
								}
							}
						});
						contentPane.add(board[i][j], 1);
					}
				}
				
				undo.setBounds(20, 20, 50, 20);
				undo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// if(!undoStck.isEmpty())
						// {
						if (clickk > 0) {
							clickk--;
							// undoStck.pop().run();
							for (int i = 0; i < setting.getSize(); ++i) {
								for (int j = 0; j < setting.getSize(); ++j) {
									board[i][j].undo();
									// board[i][j].undo();
								}
							}
							gc.changeTeam();
							if (setting.getGameMode() == 0) {
								System.out.println("GER");
								for (int i = 0; i < setting.getSize(); ++i) {
									for (int j = 0; j < setting.getSize(); ++j) {
										// board[i][j].undo();
										board[i][j].undo();
									}
								}
								gc.changeTeam();
								/*
								 * cf.recalculateAndMark(gc.getTeamID()); state
								 * = 2; cf.resetEmpty(); gc.changeTeam();
								 */
							}
							cf.setPadsVisibility(true);
							cf.hidePads();
							cf.recalculateAndMark(gc.getTeamID());
							// cf.resetEmptyAll();
							actualizeScore(whiteScore, blackScore);
							// }
						}
					}
				});
				contentPane.add(undo, 1);

				JButton saveGame = new JButton("Save Game");
				saveGame.setBounds(20, 50, 120, 90);
				saveGame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentGame.saveGame();
					}
				});

				contentPane.add(saveGame, 1);

				undoBoard = board;
				// recordAll();
				try {
					win.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				JButton tmp = new JButton();
				tmp.addActionListener(w);
				tmp.setActionCommand("HideMD");
				tmp.doClick();

				/////////////// ********GAME START*********///////////////

				// int state = 0;
				cf.recalculateAndMark(gc.getTeamID());

			}
		};
		new Thread(rnbl).start();
	}

	public Game(gameSetting s, WindowManager w) {
		this.setting = s;
		win = new JFrame();
		Stack<Runnable> undoStck = new Stack<Runnable>();
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try {
		 * win.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
		 * } });
		 */

		// win.setVisible(true);
		this.currentGame = this;
		this.clickk = 0;
		contentPane = new JLayeredPane();
		counter++;
		win.setContentPane(contentPane);
		win.setForeground(Color.WHITE);
		win.setBackground(Color.WHITE);
		win.setTitle("Othello");
		win.setResizable(true);
		win.setBounds(0, 0, 1024, 720);// 622 388
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		win.setContentPane(contentPane);
		board = new Cell[this.setting.getSize()][this.setting.getSize()];
		this.cellWidth = this.computeCellSize(0);
		if (!this.widthIsLower) {
			this.offset = win.getWidth() - this.cellWidth * this.setting.getSize();
		} else {
			this.offset = win.getHeight() - this.cellWidth * this.setting.getSize();
		}
		this.offset = this.offset / 2;
		this.sprite = new SpriteHolder(cellWidth);
		Runnable rnbl = new Runnable() {
			GameController gc = new GameController(undoStck);
			CellFinder cf = new CellFinder(setting.getSize(), board);
			JLabel blackScore;
			JLabel whiteScore;
			// AIPlay1 ai2 = new AIPlay1(cf,gc, setting.getSize(), 1, board);
			AIInterface ai;
			int state = 1;
			JButton undo = new JButton("Undo");

			public void toDo(Cell temp, MouseEvent e) {
				recordAll();
				clickk++;
				if (state == 1) {
					// undoStck.push(this);
					cf.resetEmpty();
					gc.placeStone(temp);
					Cell placedStone = (Cell) e.getSource(); // gets ID of cell
																// where stone
																// was added
					cf.turnStones(placedStone.getXPos(), placedStone.getYPos(), gc.getActualPlayer()); // turns
																										// opponents
																										// stones
																										// and
																										// gc.getActualPlayer
					if (setting.getGameMode() == 0) {
						cf.setPadsVisibility(false);
					}
					cf.recalculateAndMark(gc.getTeamID());
					if (setting.getGameMode() == 0) {
						state = 2;
						cf.resetEmpty();
					} else {
						state = 1;
					}
				} else if (setting.getGameMode() != 1) {
					state = 2;
					cf.resetEmpty();
					Cell aiStone = ai.doJob();
					cf.turnStones(aiStone.getXPos(), aiStone.getYPos(), gc.getActualPlayer());
					cf.setPadsVisibility(true);
					cf.recalculateAndMark(gc.getTeamID());
					state = 1;
				}
				actualizeScore(whiteScore, blackScore);
			}

			public void actualizeScore(JLabel whiteLabel, JLabel blackLabel) {
				int black = 0;
				int white = 0;
				for (int i = 0; i < setting.getSize(); i++) {
					for (int j = 0; j < setting.getSize(); j++) {
						if (board[i][j].getTeam() == Team.BLACK) {
							black++;
						} else if (board[i][j].getTeam() == Team.WHITE) {
							white++;
						}
						// board[i][j].recordStatus();
					}
				}
				whiteLabel.setText("White :" + white);
				blackLabel.setText("Black :" + black);

			}

			public void run() {
				blackScore = new JLabel("Black :2");
				blackScore.setBounds(100, 20, 100, 20);
				contentPane.add(blackScore, 1);

				whiteScore = new JLabel("White :2");
				whiteScore.setBounds(220, 20, 100, 20);
				contentPane.add(whiteScore, 1);

				if (setting.getGameMode() == 0) {
					if (setting.getAiMode() == 1) {
						ai = new AIPlay1(cf, gc, setting.getSize(), 1, board);
					} else {
						ai = new AIPlay2(cf, gc, setting.getSize(), 1, board);
					}
				}
				for (int i = 0; i < setting.getSize(); ++i) {
					for (int j = 0; j < setting.getSize(); ++j) {
						if (!widthIsLower) {
							board[i][j] = new Cell(cellWidth, i, j, (i * cellWidth) + offset - 10,
									j * cellWidth + topOffset, sprite);
						} else {
							board[i][j] = new Cell(cellWidth, i, j, i * cellWidth, (j * cellWidth) + offset + topOffset,
									sprite);
						}
						Cell temp = board[i][j];

						board[i][j].addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								if (temp.isEnabled()) {
									toDo(temp, e);
									if (setting.getGameMode() == 0) {
										EventQueue.invokeLater(new Runnable() {
											public void run() {
												try {
													Thread.sleep(750);
													toDo(temp, e);
												} catch (InterruptedException e1) {
													// TODO Auto-generated catch
													// block
													e1.printStackTrace();
												}
											}
										});
									}
								}
							}
						});
						contentPane.add(board[i][j], 1);
					}
				}
				undo.setBounds(20, 20, 50, 20);
				undo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// if(!undoStck.isEmpty())
						// {
						if (clickk > 0) {
							clickk--;
							// undoStck.pop().run();
							for (int i = 0; i < setting.getSize(); ++i) {
								for (int j = 0; j < setting.getSize(); ++j) {
									board[i][j].undo();
									// board[i][j].undo();
								}
							}
							gc.changeTeam();
							if (setting.getGameMode() == 0) {
								System.out.println("GER");
								for (int i = 0; i < setting.getSize(); ++i) {
									for (int j = 0; j < setting.getSize(); ++j) {
										// board[i][j].undo();
										board[i][j].undo();
									}
								}
								gc.changeTeam();
								/*
								 * cf.recalculateAndMark(gc.getTeamID()); state
								 * = 2; cf.resetEmpty(); gc.changeTeam();
								 */
							}
							cf.setPadsVisibility(true);
							cf.hidePads();
							cf.recalculateAndMark(gc.getTeamID());
							// cf.resetEmptyAll();
							actualizeScore(whiteScore, blackScore);
							// }
						}
					}
				});
				contentPane.add(undo, 1);

				JButton saveGame = new JButton("Save Game");
				saveGame.setBounds(20, 50, 120, 90);
				saveGame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentGame.saveGame();
					}
				});

				contentPane.add(saveGame, 1);

				board[setting.getSize() / 2][setting.getSize() / 2].setWhite();
				board[setting.getSize() / 2][setting.getSize() / 2 - 1].setBlack();
				board[setting.getSize() / 2 - 1][setting.getSize() / 2 - 1].setWhite();
				board[setting.getSize() / 2 - 1][setting.getSize() / 2].setBlack();
				undoBoard = board;
				// recordAll();
				try {
					win.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				JButton tmp = new JButton();
				tmp.addActionListener(w);
				tmp.setActionCommand("HideMD");
				tmp.doClick();

				/////////////// ********GAME START*********///////////////

				// int state = 0;
				cf.recalculateAndMark(gc.getTeamID());

			}
		};
		new Thread(rnbl).start();
	}

	@Override
	public void run() {
	}
}