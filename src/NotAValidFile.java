/**
 * Christel van Haren
 * Custom Exception (poging tot)
 * BI5a-T HK op 10 december 2020
 */

import javax.swing.*;

public class NotAValidFile extends Exception {

    public NotAValidFile() {
        JOptionPane.showMessageDialog(null, "Het bestand is corrupt");
    }
}
