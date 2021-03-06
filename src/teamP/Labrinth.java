package teamP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Labrinth extends JFrame {
	private int width;
	private int height;
	private JLabel[][] map;

	private boolean isLeftMouseClicked = false;
	private boolean isRightMouseClicked = false;

	private boolean isChangeable = true;

	public Labrinth(int widthCount, int heightCount) {
		width = heightCount * 50;
		height = widthCount * 50;

		// get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int) ((screenSize.getWidth() - width) / 2);
		int yPos = (int) ((screenSize.getHeight() - height) / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(xPos, yPos, width, height);
		setBackground(Color.black);

		setLayout(new GridLayout(widthCount, heightCount, 0, 0));
		initComponenet(widthCount, heightCount);
		setVisible(true);

	}

	private void initComponenet(int widthCount, int heightCount) {
		map = new JLabel[widthCount][heightCount];
		for (int i = 0; i < widthCount; i++) {
			for (int j = 0; j < heightCount; j++) {
				map[i][j] = new JLabel();
				map[i][j].setBackground(Color.white);
				map[i][j].setOpaque(true);
				map[i][j].setBorder(new LineBorder(Color.BLACK, 1));
				map[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (isChangeable)
							changeClicked("none");
					}

					@Override
					public void mousePressed(MouseEvent e) {
						JLabel tmp = (JLabel) e.getSource();
						if (isChangeable) {
							if (!e.isMetaDown()) {
								changeClicked("left");
								tmp.setBackground(Color.black);
							} else {
								changeClicked("right");
								tmp.setBackground(Color.white);
							}
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						if (isChangeable) {
							if (isLeftMouseClicked) {
								JLabel tmp = (JLabel) e.getSource();
								tmp.setBackground(Color.black);
							} else if (isRightMouseClicked) {
								JLabel tmp = (JLabel) e.getSource();
								tmp.setBackground(Color.white);
							}
						}
					}
				});

				add(map[i][j]);
			}
		}

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					isChangeable = false;
					System.out.println("Enter is Clicked");
					ClearMaze cm = new ClearMaze(map);
					cm.start();
				}
			}
		});
	}

	private void changeClicked(String state) {
		switch (state) {
		case "left":
			isLeftMouseClicked = true;
			isRightMouseClicked = false;
			break;

		case "right":
			isLeftMouseClicked = false;
			isRightMouseClicked = true;
			break;

		default:
			isLeftMouseClicked = false;
			isRightMouseClicked = false;
			break;
		}
	}

	public static void main(String[] args) {
		new Labrinth(15, 15);
	}
}