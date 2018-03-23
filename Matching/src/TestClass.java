import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TestClass {
    public static void main(String[] args)
    {
        String[] inFilePaths = {"sm-bbt-in.txt", "sm-friends-in.txt", "sm-illiad-in.txt", "sm-kt-p-4-in.txt", "sm-kt-p-5-in.txt", "sm-random-5-in.txt", "sm-random-50-in.txt", "sm-random-500-in.txt",
        "sm-worst-5-in.txt", "sm-worst-50-in.txt", "sm-worst-500-in.txt"};
        String[] outFilePaths = {"sm-bbt-out.txt", "sm-friends-out.txt", "sm-illiad-out.txt", "sm-kt-p-4-out.txt", "sm-kt-p-5-out.txt", "sm-random-5-out.txt", "sm-random-50-out.txt", "sm-random-500-out.txt",
                "sm-worst-5-out.txt", "sm-worst-50-out.txt", "sm-worst-500-out.txt"};
        String[] ourOutFilePaths = {"sm-bbt-ourOut.txt", "sm-friends-ourOut.txt", "sm-illiad-ourOut.txt", "sm-kt-p-4-ourOut.txt", "sm-kt-p-5-ourOut.txt", "sm-random-5-ourOut", "sm-random-50-ourOut.txt", "sm-random-500-ourOut.txt",
                "sm-worst-5-ourOut.txt", "sm-worst-50-ourOut.txt", "sm-worst-500-ourOut.txt"};
        for(int i = 0; i < ourOutFilePaths.length; i++) {
            try {

                ArrayList<String> ourFile = new ArrayList<>();
                // BufferedReader ourReader = new BufferedReader(new FileReader(ourOutFilePaths[i]));
                BufferedReader expectedResultReader;// = new BufferedReader(new FileReader(outFilePaths[i]));
                ourFile = TestMain.main(inFilePaths[i]);
                for(String s: ourFile){
                  /*  if(!s.equals(expectedResultReader.readLine()))
                    {
                        System.out.println("Algorithm failure at i = " + i+ ", filename= " + inFilePaths[i]);
                    }*/
                }

            }
            catch(Exception e){
                System.out.println("File not found, i=" + i);
                //e.printStackTrace();
            }
        }


    }
}
