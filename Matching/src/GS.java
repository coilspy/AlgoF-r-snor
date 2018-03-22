import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ma2536st-s on 22/03/18.
 */
public class GS {
    public static void main(String[] args) {

        BufferedReader reader;
        try {
            String fileNameIn = args[0];
            String fileNameOut = fileNameIn.replace("in", "out");
            reader = new BufferedReader(new FileReader(fileNameIn));
            for(int i=0;i<2;i++){
                reader.readLine();
            }
            int amount = Integer.parseInt(reader.readLine().split("=")[1]);
            int[][] priorities= new int[amount][amount];
            ArrayList<Man> men = new ArrayList<Man>();
            ArrayList<Woman> women = new ArrayList<Woman>();
            for(int i = 0; i < amount*2; i++)
            {
                String info[] = reader.readLine().split(" ");
                if((i + 1) % 2 == 1)
                {
                    men.add(new Man(info[1], Integer.parseInt(info[0])));
                }
                else{
                    women.add(new Woman(info[1],Integer.parseInt(info[0])));
                }
            }
            reader.readLine();
            for(int i = 0; i < (amount * 2); i++)
            {
                ArrayList<String> preferences  = new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
                String indexString=preferences.remove(0);
                int index = Integer.parseInt(indexString.split(":")[0]);
                if((index) % 2 == 1)
                {
                    men.get(((index-1)/2)).setPreferences(preferences);
                }
                else {
                    women.get((index/2)-1).setPreferences(preferences);
                }
            }
            for(int i=0;i < amount;i++){
                ArrayList<String> pref = women.get(i).preferences;
                for(int j=0;j < amount; j++){
                    priorities[i][Integer.parseInt(pref.get(j))/2]=j;
                }
            }

            match(men,women,priorities);
            printAllPairs(women, new BufferedWriter(new FileWriter(fileNameOut)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void match(ArrayList<Man> men, ArrayList<Woman> women, int [][] priorities){
        while(!men.isEmpty()){
            men.get(0).Propose(men, women,priorities);
        }
    }
    public static void printAllPairs(ArrayList<Woman> women, BufferedWriter writer) throws Exception
    {
        women.sort((Woman w1, Woman w2)->(w1.partner.id - w2.partner.id));
        for (Woman w: women) {
            writer.write(w.partner.name + " -- " + w.name+"\n");
        }
        writer.close();
    }
}
