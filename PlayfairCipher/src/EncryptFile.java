import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EncryptFile {

	private JFrame frame;
	private JPanel contentPane;
	private JComboBox comboBox;
	JTextArea plainText;
	JTextArea key;
	JTextArea cipherText;
	File filePlain;
	File fileCipher;
	String contentPlain = new String();
	String contentCipher = new String();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptFile window = new EncryptFile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EncryptFile() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 496, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 460, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblThMcLu = new JLabel("Plain text file:");
		lblThMcLu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThMcLu.setBounds(12, 28, 91, 14);
		panel.add(lblThMcLu);
		
		plainText = new JTextArea();
		plainText.setBounds(125, 24, 229, 20);
		panel.add(plainText);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKey.setBounds(12, 75, 37, 14);
		panel.add(lblKey);
		
		key = new JTextArea();
		key.setBounds(125, 71, 229, 20);
		panel.add(key);
		
		JList list = new JList();
		list.setBounds(127, 111, 1, 1);
		panel.add(list);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caesar Cipher", "Monoalphabetic Cipher", "Playfair Cipher"}));
		comboBox.setBounds(125, 109, 229, 20);
		panel.add(comboBox);
		
		JLabel label = new JLabel("");
		label.setBounds(30, 112, 46, 14);
		panel.add(label);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(contentPlain.equals("")|| cipherText.getText().equals("")){
					return;
				}
				if(comboBox.getSelectedIndex() == 0){
					if(key.getText().equals("")){
						return;
					}
					System.out.println(contentPlain);
					contentCipher = Caesar.encrypt(contentPlain, Integer.parseInt(key.getText()));
					saveFile(contentCipher, fileCipher);
				}
				if(comboBox.getSelectedIndex() == 1){
					key.setText(Monoalphabetic.generateKey());
					contentCipher = Monoalphabetic.encrypt(contentPlain, key.getText());
					saveFile(contentCipher, fileCipher);
				}
				if(comboBox.getSelectedIndex() == 2){
					PlayFair x = new PlayFair();
					x.setKey(key.getText());
					contentCipher = x.encrypt(contentPlain);
					saveFile(contentCipher, fileCipher);
				}
			}
		});
		btnEncrypt.setBackground(Color.CYAN);
		btnEncrypt.setBounds(76, 188, 89, 23);
		panel.add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(contentCipher.equals("") || plainText.getText().equals("")){
					return;
				}
				if(comboBox.getSelectedIndex() == 0){
					if(key.getText().equals("")){
						return;
					}
					System.out.println(contentCipher);
					contentPlain = Caesar.decrypt(contentCipher, Integer.parseInt(key.getText()));
					saveFile(contentPlain, filePlain);
					
				}
				if(comboBox.getSelectedIndex() == 1){
					if(key.getText().length() != 27 ){
						return;
					}
					contentPlain = Monoalphabetic.decrypt(contentCipher, key.getText());
					saveFile(contentPlain, filePlain);
				}
				if(comboBox.getSelectedIndex() == 2){
					PlayFair x = new PlayFair();
					x.setKey(key.getText());
					contentPlain = x.decrypt(contentCipher);
					saveFile(contentPlain, filePlain);
				}
			}
		});
		btnDecrypt.setBackground(Color.CYAN);
		btnDecrypt.setBounds(280, 188, 89, 23);
		panel.add(btnDecrypt);
		
		JLabel lblCiph = new JLabel("Cipher text file:");
		lblCiph.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCiph.setBounds(12, 147, 100, 14);
		panel.add(lblCiph);
		
		cipherText = new JTextArea();
		cipherText.setBounds(125, 143, 229, 20);
		panel.add(cipherText);
		
		JButton btnOpenPlainText = new JButton("Open");
		btnOpenPlainText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(frame);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	filePlain = new File(chooser.getSelectedFile().getAbsolutePath());
			    	plainText.setText(filePlain.getAbsolutePath());
			    	try {
						FileReader fr = new FileReader(filePlain);
						BufferedReader br = new BufferedReader(fr);
						int i;
						while((i = br.read()) != -1){
							contentPlain += (char)(i);
						}
						br.close();
						fr.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    }
			}
		});
		btnOpenPlainText.setBounds(369, 25, 70, 23);
		panel.add(btnOpenPlainText);
		
		JButton btnOpenCipherText = new JButton("Open");
		btnOpenCipherText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(frame);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	fileCipher = new File(chooser.getSelectedFile().getAbsolutePath());
			    	cipherText.setText(fileCipher.getAbsolutePath());
			    	try {
						FileReader fr = new FileReader(fileCipher);
						BufferedReader br = new BufferedReader(fr);
						int i;
						System.out.println(contentCipher);
						while((i = br.read()) != -1){
							contentCipher += (char)(i);
						}
						System.out.println(contentCipher);
						br.close();
						fr.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    }
			}
		});
		btnOpenCipherText.setBounds(369, 144, 70, 23);
		panel.add(btnOpenCipherText);
	}
	
	private void saveFile(String content, File file){
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			int i;
//			while((i = bw.) != -1){
//				bw.write(
//			}
			bw.write(content);
			bw.close();
			fw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

