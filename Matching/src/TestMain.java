import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestMain {
    public static ArrayList<String> main(String args) {
        ArrayList<String> returnList = new ArrayList<>();
        Scanner reader;
        try {
           // String fileNameIn = args;
           // String fileNameOut = fileNameIn.replace("in", "out");
            reader =  new Scanner(System.in);
            for(int i=0;i<2;i++){
                reader.nextLine();
            }
            int amount = Integer.parseInt(reader.nextLine().split("=")[1]);
            int[][] priorities= new int[amount][amount];
            ArrayList<Man> men = new ArrayList<Man>();
            ArrayList<Woman> women = new ArrayList<Woman>();
            for(int i = 0; i < amount*2; i++)
            {
                String info[] = reader.nextLine().split(" ");
                if((i + 1) % 2 == 1)
                {
                    men.add(new Man(info[1], Integer.parseInt(info[0])));
                }
                else{
                    women.add(new Woman(info[1],Integer.parseInt(info[0])));
                }
            }
            reader.nextLine();
            for(int i = 0; i < (amount * 2); i++)
            {
                ArrayList<String> preferences  = new ArrayList<>(Arrays.asList(reader.nextLine().split(" ")));
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
            returnList = printAllPairs(women, returnList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public static void match(ArrayList<Man> men, ArrayList<Woman> women, int [][] priorities){
        while(!men.isEmpty()){
            men.get(0).Propose(men, women,priorities);
        }
    }
    public static ArrayList<String> printAllPairs(ArrayList<Woman> women, ArrayList<String> list) throws Exception
    {
        women.sort((Woman w1, Woman w2)->(w1.partner.id - w2.partner.id));
        for (Woman w: women) {
            System.out.println(w.partner.name + " -- " + w.name+"\n");
        }
        return list;
    }
}
