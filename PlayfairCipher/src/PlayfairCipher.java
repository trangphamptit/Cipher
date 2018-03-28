import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import client.RemoteClient;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class PlayfairCipher extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayfairCipher frame = new PlayfairCipher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayfairCipher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCaesarEncrypt = new JButton("Caesar Encryption");
		btnCaesarEncrypt.setBounds(10, 95, 181, 23);
		btnCaesarEncrypt.setBackground(Color.GREEN);
		panel.add(btnCaesarEncrypt);
		
		JButton btnCaesarCipher = new JButton("Monoalphabetic Encryption");
		btnCaesarCipher.setBounds(10, 137, 181, 23);
		btnCaesarCipher.setBackground(Color.RED);
		panel.add(btnCaesarCipher);
		
		JButton btnPlayfairCipher = new JButton("Playfair Encryption");
		btnPlayfairCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlayfairCipher.setBounds(10, 183, 181, 23);
		btnPlayfairCipher.setBackground(Color.YELLOW);
		panel.add(btnPlayfairCipher);
		
		JLabel lblThMcLu = new JLabel("Plain text file");
		lblThMcLu.setBounds(30, 38, 105, 14);
		panel.add(lblThMcLu);
		
		textField = new JTextField();
		textField.setBounds(114, 35, 191, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnVal = jfc.showOpenDialog(PlayfairCipher.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					textField.setText(file.getAbsolutePath());
				}
			}
		});
		btnOpen.setBackground(Color.CYAN);
		btnOpen.setBounds(315, 34, 89, 23);
		panel.add(btnOpen);
		
		JButton btnCaesarDecryption = new JButton("Caesar Decryption");
		btnCaesarDecryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCaesarDecryption.setBackground(Color.GREEN);
		btnCaesarDecryption.setBounds(221, 95, 168, 23);
		panel.add(btnCaesarDecryption);
		
		JButton btnMonoalphabeticDecryption = new JButton("Monoalphabetic Decryption");
		btnMonoalphabeticDecryption.setBackground(Color.RED);
		btnMonoalphabeticDecryption.setBounds(221, 137, 168, 23);
		panel.add(btnMonoalphabeticDecryption);
		
		JButton btnPlayfairDecryption = new JButton("Playfair Decryption");
		btnPlayfairDecryption.setBackground(Color.YELLOW);
		btnPlayfairDecryption.setBounds(221, 183, 168, 23);
		panel.add(btnPlayfairDecryption);
	}
}
