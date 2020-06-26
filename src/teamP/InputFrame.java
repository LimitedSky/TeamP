package teamP;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputFrame extends JFrame {

	private final int width = 350;
	private final int height = 120;

	private JTextField widthCount;
	private JTextField heightCount;
	private JLabel multiply;
	private JButton startBtn;
	private Labrinth lab;

	public InputFrame() {
		// get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int) ((screenSize.getWidth() - width) / 2);
		int yPos = (int) ((screenSize.getHeight() - height) / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("insert block width x height");
		setBounds(xPos, yPos, width, height);
		setSize(width, height);

		multiply = new JLabel("x");
		widthCount = new JTextField(3);
		heightCount = new JTextField(3);
		startBtn = new JButton("start!!");

		startBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				lab = new Labrinth(Integer.parseInt(widthCount.getText()), Integer.parseInt(heightCount.getText()));
				if (lab != null)
					System.out.println("make labrinth");
			}
		});

		widthCount.setFont(new Font("고딕", Font.PLAIN, 25));
		heightCount.setFont(new Font("고딕", Font.PLAIN, 25));
		multiply.setFont(new Font("고딕", Font.PLAIN, 25));
		startBtn.setFont(new Font("고딕", Font.PLAIN, 25));
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		c.add(widthCount);
		c.add(multiply);
		c.add(heightCount);
		c.add(startBtn);

		setVisible(true);
	}

}