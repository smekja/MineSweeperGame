import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@FunctionalInterface
public interface SimpleDocumentListener extends DocumentListener {
    void change(DocumentEvent e);

    @Override
    default void insertUpdate(DocumentEvent e) {
        change(e);
    }
    @Override
    default void removeUpdate(DocumentEvent e) {
        change(e);
    }
    @Override
    default void changedUpdate(DocumentEvent e) {
        change(e);
    }
}