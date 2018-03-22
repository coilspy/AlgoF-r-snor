import java.util.ArrayList;

/**
 * Created by ma2536st-s on 22/03/18.
 */
public class Man extends Person {
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
