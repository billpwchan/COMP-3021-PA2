package viewmodel.customNodes;

import javafx.scene.control.TextField;

/**
 * Extends the TextField component to only accept positive integers
 */
public class NumberTextField extends TextField {
    public NumberTextField(String init) {
        super(init);
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text))
            super.replaceText(start, end, text);
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text))
            super.replaceSelection(text);
    }

    private boolean validate(String text) {
        return text.matches("[0-9]*");
    }

    public int getValue() {
        return Integer.valueOf(this.getCharacters().toString());
    }
}
