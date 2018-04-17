import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class spanning {

    public static void main(String[] args) {

        String inputFile = args[0];
        String[] input = readFromFile(inputFile);
        List<Vertices> cities = getCitiesFromInput(input);
        int breakpoint = Integer.parseInt(cities.remove(cities.size()).name);

        for(int i = breakpoint; i < input.length; i++)
        {
            //Read from file content
            String line = input[i];
            //Split line by --
            String[] sides = line.split("--");
            //get the index of the [ on the right hand side of "--"
            int indexOfOpenBracket = sides[1].indexOf("[");
            //remove the end "]" to extract the length-integer
            int distance = Integer.parseInt(sides[1].substring(indexOfOpenBracket+1 ,sides[1].length() -1));
            //remove everything past and including [ in the string to get the city string.
            String secondCity = sides[1].substring(0,indexOfOpenBracket -1);
            //Retrieve city on left hand side and add an edge with destination set to left hand city and the distance specified.
            getCityWithName(sides[0], cities).addEdge(new Edge(distance, getCityWithName(secondCity,cities)));
        }


        //Prim/kruskals algorithm

        //TODO: Implement algorithm for spanning tree.
    }


    public static Vertices getCityWithName(String name, List<Vertices> cities)
    {
        for (Vertices v: cities) {
            if(v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }

    public static String[] readFromFile(String fileName)
    {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            List<String> fileContent = new ArrayList<>();
            String line = null;
            while((line = reader.readLine()) != null)
            {
                fileContent.add(line);
            }
            return fileContent.toArray(new String[fileContent.size()]);
        }
        catch(Exception e)
        {
            System.out.println("Error reading file");
        }
        return null;
    }
    public static List<Vertices> getCitiesFromInput(String[] input)
    {
        List<Vertices> cities = new ArrayList<>();
        int counter = 0;
        for(String city : input)
        {

            if(city.contains("-"))
            {
                break;
            }
            counter++;
            cities.add(new Vertices(city));
        }
        cities.add(new Vertices("" + counter));
        return cities;
    }


}
