package util;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

class ItemRenderer extends BasicComboBoxRenderer {
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value != null) {
			Item item = (Item) value;
			setText(item.getDescription().toUpperCase());
		}

		if (index == -1) {
			Item item = (Item) value;
			setText("" + item.getId());
		}

		return this;
	}
}
