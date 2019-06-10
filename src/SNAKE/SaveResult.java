package SNAKE;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * Klasa  - wyswietlenie wynikow gry
 */

public class SaveResult {
    public JLabel[] getBestResult() {
        return bestResult;
    }

    private JLabel[] bestResult; //najlepsze wyniki
    private ArrayList<Integer> result;
    private int[] table_result;



    private void SaveResult() {

        try {

            Scanner odczyt = new Scanner(Paths.get("result.txt"));
            result = new ArrayList();
            for (int i = 0; i < 10; i++) {
                result.add(0);
            }
            while (odczyt.hasNext()) {
                int res = odczyt.nextInt();
                result.add(res);
                Collections.sort(result);
                Collections.reverse(result);
            }

        } catch (
                IOException e1) {
            e1.printStackTrace();
            result = new ArrayList();
            for (int i = 0; i < 10; i++) {
                result.add(0);
            }
        }
        table_result = new int[10];
        for (int i = 0; i < table_result.length; i++) {
            table_result[i] = result.get(i);
        }
        for (int el : table_result) {
            System.out.println(el);
        }
        bestResult = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            //napisy
            bestResult[i] = new JLabel(String.valueOf(table_result[i]));
            bestResult[i].setFont(new Font("czcionka", Font.BOLD, 22));
            bestResult[i].setForeground(Color.black);
        }
        //ustawienie polozenia na wezu wynikow
        bestResult[0].setBounds(163, 158, 500, 20);
        bestResult[1].setBounds(273, 158, 500, 20);
        bestResult[2].setBounds(378, 158, 500, 20);
        bestResult[3].setBounds(483, 158, 500, 20);
        bestResult[4].setBounds(593, 158, 500, 20);
        bestResult[5].setBounds(593, 268, 500, 20);
        bestResult[6].setBounds(593, 368, 500, 20);
        bestResult[7].setBounds(593, 478, 500, 20);
        bestResult[8].setBounds(703, 478, 500, 20);
        bestResult[9].setBounds(808, 478, 500, 20);
    }

}

