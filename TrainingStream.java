import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class TrainingStream {
    
    BufferedReader reader;
    public int fileLength;


    public TrainingStream(String filename){
        try
        {
            File file = new File(filename);
            fileLength = (int) file.length();
            reader = new BufferedReader(new FileReader(file));
        }
        catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    public char nextChar(){
        try
        {
            char c = '\0';

            //repeat until the next valid char is reached
            while(! Chunk.isValidChar(c)){
                int cInt = reader.read();

                //if cInt is -1 the end of file has been reached
                //send null char to indicate that
                if(cInt == -1) return '\0';

                c = (char) cInt;
                
            }
            
            return c;
        }    
        catch(IOException o){
            throw new RuntimeException("IOException");
        }   
    }


    public void close(){
        try{
            reader.close();
        }
        catch(IOException o)
        {
            throw new RuntimeException("IOException");
        }  
    }
}