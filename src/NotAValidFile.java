import javax.swing.*;

public class NotAValidFile extends Exception {

    public NotAValidFile() {
        JOptionPane.showMessageDialog(null, "Het bestand is corrupt");
    }
}
