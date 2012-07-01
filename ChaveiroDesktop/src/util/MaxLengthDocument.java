package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLengthDocument extends PlainDocument {
	private int max;

	public MaxLengthDocument(int maxLength) {
		max = maxLength;
	}

	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		if (getLength() + str.length() > max)
			java.awt.Toolkit.getDefaultToolkit().beep();
		else
			super.insertString(offset, str, a);
	}

}
