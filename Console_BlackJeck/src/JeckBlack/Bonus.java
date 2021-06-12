/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import java.io.PrintStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Arsak
 */
public class Bonus {

    public int SixSevenEight(ArrayList<String> karti) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);





        ArrayList<String> chisla = new ArrayList<>(Arrays.asList("6", "7", "8"));
        ArrayList<String> chisladelete = new ArrayList<>(Arrays.asList("6", "7", "8"));
        String x1 = karti.get(0);
        String x2 = karti.get(1);
        String x3 = karti.get(2);

        //777
        String s1 = Character.toString(x1.charAt(x1.length() - 1));
        String s2 = Character.toString(x2.charAt(x2.length() - 1));
        String s3 = Character.toString(x3.charAt(x3.length() - 1));

        //♥♥♥
        
        String e1 = Character.toString(x1.charAt(0));
        String e2 = Character.toString(x2.charAt(0));
        String e3 = Character.toString(x3.charAt(0));
        if ((s1.equals("7") && s2.equals("7") && s3.equals("7")) && (e1.equals(e2) && e2.equals(e3) && e3.equals(e1))) {
            out.println("У вас три 777 однакової масті ви отримуєте пятикратну суму ставки");
            return 1;

        }
        if ((s1.equals("7") && s2.equals("7") && s3.equals("7"))) {
            out.println("У вас три 777 різної масті масті ви отримуєте трьохкратну суму ставки");
            return 2;
        }
        int i = 0;
        for (String d : chisla) {
            if (d.equals(s1) || d.equals(s2) || d.equals(s3)) {
                chisladelete.remove(i);
            }
        }
        if (chisladelete.isEmpty()) {
            if ((e1.equals(e2) && e2.equals(e3) && e3.equals(e1))) {
                out.println("У вас три 7 6 8 різної масті ви отримуєте трьохкратну суму ставки");
                return 3;
            } else {
                out.println("У вас три 7 6 8 різної масті ви отримуєте трьохкратну суму ставки");
                return 4;
            }
        } else {
            return 0;
        }

    }

    public int Poradok(ArrayList<String> karti) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        int f = 1;
        for (String d : karti) {
            String e = Character.toString(d.charAt(0));
            for (int i = 1; i < karti.size(); i++) {
                String x1 = karti.get(i);
                String z = Character.toString(x1.charAt(0));
                if (e.equals(z)) {
                     f++;
                }
            }
        }      

        if(f/karti.size()+1 == karti.size()){
            return 1;
        }
        return 0;
    }
}
