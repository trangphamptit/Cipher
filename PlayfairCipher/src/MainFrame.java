import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 263);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnText = new JButton("Encrypt Text");
		btnText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlayfairCipher frame = new PlayfairCipher();
				frame.setVisible(true);
			}
		});
		btnText.setBounds(54, 76, 113, 66);
		frame.getContentPane().add(btnText);
		
		JButton btnFile = new JButton("Encrypt File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EncryptFile frame = new EncryptFile();
			}
		});
		btnFile.setBounds(218, 76, 113, 66);
		frame.getContentPane().add(btnFile);
	}
}
