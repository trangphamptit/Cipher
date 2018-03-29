import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {
    private String keyWord        = new String();
    private ArrayList<ArrayList<Character>> matrix 	  = new ArrayList<ArrayList<Character>>();
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args)
    {
        Test x = new Test();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a keyword:");
        String keyword = sc.next();
        x.setKey(keyword);
        System.out.println("Enter word to encrypt: ");
        String key_input = sc.next();
        System.out.println("Encryption: " + x.encryptMessage(key_input));
        sc.close();
    }
    
    public void setKey(String k)
    {
    	Set<Character> charSet = new LinkedHashSet<Character>();
    	k += alphabet;
        for(char ch: k.toCharArray()){
//        	if(ch == 'j')
//        		continue;
            charSet.add(ch);
        }
        for(Character ch: charSet){
        	keyWord += ch;
        }
        System.out.println(keyWord);
        createMatrix();
    }
 
    private void createMatrix()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
            	matrix.add(new ArrayList<Character>());
                matrix.get(i).add(new Character(keyWord.charAt(i*5 + j)));
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
 
    private String format(String old_text)
    {
        int len = 0;
        String text = old_text.replace('j', 'i');
        len = text.length();
        String pair = new String();
        for (int i = 0; i < len; i++)
        {
            if (i == (len - 1) || text.charAt(i + 1) == text.charAt(i))
            {
                pair += text.substring(i, i + 1) + 'x';
            } else {
            	pair += text.substring(i, i + 2);
            	i++;
            }
        }
        return pair;
    }
 
    private String[] Divid2Pairs(String new_string)
    {
        String Original = format(new_string);
        int size = Original.length();
        String x[] = new String[size / 2];
        for (int i = 0; i < size; i += 2)
        {
            x[i / 2] = Original.substring(i, i + 2);
        }
        return x;
    }
 
    public int[] findLetterPos(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix.get(i).get(j) == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }
 
    public String encryptMessage(String Source)
    {
        String src_arr[] = Divid2Pairs(Source);
        String Code = new String();
        char one;
        char two;
        int pos1[] = new int[2];
        int pos2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            pos1 = findLetterPos(one);
            pos2 = findLetterPos(two);
            if (pos1[0] == pos2[0])
            {
                if (pos1[1] < 4)
                    pos1[1]++;
                else
                    pos1[1] = 0;
                if (pos2[1] < 4)
                    pos2[1]++;
                else
                    pos2[1] = 0;
            }
            else if (pos1[1] == pos2[1])
            {
                if (pos1[0] < 4)
                    pos1[0]++;
                else
                    pos1[0] = 0;
                if (pos2[0] < 4)
                    pos2[0]++;
                else
                    pos2[0] = 0;
            }
            else
            {
            	// exchange row and col
                int temp = pos1[1];
                pos1[1] = pos2[1];
                pos2[1] = temp;
            }
            Code = Code + matrix.get(pos1[0]).get(pos1[1])
                    + matrix.get(pos2[0]).get(pos2[1]);
        }
        return Code;
    }
}
