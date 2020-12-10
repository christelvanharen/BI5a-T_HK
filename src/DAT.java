/**
 * @author: Christel van Haren
 * BI5a-T HK op 10 december 2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class DAT {
    private JPanel panel;
    private JTextField bestand_invoeren;
    private JButton bladerButton;
    private JTextField zoekwoord_meegeven;
    private JButton analyseButton;
    private JTextArea textArea;
    private JPanel panel2;
    private JFileChooser fileChooser;

    public DAT() {
        /**
         * Het bestand wordt ingelezen en de analyseerknop voor het
         * zoekwoord wordt gemaakt
         */
        bladerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selectedFile; // bestand inlezen
                fileChooser = new JFileChooser();
                int reply = fileChooser.showOpenDialog(null);
                if (reply == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    bestand_invoeren.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        analyseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader b =
                            new BufferedReader(new FileReader(bestand_invoeren.getText()));
                    FileReader f = new FileReader(bestand_invoeren.getText());
                    String line;
                    String input= (zoekwoord_meegeven.getText().toLowerCase());
                    int lines = 0;
                    int tellen=0;
                    // tellen in hoeveel regels het zoekwoord voorkomt
                    while ((line = b.readLine()) != null) {
                        if ((line.toLowerCase()).contains(input)) {
                            tellen++;
                        } else {
                            lines++;
                        }
                    }
                    // totaal aantal regels
                    float totaal = tellen + lines;
                    System.out.println(totaal);

                    b.close();
                    f.close();

                    // percentage berekening
                    float percentage = ((float) tellen / totaal * 100);

//                    System.out.println("Dit bestand heeft " + lines +
//                            " regels.");
                    textArea.setText("Dit bestand heeft " + totaal +
                            " regels." + "\n" + "Het ingevoerde " +
                            "zoekwoord is: " + zoekwoord_meegeven.getText() +
                            "\n" + "Het woord komt voor in " + tellen + " regels." + "\n" + "Dat is " + percentage + "% van alle regels.");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
//                } catch (NotAValidFile navf) { // deze werkt nog niet
//                    // helemaal
//                    JOptionPane.showMessageDialog("Het bestand is corrupt.");
                }
                }
            });
    }



    public static void main(String[] args) throws ReflectiveOperationException {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Er ging iets fout.");
        }

        JFrame frame = new JFrame("DAT | Disease Analyse Tool");
        frame.setContentPane(new DAT().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
