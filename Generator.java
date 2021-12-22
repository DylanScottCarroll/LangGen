import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Random;
import java.lang.Integer;


class Generator{

    public static void main(String[] args) {
        //TODO: Move these parameteres into a config file
        String seed = "Sherlock Holmes";
        int back = 6;
        int length = 10000;
        
        
        
        Generate(seed, back, length);
    }

    public static void Generate(String start, int back, int length){
        ChunkInterfacer cIO = new ChunkInterfacer();
        cIO.load();

        Random rng = new Random();

        FileWriter writer;
        try{
            File file = new File("../output.txt");
            //if the file doesn't exist, create it
            file.createNewFile();
            writer = new FileWriter(file);
            
        }
        catch (IOException e){
            throw new RuntimeException("IOException");
        }

        //Setup the queue
        ArrayList<Character> queue =  new ArrayList<Character>();
        for(int i = 0; i < back; i ++){
            queue.add(start.charAt(i));
            System.out.print(start.charAt(i));
        }


        //Generate
        for(int i = 0; i < length; i ++)
        {
            String seed = Trainer.qToString(queue);
            Chunk chunk = cIO.getChunk(seed);

            char nextChar = chunk.getRandomChar(rng);

            System.out.print(nextChar);

            try{writer.write(nextChar);}
            catch(IOException e){throw new RuntimeException("IOException");}

            queue.add(nextChar);
            queue.remove(0);                
        }

        try{ writer.close();}
        catch(IOException e){throw new RuntimeException("IOException");}
    }

}