import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class stablemarriage {
    public static void main(String args[]) {
        Scanner reader;
        try {
            reader =  new Scanner(System.in);
                //Hantera #
                //Om raden innehåller #, läs in igen, annars hoppa ur
                String text = reader.nextLine();
                while(text.contains("#"))
                {
                    text = reader.nextLine();
                }
                int amount = Integer.parseInt(text.split("=")[1]);
                int[][] priorities = new int[amount][amount];
                ArrayList<Man> men = new ArrayList<Man>();
                ArrayList<Woman> women = new ArrayList<Woman>();
                for (int i = 0; i < amount * 2; i++) {
                    String info[] = reader.nextLine().split(" ");
                    if ((i + 1) % 2 == 1) {
                        men.add(new Man(info[1], Integer.parseInt(info[0])));
                    } else {
                        women.add(new Woman(info[1], Integer.parseInt(info[0])));
                    }
                }
                reader.nextLine();
                for (int i = 0; i < (amount * 2); i++) {
                    ArrayList<String> preferences = new ArrayList<>(Arrays.asList(reader.nextLine().split(" ")));
                    String indexString = preferences.remove(0);
                    int index = Integer.parseInt(indexString.split(":")[0]);
                    if ((index) % 2 == 1) {
                        men.get(((index - 1) / 2)).setPreferences(preferences);
                    } else {
                        women.get((index / 2) - 1).setPreferences(preferences);
                    }
                }
                for (int i = 0; i < amount; i++) {
                    ArrayList<String> pref = women.get(i).preferences;
                    for (int j = 0; j < amount; j++) {
                        priorities[i][Integer.parseInt(pref.get(j)) / 2] = j;
                    }
                }
                if (!reader.hasNextLine()) {
                    reader.close();
                }

                match(men, women, priorities);
                printAllPairs(women);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void match(ArrayList<Man> men, ArrayList<Woman> women, int [][] priorities){
        while(!men.isEmpty()){
            men.get(0).Propose(men, women,priorities);
        }
    }
    public static void printAllPairs(ArrayList<Woman> women) throws Exception
    {
        women.sort((Woman w1, Woman w2)->(w1.partner.id - w2.partner.id));
        for (Woman w: women) {
            System.out.println(w.partner.name + " -- " + w.name+"\n");
        }
    }
}

abstract class Person {
    int id;
    String name;
    boolean taken;
    Person partner;
    ArrayList<String> preferences;

    public Person(String name, int id)
    {
        this.name = name;
        this.id = id;
    }
    public void setPreferences(ArrayList preferences)
    {
        this.preferences=preferences;
    }
    public void removeFromPreferences(Person p){
        preferences.remove(p);
    };

    public void setTaken(Boolean b, Person p){
        taken = b;
        partner = p;
    };
}
class Man extends Person {
    public Man(String name, int id)
    {
        super(name, id);
    }
    public void Propose(ArrayList<Man> men,ArrayList<Woman> women, int [][] priorities)
    {
        //System.out.println(women.size() );
        // System.out.println(this.preferences.size());
        women.get((Integer.parseInt(preferences.get(0))/2) - 1).Decide(this,men, priorities);
        this.preferences.remove(0);
    }
}
class Woman extends Person {

    public Woman(String name, int id) {
        super(name, id);
    }

    public boolean Decide(Man man, ArrayList<Man> men, int [][] priorities) {
        if (!taken) {
            setTaken(true, man);
            men.remove(0);
            return true;
        }
        else{
            if (priorities[(id/2)-1][partner.id/2] < priorities[(id/2)-1][man.id/2]) {
                return false;
            } else {
                men.add((Man) partner);
                setTaken(true, man);
                men.remove(0);
                return true;
            }
        }
    }
}
