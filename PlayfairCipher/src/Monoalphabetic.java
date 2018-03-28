

import java.awt.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Monoalphabetic {
	static String alphabet = "abcdefghijklmnopqrstuvwxyz ";
	public static void main(String[] args) {
		String text, cipherText, plainText;
		String key = generateKey();
		text = "hello everyone";
		System.out.println(key);
		cipherText = encrypt(text, key);
		System.out.println(cipherText);
		plainText = decrypt(cipherText, key);
		System.out.println(plainText);
	}
	
	public static String encrypt(String plainText, String key){
		String cipher = "";
		for(int i = 0; i < plainText.length(); i++){
			int index = alphabet.indexOf(plainText.charAt(i));
			cipher += key.charAt(index); 
		}
		return cipher;
	}
	
	public static String decrypt(String cipherText, String key){
		String plain = "";
		for(int i = 0; i < cipherText.length(); i++){
			int index = key.indexOf(cipherText.charAt(i));
			plain += alphabet.charAt(index); 
		}
		return plain;
	}
	
	public static String generateKey(){
		return shuffle(alphabet);
	}
	
	public static String shuffle(String text){
		Random rnd = ThreadLocalRandom.current();
		char tmpText[] = text.toCharArray();
		for(int i = tmpText.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			char ch = tmpText[index];
			tmpText[index] = tmpText[i];
			tmpText[i] = ch;
		}
		return new String(tmpText);
	}
}
