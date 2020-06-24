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

	public ClearMaze(JLabel[][] map) {
		this.map = map;
		maxWidth = map[0].length;
		maxHeight = map.length;

		System.out.println(" maxWidth : " + maxWidth + " / maxHeight : " + maxHeight);
	}

	@Override
	public void run() {

		int i = 0, j = 0;
		push(i, j);
		map[i][j].setBackground(Color.red);
		System.out.println("stack size = " + stack.size());
		while (stack.size() != 0) {
			a();
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

	private synchronized void a() {
		try {
			wait(100);

			HashMap<String, String> hm = stack.pop();
			int x = getIntegerValue(hm, "x");
			int y = getIntegerValue(hm, "y");

			if (isPossible(x + 1, y))
				push(x + 1, y);
			if (isPossible(x - 1, y))
				push(x - 1, y);
			if (isPossible(x, y + 1))
				push(x, y + 1);
			if (isPossible(x, y - 1))
				push(x, y - 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
