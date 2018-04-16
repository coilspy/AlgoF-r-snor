import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by ma2536st-s on 16/04/18.
 */
public class Main {
    public static void main(String[] args)
    {
        String wordFile = args[0];
        String wordPairs = args[1];

        String[] words = readFromFile(wordFile);
        String[] pairs = readFromFile(wordPairs);
        //Build the graph
        boolean[][] wordGraph = buildGraph(words);
        //Graph is built.
        // nod utgång: föralla grannar, kolla grannar, tills mål är funnet.
    }



    public static String[] readFromFile(String input) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(input)));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[lines.size()]);
        }
        catch(Exception e) {
            System.out.println("Error reading file");
            return null;
        }
    }

    public static boolean[][] buildGraph(String[] words){
        boolean[][] wordGraph = new boolean[words.length][words.length];
        for(int i = 0; i < words.length; i++)
        {
            for(int j = 0; j < words.length; j++)
            {
                if(isPair(words[i],words[j]))
                {
                    wordGraph[i][j]=true;
                }
            }
        }

        return wordGraph;
    }

    public static boolean isPair(String s1, String s2) {
        String s1temp = s1.substring(1);
        int[] hasBeenUsed = new int[5];
        char[] s1Chars = s1temp.toCharArray();
        char[] s2Chars = s2.toCharArray();
        for (int i = 0; i < 4; i++) {
            boolean wasUsed = false;
            for (int j = 0; j < 5; j++) {
                if(s1Chars[i] == s2Chars[j] && hasBeenUsed[j] == 0)
                {
                    hasBeenUsed[j] = 1;
                    wasUsed = true;
                    break;
                }
            }
            if(!(wasUsed)){
                return false;
            }
        }
        return true;
    }

    public static int BFS(boolean[][] graph, String start, String finish, String[] words)
    {
        ArrayList<String> visited = new ArrayList<String>();
        visited.add(start);
        ArrayList<String> nodes = new ArrayList<String>();
        nodes.add(start);
        while(!nodes.isEmpty())
        {
            String currentNode = nodes.get(0);
            int index = getIndex(words,currentNode);
            for(int i = 0; i < words.length;i++)
            {
                if(graph[index][i])
                {
                    nodes.add(words[i]);
                }
            }
        }
        return -1;
    }
    public static int getIndex(String[] words, String word)
    {
        for(int i = 0; i < words.length;i++)
        {
            if(words[i] == word)
            {
                return i;
            }
        }
    }
    }

