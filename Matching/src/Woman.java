import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ma2536st-s on 22/03/18.
 */
public class Woman extends Person {

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
