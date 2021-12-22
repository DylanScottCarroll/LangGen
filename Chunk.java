import java.util.ArrayList;
import java.util.Random;

public class Chunk {
    
    public static char[] CHARS =  {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ' ', '.', '!', '?', ',', '\"', '\'', ';', ':', '-', '(', ')', '/', '\n'};
    
    int[] data;

    public Chunk(){
        data = new int[CHARS.length];
    }

    public Chunk(int[] data_){
        data = data_;
    }

    public int get(char c){
       return data[getIndex(c)];
    }

    public void set(char c, int val){
        data[getIndex(c)] = val;
    }

    public String getData(){
        //Return a comma seperated list of the integers that make up the data
        
        String dataString = "";
        for(int i = 0; i < CHARS.length; i++){
            if(i != 0 ) dataString += ",";
            dataString += Integer.toString(data[i]);
        } 

        return dataString;
    }

    //get's a random character following the distribution
    public char getRandomChar(Random rng){
        
        int total = 0;
        for(int i = 0; i < CHARS.length; i ++){
            total += data[i];
        }

        
        if(total <= 0) return GetChar(rng.nextInt(CHARS.length));

        int number = rng.nextInt(total);

        int i = 0;
        for(i = 0; i < CHARS.length; i ++){
            number -= data[i];

            if(number < 0) break;
        }

        return GetChar(i);
        
    }

    //get's all of the ties for most common and randomizes from those
    public char getMostCommonCharRandom(Random rng){
        int max = data[0];
        ArrayList<Integer> maxIndex = new ArrayList<Integer>();
        maxIndex.add(0);
        

        for(int i = 0; i < CHARS.length; i++){
            if(data[i] == max){
                maxIndex.add(i);
            }
            else if(data[i] > max){
                max = data[i];
                maxIndex = new ArrayList<Integer>();
                maxIndex.add(i);
            }
        }
        int index = maxIndex.get(rng.nextInt(maxIndex.size()));

        return GetChar(index);

    }

    public char getLeastCommonCharRandom(Random rng){
        int min = data[0];
        ArrayList<Integer> minIndex = new ArrayList<Integer>();
        minIndex.add(0);
        

        for(int i = 0; i < CHARS.length; i++){
            if(data[i] == min){
                minIndex.add(i);
            }
            else if(data[i] < min){
                min = data[i];
                minIndex = new ArrayList<Integer>();
                minIndex.add(i);
            }
        }

        int index = minIndex.get(rng.nextInt(minIndex.size()));

        return GetChar(index);

    }

    public void iter(char c){
        data[getIndex(c)]++;
    }

    public static int getIndex(char c){
        for(int i = 0; i < CHARS.length; i++){
            if(c == CHARS[i]){
                return i;
            }
        }

        return -1;
    }

    public static char GetChar(int index){
        return CHARS[index];
    }

    public static boolean isValidChar(char c){
        return (getIndex(c) != -1);
    }

}