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
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PlayfairCipher extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	JTextArea plainText;
	JTextArea key;
	JTextArea cipherText;
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
		
		JLabel lblThMcLu = new JLabel("Plain text:");
		lblThMcLu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThMcLu.setBounds(12, 28, 66, 14);
		panel.add(lblThMcLu);
		
		plainText = new JTextArea();
		plainText.setBounds(102, 11, 278, 37);
		panel.add(plainText);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKey.setBounds(12, 75, 37, 14);
		panel.add(lblKey);
		
		key = new JTextArea();
		key.setBounds(102, 59, 278, 37);
		panel.add(key);
		
		JList list = new JList();
		list.setBounds(127, 111, 1, 1);
		panel.add(list);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caesar Cipher", "Monoalphabetic Cipher", "Playfair Cipher"}));
		comboBox.setBounds(102, 111, 278, 20);
		panel.add(comboBox);
		
		JLabel label = new JLabel("");
		label.setBounds(30, 112, 46, 14);
		panel.add(label);
		
		JButton btnEncrypt = new JButton("Encryption");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(plainText.getText().equals("")){
					return;
				}
				if(comboBox.getSelectedIndex() == 0){
					if(key.getText().equals("")){
						return;
					}
					cipherText.setText(Caesar.encrypt(plainText.getText(), Integer.parseInt(key.getText())));
				}
				if(comboBox.getSelectedIndex() == 1){
					key.setText(Monoalphabetic.generateKey());
					cipherText.setText(Monoalphabetic.encrypt(plainText.getText(), key.getText()));
				}
				if(comboBox.getSelectedIndex() == 2){
					PlayFair x = new PlayFair();
					x.setKey(key.getText());
					cipherText.setText(x.encrypt(plainText.getText()));
				}
			}
		});
		btnEncrypt.setBackground(Color.CYAN);
		btnEncrypt.setBounds(102, 142, 89, 23);
		panel.add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decryption");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cipherText.getText().equals("")){
					return;
				}
				if(comboBox.getSelectedIndex() == 0){
					if(key.getText().equals("")){
						return;
					}
					plainText.setText(Caesar.decrypt(cipherText.getText(), Integer.parseInt(key.getText())));
				}
				if(comboBox.getSelectedIndex() == 1){
					key.setText(Monoalphabetic.generateKey());
					plainText.setText(Monoalphabetic.decrypt(cipherText.getText(), key.getText()));
				}
				if(comboBox.getSelectedIndex() == 2){
					PlayFair x = new PlayFair();
					x.setKey(key.getText());
					plainText.setText(x.decrypt(cipherText.getText()));
				}
			}
		});
		btnDecrypt.setBackground(Color.CYAN);
		btnDecrypt.setBounds(291, 142, 89, 23);
		panel.add(btnDecrypt);
		
		JLabel lblCiph = new JLabel("Cipher text:");
		lblCiph.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCiph.setBounds(12, 192, 80, 14);
		panel.add(lblCiph);
		
		cipherText = new JTextArea();
		cipherText.setBounds(102, 176, 278, 42);
		panel.add(cipherText);
	}
}
