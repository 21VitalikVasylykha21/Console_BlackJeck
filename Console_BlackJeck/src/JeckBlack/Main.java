/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.net.URISyntaxException;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 *
 * @author Vitalik
 */
public class Main {

    static Sound S;
    static PrintStream out = new PrintStream(System.out, true, UTF_8);

    public static void main(String[] args) throws IOException, URISyntaxException {
        try {
            Info.Animation();
        } catch (InterruptedException e) {
            out.print("Сталася помилка, перевірте введені данні");
        }

        Menu();
    }

    public static void Menu() throws IOException, URISyntaxException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //музика
        File Sound = new File("Throttle - Hit The Road Jack (Official Music Video).wav");
        S = new Sound();
        S.Sound(Sound);

        out.println(ansi().fg(BLACK).a("/").reset());
        OUTER:
        while (true) {

            Info.Menu();
            out.print(ansi().fg(RED).a("\t" + "\t" + "\t" + "\t" + "\t" + "---> ").reset());
            String input = in.readLine();
            switch (input) {
                case "1":
                    Game.Run();
                    break;
                case "2":
                    Info.info();
                    break;
                case "3":
                    S.play(true);
                    out.print(ansi().fg(YELLOW).a("╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕\n"
                            + "╏                                  Змінюємо гучність музики? Да(+) чи Ні(-)                                  ╏\n"
                            + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛" + "\n" + "--->").reset());
                    String musik = in.readLine();
                    if (musik.equals("Да") | musik.equals("да") | musik.equals("+")) {
                        out.print(ansi().fg(YELLOW).a("╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕\n"
                                + "╏                 Тепер гучність на рівні: " + S.getVolume() + "; Уведіть гучність в форматі 0.5:                        ╏\n"
                                + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛"+ "\n" + "--->").reset());
                        try {
                            float Volume = (float) Double.parseDouble(in.readLine());
                            S.setVolume(Volume);
                        } catch (Exception e) {
                            out.println("Сталася помилка: " + e);
                        }
                        out.println(ansi().fg(YELLOW).a("╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕\n"
                                + "╏                                       Налаштування звуку закінчені!                                        ╏\n"
                                + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛").reset());
                    } else if (musik.equals("Ні") | musik.equals("ні") | musik.equals("-")) {
                        out.println(ansi().fg(YELLOW).a("╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕\n"
                                + "╏                                       Налаштування звуку закінчені!                                        ╏\n"
                                + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛").reset());
                    } else {
                        out.println(ansi().fg(RED).a("Некоректний ввід!").reset());
                    }
                    break;
                case "4":
                    break OUTER;
                default:
                    out.println(ansi().fg(RED).a("Некоректний ввід").reset());
                    break;
            }
        }
    }
}
