package teamP;

import java.awt.Color;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JLabel;

public class ClearMaze extends Thread {
	private Stack<HashMap<String, String>> stack = new Stack<HashMap<String, String>>();
	private JLabel[][] map;
	private int maxWidth;
	private int maxHeight;
	
	private final int DELAY_COUNT = 30;

	public ClearMaze(JLabel[][] map) {
		this.map = map;
		maxWidth = map[0].length;
		maxHeight = map.length;

		System.out.println("maxWidth : " + maxWidth + " / maxHeight : " + maxHeight);
	}

	@Override
	public void run() {
		int i = 0, j = 0;
		push(i, j);
		map[i][j].setBackground(Color.red);
		System.out.println("stack size = " + stack.size());
		while (stack.size() != 0) {
			findWay();
		}

	}

	private synchronized void findWay() {
		try {
			wait(DELAY_COUNT);
			HashMap<String, String> hm = stack.pop();
			int x = getIntegerValue(hm, "x");
			int y = getIntegerValue(hm, "y");

			int count = 0;
			int reverseCount = -1;

			if (isPossible(x + 1, y)) {
				push(x + 1, y);
				count++;
			}
			if (isPossible(x - 1, y)) {
				push(x - 1, y);
				count++;
			}
			if (isPossible(x, y + 1)) {
				push(x, y + 1);
				count++;
			}
			if (isPossible(x, y - 1)) {
				push(x, y - 1);
				count++;
			}

			if (count == 0) {
				map[x][y].setBackground(Color.white);
				int tmpX = x;
				int tmpY = y;
				while (true) {
					wait(DELAY_COUNT);
					reverseCount = 0;
					int redX = 0;
					int redY = 0;
					if (isRed(tmpX+1, tmpY)) {
						redX = tmpX + 1;
						redY = tmpY;
						reverseCount++;
					}
					if (isRed(tmpX-1, tmpY)) {
						redX = tmpX - 1;
						redY = tmpY;
						reverseCount++;
					}
					if (isRed(tmpX, tmpY+1)) {
						redX = tmpX;
						redY = tmpY + 1;
						reverseCount++;
					}
					if (isRed(tmpX, tmpY-1)) {
						redX = tmpX;
						redY = tmpY - 1;
						reverseCount++;
					}

					if (reverseCount != 1) {
						map[tmpX][tmpY].setBackground(Color.red);
						break;						
					}
					else {
						map[redX][redY].setBackground(Color.white);
						tmpX = redX;
						tmpY = redY;
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void push(int x, int y) {
		HashMap<String, String> tmp = new HashMap<String, String>();
		tmp.put("x", Integer.toString(x));
		tmp.put("y", Integer.toString(y));
		stack.push(tmp);
	}

	private int getIntegerValue(HashMap<String, String> hm, String key) {
		return Integer.parseInt(hm.get(key));
	}


	private boolean isPossible(int x, int y) {
		if (x < 0 || y < 0 || x >= maxWidth || y >= maxHeight)
			return false;
		else if (map[x][y].getBackground() == Color.black || map[x][y].getBackground() == Color.red)
			return false;

		System.out.println("x = " + x + " y = " + y);
		map[x][y].setBackground(Color.red);
		return true;
	}
	
	private boolean isRed(int x, int y) {
		if (x < 0 || y < 0 || x >= maxWidth || y >= maxHeight)
			return false;
		else if (map[x][y].getBackground() == Color.red)
			return true;
		return false;
	}


}
