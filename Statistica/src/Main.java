
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws Exception {



        var s1 = new DuePassate(Arrays.asList(49.80401,49.80400,49.80309,49.80399));

        var s2 = new UnaPassata(Arrays.asList(49.80401,49.80400,49.80309,49.80399));

        var s3 = new Induttiva(Arrays.asList(49.80401,49.80400,49.80309,49.80399));
        
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        
    }
}
