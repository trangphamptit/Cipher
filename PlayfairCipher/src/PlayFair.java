import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class PlayFair {
    public static String keyWord        = new String();
    private static char matrix[][] 	  = new char[5][5];
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args)
    {
        PlayFair x = new PlayFair();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a keyword:");
        String keyword = sc.nextLine();
        x.setKey(keyword);
        System.out.println("Enter word to encrypt: ");
        String key_input = sc.nextLine();
        System.out.println("Encryption: " + x.encrypt(key_input));
        System.out.println("Encryption: " + x.decrypt(x.encrypt(key_input)));
        sc.close();
    }
    
    public void setKey(String k)
    {
    	Set<Character> charSet = new LinkedHashSet<Character>();
    	k = k.replace(" ", "");
    	k += alphabet;
        for(char ch: k.toCharArray()){
        	if(ch == 'j')
        		continue;
            charSet.add(ch);
        }
        for(Character ch: charSet){
        	keyWord += ch;
        }
//        System.out.println(keyWord);
        createMatrix();
    }
 
    private void createMatrix()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix[i][j] = keyWord.charAt(i*5 + j);
//                System.out.print(matrix[i][j] + " ");
            }
//            System.out.println();
        }
    }
 
    private static String format(String old_text)
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
 
    private static String[] Divid2Pairs(String new_string)
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
 
    private static int[] findLetterPos(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }
 
    public static String encrypt(String Source)
    {
    	Source = Source.replace(" ", "");
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
            Code = Code + matrix[pos1[0]][pos1[1]]
                    + matrix[pos2[0]][pos2[1]];
        }
        return Code;
    }
    
	public static String decrypt(String Code)
	{
	    String Original = new String();
	    String src_arr[] = Divid2Pairs(Code);
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
	            if (pos1[1] > 0)
	                pos1[1]--;
	            else
	                pos1[1] = 4;
	            if (pos2[1] > 0)
	                pos2[1]--;
	            else
	                pos2[1] = 4;
	        }
	        else if (pos1[1] == pos2[1])
	        {
	            if (pos1[0] > 0)
	                pos1[0]--;
	            else
	                pos1[0] = 4;
	            if (pos2[0] > 0)
	                pos2[0]--;
	            else
	                pos2[0] = 4;
	        }
	        else
	        {
	            int temp = pos1[1];
	            pos1[1] = pos2[1];
	            pos2[1] = temp;
	        }
	        Original = Original + matrix[pos1[0]][pos1[1]]
	                + matrix[pos2[0]][pos2[1]];
	    }
	    return Original;
	}
}