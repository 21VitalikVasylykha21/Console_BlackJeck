/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.Objects;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 *
 * @author Vitalik
 */
public class Dealer extends Player {

    PrintStream out = new PrintStream(System.out, true, UTF_8);
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader im = new BufferedReader(new InputStreamReader(System.in));
    public static ArrayList<String> DealerCards = new ArrayList<>();
    ArrayList<String> CardOne = new ArrayList<>();
    ArrayList<String> CardTwo = new ArrayList<>();
    public String s1 = "";
    public int lox;

    public Dealer() throws IOException, URISyntaxException {
        s1 = t.rand(cards);
        DealerCards.add(t.rand(cards));
        DealerCards.add("Turn");
        R.WriteCard(DealerCards);
        Bonus b = new Bonus();
        lox = t.sum(DealerCards.get(0)) + t.sum(s1);
        boolean run = true;

        if (NumPlayer == 21 && lox == 21) {
            out.println(ansi().fg(YELLOW).a("Нічия! Вам повертається ваша ставка").reset());
            run = false;
        } else if (NumPlayer == 21) {
            stavka += stavka / 2;
            out.println(ansi().fg(GREEN).a("Ви виграли!").reset());
            balance += stavka;
            run = false;
        }

        while (run) {
            Info.MenuV2();
            out.print(ansi().fg(CYAN).a("---> ").reset());
            switch (in.readLine()) {
                case "1":
                    Border(NumPlayer);
                    R.WriteCard(PrintCards);

                    DealerCards.remove(1);
                    DealerCards.add(s1);

                    while (lox <= 16) {
                        DealerCards.add(t.rand(cardreal));
                        lox = 0;
                        for (int i = 0; i < DealerCards.size(); i++) {
                            lox += t.sum(DealerCards.get(i));
                        }
                    }
                    BorderD(lox);
                    R.WriteCard(DealerCards);

                    if (NumPlayer > 21) {
                        out.println(ansi().fg(RED).a("Перебір карт, ви програли").reset());

                        balance -= stavka;
                    } else if (lox > 21) {
                        out.println(ansi().fg(GREEN).a("Ви виграли!").reset());
                        balance += stavka;
                    } else if (NumPlayer > lox) {
                        out.println(ansi().fg(GREEN).a("Ви виграли!").reset());
                        balance += stavka;
                    } else if (lox == NumPlayer) {
                        out.println(ansi().fg(YELLOW).a("Нічия! Вам повертається ваша ставка").reset());
                    } else {
                        out.println(ansi().fg(RED).a("Ви програли(").reset());
                        balance -= stavka;
                    }

                    NumPlayer = 0;
                    PrintCards.clear();
                    DealerCards.clear();
                    run = false;

                    break;

                case "2":

                    String s = t.rand(cardreal);
                    PrintCards.add(s);
                    NumPlayer += t.sum(s);
                    Border(NumPlayer);
                    R.WriteCard(PrintCards);

                    if (PrintCards.size() == 3) {
                        int vot = b.SixSevenEight(PrintCards);
                        if (vot == 1) {
                            balance += stavka * 5;
                            out.println("На разі ваш баланс становить: " + Player.balance + "$");
                        }
                        if (vot == 2) {
                            balance += stavka * 3;
                            out.println("На разі ваш баланс становить: " + Player.balance + "$");
                        }
                        if (vot == 3) {
                            balance += stavka * 3;
                            out.println("На разі ваш баланс становить: " + Player.balance + "$");
                        }
                        if (vot == 4) {
                            balance += stavka * 5;
                            out.println("На разі ваш баланс становить: " + Player.balance + "$");
                        }
                    }
                    if (PrintCards.size() >= 5 && NumPlayer == 21) {
                        int bot = b.Poradok(PrintCards);
                        if (bot == 1) {
                            balance += stavka * 7;
                            out.println("Ви зібрали BlackJаck 5-ма і більше однаковими картами ви отримуєте бонус семикратний ставці");
                            out.println("На разі ваш баланс становить: " + Player.balance + "$");
                        }
                        if (bot == 0) {
                            balance += stavka;
                            out.println("Ви зібрали BlackJаck 5-ма і більше картами ви отримуєте бонус в вигляді стаки");
                            out.println("На разі ваш баланс становить: " + Player.balance + "$");
                        }
                    }
                    if (NumPlayer > 21) {
                        DealerCards.remove(1);
                        DealerCards.add(s1);
                        R.WriteCard(DealerCards);
                        out.println(ansi().fg(RED).a("Перебір карт, ви програли").reset());
                        balance -= stavka;
                        NumPlayer = 0;
                        PrintCards.clear();
                        DealerCards.clear();
                        run = false;
                    }
                    break;
                case "3":
                    if (stavka <= balance / 2) {
                        String card = t.rand(cardreal);
                        PrintCards.add(card);
                        NumPlayer += t.sum(card);
                        Border(NumPlayer);
                        R.WriteCard(PrintCards);
                        stavka *= 2;
                        out.println("Ваша ставка подвоїлася: " + stavka);
                        balance -= stavka;

                        DealerCards.remove(1);
                        DealerCards.add(s1);

                        while (lox <= 16) {
                            DealerCards.add(t.rand(cardreal));
                            lox = 0;
                            for (int i = 0; i < DealerCards.size(); i++) {
                                lox += t.sum(DealerCards.get(i));
                            }
                        }
                        BorderD(lox);
                        R.WriteCard(DealerCards);

                        if (NumPlayer > 21) {
                            out.println(ansi().fg(RED).a("Перебір карт, ви програли").reset());

                        } else if (lox > 21) {
                            out.println(ansi().fg(GREEN).a("Ви виграли!").reset());
                            balance += stavka * 2;

                        } else if (NumPlayer > lox) {
                            out.println(ansi().fg(GREEN).a("Ви виграли!").reset());
                            balance += stavka * 2;
                        } else if (lox == NumPlayer) {
                            out.println(ansi().fg(YELLOW).a("Нічия! Вам повертається ваша ставка").reset());
                        } else {
                            out.println(ansi().fg(RED).a("Ви програли(").reset());
                        }

                        NumPlayer = 0;
                        PrintCards.clear();
                        DealerCards.clear();
                        run = false;

                    } else {
                        out.println("Ви не можете поклаcти таку ставку");
                    }

                    break;
                case "4":
                    if (Objects.equals(t.sum(PrintCards.get(0)), t.sum(PrintCards.get(1)))) {
                        if (stavka <= balance / 2) {
                            Split();
                            NumPlayer = 0;
                            PrintCards.clear();
                            DealerCards.clear();
                            run = false;
                            break;
                        } else {
                            out.println("Ви не можете поклати таку ставку");
                        }
                    } else {
                        out.println("У вас не однакові карти, функція Спліт вам не доступна, прочитайте правила гри");
                        break;
                    }

                    break;
                case "5":
                    balance -= stavka / 2;
                    PrintCards.clear();
                    DealerCards.clear();
                    run = false;
                    break;
                default:
                    out.println("Некоректний ввід. Спробуйте знову");
                    break;
            }
        }
    }

    public int Numone;
    public int Numtwo;
    public int Stavkaone = stavka;
    public int Stavkatwo = stavka;

    private void Split() throws IOException {

        CardOne.add(PrintCards.get(0));
        CardTwo.add(PrintCards.get(1));
        Numone = t.sum(CardOne.get(0));
        Numtwo = t.sum(CardTwo.get(0));
        out.println("Граємо з першою картою");
        R.WriteCard(CardOne);
        Firstcard();

        DealerCards.remove(1);
        DealerCards.add(s1);

        while (lox <= 16) {
            DealerCards.add(t.rand(cardreal));
            lox = 0;
            for (int i = 0; i < DealerCards.size(); i++) {
                lox += t.sum(DealerCards.get(i));
            }
        }
        BorderD(lox);
        R.WriteCard(DealerCards);
        out.println("Кількість балів першої ставки " + Numone);
        out.println("Кількість балів другої ставки " + Numtwo);

        if (lox > 21) {
            out.println("У дилера перебір");
            balance += Stavkaone + Stavkatwo;
        } else if ((Numtwo == lox || Numone == lox) || (Numtwo == lox && Numone == lox)) {
            out.println(ansi().fg(YELLOW).a("Нічия! Вам повертається ваша ставка").reset());

        } else if (((Numtwo > lox && Numtwo <= 21) || (Numone > lox && Numone <= 21)) || ((Numtwo > lox && Numtwo <= 21) && (Numone > lox && Numone <= 21))) {
            out.println(ansi().fg(GREEN).a("Ви виграли!").reset());
            balance += Stavkaone + Stavkatwo;
        } else {
            out.println(ansi().fg(RED).a("Ви програли(").reset());
            balance -= Stavkaone + Stavkatwo;
        }
        Numone = 0;
        Numtwo = 0;
        CardOne.clear();
        CardTwo.clear();
        DealerCards.clear();

    }

    private void Firstcard() throws IOException {
        boolean go = true;
        while (go) {
            out.print("╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕\n"
                    + "╏                             Взяти ще одну карту(1) Перейти до наступної карти(2)                           ╏\n"
                    + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛\n---> ");

            switch (in.readLine()) {

                case "1":
                    String s = t.rand(cardreal);
                    CardOne.add(s);
                    Numone += t.sum(s);
                    Border(Numone);
                    R.WriteCard(CardOne);

                    if (Numone > 21) {
                        out.println("Перебір карт, ви програли, переходимо до наступної карти");

                        CardOne.clear();

                        go = false;
                        Secondcard();
                        break;
                    }

                    break;
                case "2":
                    out.print("╒════════════════════════════════════════════════════════════════════════════════════════════════════════════╕\n"
                            + "╏       Якщо ви перейдете на іншу карту то гра із цією картою закічиться, Ви погоджуєтесь?(+/-)              ╏\n"
                            + "╘════════════════════════════════════════════════════════════════════════════════════════════════════════════╛\n---> ");
                    if (in.readLine().equals("+")) {
                        go = false;
                        Secondcard();
                        break;

                    } else {
                        break;
                    }
                default:
                    out.println("Некоректний ввід. Спробуйте знову");
                    break;
            }
        }
    }

    private void Secondcard() throws IOException {
        boolean go = true;
        out.println("Граємо з другою картою");
        R.WriteCard(CardTwo);
        while (go) {
            out.print("Взяти іще одну карту(1) Відкрити карту дилера(2)\n---> ");

            switch (in.readLine()) {
                case "1":
                    String s = t.rand(cardreal);
                    CardTwo.add(s);
                    Numtwo += t.sum(s);
                    Border(Numtwo);
                    R.WriteCard(CardTwo);

                    if (Numtwo > 21) {
                        out.println(ansi().fg(RED).a("Перебір карт, ви програли").reset());
                        go = false;
                        break;
                    }

                    break;
                case "2":
                    go = false;
                    break;
                default:
                    out.println("Некоректний ввід. Спробуйте знову");
                    break;
            }
        }
    }

    public static void Border(int rach) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        if (rach >= 10) {
            out.println("╭━━━━━━━━━━━━━━━━━━━━━━━━╮\n"
                    + "│Ваша кількість балів: " + rach + "│\n"
                    + "╰━━━━━━━━━━━━━━━━━━━━━━━━╯ ");
        } else {
            out.println("╭━━━━━━━━━━━━━━━━━━━━━━━╮\n"
                    + "│Ваша кількість балів: " + rach + "│\n"
                    + "╰━━━━━━━━━━━━━━━━━━━━━━━╯ ");
        }

    }

    public static void BorderD(int rach) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        if (rach >= 10) {
            out.println("╭━━━━━━━━━━━━━━━━━━━━━━━━━━━━╮\n"
                    + "│Кількість балів у Дилера: " + rach + "│\n"
                    + "╰━━━━━━━━━━━━━━━━━━━━━━━━━━━━╯");
        } else {
            out.println("╭━━━━━━━━━━━━━━━━━━━━━━━━━━━╮\n"
                    + "│Кількість балів у Дилера: " + rach + "│\n"
                    + "╰━━━━━━━━━━━━━━━━━━━━━━━━━━━╯");
        }

    }
}
