/**
 * @author: Christel van Haren
 * BI5a-T HK op 10 december 2020
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

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
        bladerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selectedFile;
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
                    Scanner reader = new Scanner(b);
                    String [] woord=null;
                    FileReader f = new FileReader(bestand_invoeren.getText());
                    String s;
                    String input= zoekwoord_meegeven.getText();
                    int lines = 0;
                    int tellen=0;

                    while (reader.hasNextLine()) {
                        b.readLine(); //aantal regels van het
                        // bestand bepalen
                        lines++;
                    }

//                    while ((s=b.readLine())!=null) {
//                        woord=s.split("\\s");
//                        for (String woorden : woord) {
//                            if (woorden.equals(input)) {
//                                tellen++;
//                            }
//                        }
//                    }

                    if (tellen!=0){
                        System.out.println("Het woord zit" + tellen + "keer in het bestand");
                    }
                    else {
                        System.out.println("Het woord zit niet in het" +
                                " bestand.");
                    }
                    b.close();
                    f.close();

                    int percentage = ((tellen / lines) * 100);

//                    System.out.println("Dit bestand heeft " + lines +
//                            " regels.");
                    textArea.setText("Dit bestand heeft " + lines +
                            " regels." + "\n" + "Het ingevoerde " +
                            "zoekwoord is: " + zoekwoord_meegeven.getText() +
                            "\n" + "Het woord komt voor in " + tellen + " regels." + "\n" + "Dat is " + percentage + "% van alle regels.");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
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
        frame.pack();
        frame.setVisible(true);
    }
}
