import java.io.FilenameFilter;
import java.util.ArrayList;

class Trainer{

    public static void main(String[] args) {
        //TODO: Move these into a config file

        int back = 6;
        String filename = "../datas/Sherlock Holmes.txt";

        Train(back, filename);
    }

    public static void Train(int back, String filename){
        TrainingStream ts = new TrainingStream(filename);
        ChunkInterfacer cIO = new ChunkInterfacer();

        ArrayList<Character> queue =  new ArrayList<Character>();
        for(int i = 0; i < back; i++){
            queue.add(ts.nextChar());
        }

        char nextChar = ts.nextChar();

        int count = 0;
        while((nextChar = ts.nextChar()) != '\0' )
        {
            cIO.updateChunk(qToString(queue) , nextChar);
            
            queue.add(nextChar);
            queue.remove(0);

            
            if(count++ % 1000 == 0) System.out.println( 100*(count/((float) ts.fileLength )) );
        }

        System.out.println( 100*(count/((float) ts.fileLength)));

        cIO.save();
    }

    //converts an ArrayList of chars to a string
    public static String qToString(ArrayList<Character> list){
        String str = "";
        for(int i = 0; i < list.size(); i++){
            char c = list.get(i);
            str += c;
        }
        return str;
    }


}