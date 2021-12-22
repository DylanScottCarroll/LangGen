import java.util.HashMap;
import java.io.*;

public class ChunkInterfacer {
    
    HashMap<String, int[]> data = new HashMap<String, int[]>();

    //Get the chunk object for a given seed string
    public Chunk getChunk(String str){
        int[] dist = data.get(str);
        if(dist == null){
            dist = new int[Chunk.CHARS.length];
        }

        Chunk newChunk = new Chunk(dist);
        return newChunk;
    }


    public void updateChunk(String str, char c){
        if(! data.containsKey(str)){
            data.put(str, new int[Chunk.CHARS.length]);
        }
        
        
        int index = Chunk.getIndex(c);
        if(index == -1){ return; }

        data.get(str)[index] += 1;
        
        
    }

    public void save(){
        try {
            FileOutputStream fileOut = new FileOutputStream("../model.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(data);
            
            out.close();
            fileOut.close();
         } catch (IOException i) {
            i.printStackTrace();
         }
    } 

    public void load(){
        try {
            FileInputStream fileIn = new FileInputStream("../model.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            @SuppressWarnings("unchecked")
            HashMap<String, int[]> newData = (HashMap<String, int[]>) in.readObject();
            
            data = newData;
            
            in.close();
            fileIn.close();
         } catch (IOException i) {
            i.printStackTrace();
            return;
         } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
         }
    }


}