/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;
/**
 *
 * @author Vitalik
 */
public class PrintCard {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private PrintStream out = new PrintStream(System.out, true, UTF_8);

    public void WriteCard(ArrayList<String> card) throws UnsupportedEncodingException, IOException {
      
        String print[] = new String[]{"", "", "", "", "", "", "", "", "", ""};
        String realprint[] = new String[]{"", "", "", "", "", "", "", "", "", ""};
        //цикл що зчитує карти із файлу
        for (String s : card) {
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream("Cards\\" + s + ".txt"), "UTF-8"));
                for (int i = 0; i < 10; i++) {
                    if (i == 0) {
                        
                        print[i] += in.readLine() + "    ";
                    } else {
                        print[i] += in.readLine() + "   ";
                    }
                }
                in.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        int size = card.size();
        int space = 50;
        for(int i = 0; i < size; i++){
           space -=6;
        }
        for(int i = 0; i < 10; i++){
            for(int c = 0; c < space; c++){
            realprint[i] += " ";
            }
           realprint[i] += print[i];
        }
        //цикл що виводить їх в командний рядок.
        for (int i = 0; i < 10; i++) {
            AnsiConsole.systemInstall();
          out.print(ansi().fg(RED).a(realprint[i] + "\n").reset());
        }
    }
  
}
