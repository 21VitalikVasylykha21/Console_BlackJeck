/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vitalik
 */
public class Table {
    public String rand(ArrayList<String> cards){
        int result;
        Random random = new Random();
        result = random.nextInt(51);
        String x = cards.get(result);
        return x;
    }
    
    public Integer sum(String x){
       String s1 =  Character.toString(x.charAt(x.length()-1));
       String s2 =  Character.toString(x.charAt(x.length()-2));
       String sum = "";
       
       if(!" ".equals(s2)){
           sum = sum + s2 + s1;
       }
       else{
           sum = s1;

       }
       int foo = Integer.parseInt(sum);
        return foo;
    }
}
