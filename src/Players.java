/**
 * Created by lorszil on 02/11/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class players {

    public static String input() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String valami = new String();
        try {
            valami = br.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }


        /*try{
            int i = Integer.parseInt(br.readLine());
        }
        catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }*/
        return valami;
    }

    public static void main(String args[]){
        String input = input();
        System.out.println(input);

    }

}
