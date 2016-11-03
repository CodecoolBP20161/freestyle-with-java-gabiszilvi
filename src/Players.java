/**
 * Created by lorszil on 02/11/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Players {

    public int input() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        try {
            input = Integer.parseInt(br.readLine());
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return input;
    }

    public static void main(String args[]){



    }

}
