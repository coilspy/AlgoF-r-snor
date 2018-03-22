import java.util.ArrayList;

/**
 * Created by ma2536st-s on 22/03/18.
 */
public abstract class Person {
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
