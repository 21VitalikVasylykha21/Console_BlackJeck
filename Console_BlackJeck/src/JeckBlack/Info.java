/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import static JeckBlack.Main.out;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;
import org.fusesource.jansi.AnsiConsole;

/**
 *
 * @author Lerka
 */
public class Info {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void info() throws URISyntaxException, UnsupportedEncodingException, IOException {

        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream("Info\\Info.txt"), "UTF-8"));
            while (in.ready()) {
                String s = "        " + in.readLine();
                AnsiConsole.systemInstall();
                out.println(ansi().fg(BLUE).a(s).reset());
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void Menu() throws URISyntaxException, UnsupportedEncodingException, IOException {

        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream("Info\\Menu.txt"), "UTF-8"));
            while (in.ready()) {
                String s = "\t" + "\t" + "\t" + "   "+ in.readLine();

                AnsiConsole.systemInstall();
                out.println(ansi().fg(RED).a(s).reset());
                out.print(ansi().bgDefault().fgDefault());
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void MenuV2() throws URISyntaxException, UnsupportedEncodingException, IOException {

        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream("Info\\Menu2.txt"), "UTF-8"));
            while (in.ready()) {
                String s = in.readLine();

                AnsiConsole.systemInstall();
                out.println(ansi().fg(CYAN).a(s).reset());
                out.print(ansi().bgDefault().fgDefault());
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void Animation() throws InterruptedException {
        ArrayList<String> Cadr = new ArrayList<>(Arrays.asList("Turn1", "Turn2", "Turn3", "Turn4", "Turn5", "Turn6", "Turn7", "Turn8", "Turn9", "Turn10", "Turn11", "Turn12"));
        ArrayList<String> Zaholovok = new ArrayList<>(Arrays.asList("Zaholovok1", "Zaholovok2", "Zaholovok3", "Zaholovok4", "Zaholovok5", "Zaholovok6", "Zaholovok7", "Zaholovok8", "Zaholovok9", "Zaholovok10"));
        for (int i = 0; i < 1; i++) {
            for (String c : Cadr) {
                try {
                    in = new BufferedReader(new InputStreamReader(new FileInputStream("Animatoin\\" + c + ".txt"), "UTF-8"));
                    while (in.ready()) {
                        String s = in.readLine();
                        AnsiConsole.systemInstall();
                        out.println(ansi().fg(CYAN).a(s).reset());
                        out.print(ansi().bgDefault().fgDefault());
                    }
                    in.close();
                    Thread.sleep(200);
                    clrscr();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream("Animatoin\\" + Cadr.get(i) + ".txt"), "UTF-8"));
                while (in.ready()) {
                    String s = in.readLine();
                    AnsiConsole.systemInstall();
                    out.println(ansi().fg(RED).a(s).reset());
                    out.print(ansi().bgDefault().fgDefault());
                }
                in.close();
                Thread.sleep(200);
                clrscr();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        for (String c : Zaholovok) {
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream("Animatoin\\" + c + ".txt"), "UTF-8"));
                clrscr();
                while (in.ready()) {
                    String s = in.readLine();
                    AnsiConsole.systemInstall();
                    out.println(ansi().fg(CYAN).a(s).reset());
                    out.print(ansi().bgDefault().fgDefault());
                }
                in.close();
                Thread.sleep(200);

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
}
