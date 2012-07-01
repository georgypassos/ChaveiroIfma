// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 01/07/2012 12:57:36
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MyModelComboBox.java

package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

// Referenced classes of package myjava:
//            SelectItem

public class MyModelComboBox extends DefaultComboBoxModel {

	public MyModelComboBox() {
		arrayList = new ArrayList();
	}

	public MyModelComboBox(SelectItem selectItems[]) {
		this();
		populate(selectItems);
	}

	public MyModelComboBox(List list, String key, String value) {
		this();
		// populate(list, key, value);
	}

	public void addItem(Object key, Object value) {
		SelectItem selectItem = new SelectItem(key, value);
		arrayList.add(selectItem);
		super.addElement(value);
	}

	public void addItem(int index, Object key, Object value) {
		SelectItem selectItem = new SelectItem(key, value);
		arrayList.add(index, selectItem);
		super.removeAllElements();
		populate(arrayList);
	}

	public Object getItem(Object key) {
		for (Iterator iterator = arrayList.iterator(); iterator.hasNext();) {
			SelectItem item = (SelectItem) iterator.next();
			if (item.getKey().equals(key))
				return item.getValue();
		}

		return null;
	}

	public Object getItemAt(int index) {
		return getItemAt(index);
	}

	public Object getKeyAt(int index) {
		return ((SelectItem) arrayList.get(index)).getKey();
	}

	public int getSelectedIndex() {
		Object sObject = super.getSelectedItem();
		int i = 0;
		for (int c = super.getSize(); i < c; i++) {
			Object obj = super.getElementAt(i);
			if (obj != null && obj.equals(sObject))
				return i;
		}

		return -1;
	}

	public Object getSelectedValue() {
		return super.getSelectedItem();
	}

	public Object getSelectedKey() {
		return ((SelectItem) arrayList.get(getSelectedIndex())).getKey();
	}

	public void setSelectedKey(Object key) {
		if (key != null) {
			Object object = getItem(key);
			if (object != null)
				setSelectedItem(object);
		}
	}

	public void setSelectedItem(Object object) {
		super.setSelectedItem(object);
	}

	public int getIndexByKey(Object key) {
		int index = -1;
		for (Iterator iterator = arrayList.iterator(); iterator.hasNext();) {
			SelectItem select = (SelectItem) iterator.next();
			if (select.getKey().equals(key)) {
				index++;
				break;
			}
		}

		return index;
	}

	public void removeItem(Object key) {
		int index = getIndexByKey(key);
		arrayList.remove(index);
		super.removeElementAt(index);
	}

	public void removeItemAt(int index) {
		arrayList.remove(index);
		super.removeElementAt(index);
	}

	public void removeAllItems() {
		arrayList = new ArrayList();
		super.removeAllElements();
	}

	/**
	 * @deprecated Method removeAllElements is deprecated
	 */

	public void removeAllElements() {
		super.removeAllElements();
	}

	public void populate(SelectItem comboboxitemssexo[]) {
		SelectItem aselectitem[];
		int j = (aselectitem = comboboxitemssexo).length;
		for (int i = 0; i < j; i++) {
			SelectItem selectItem = aselectitem[i];
			addItem(selectItem.getKey(), selectItem.getValue());
		}

	}

	public void populate(ArrayList list) {
		SelectItem selectItem;
		for (Iterator iterator = list.iterator(); iterator.hasNext(); super.addElement(selectItem.getValue()))
			selectItem = (SelectItem) iterator.next();

	}

	// private void populate(List list, String keyStr, String valueStr)
	// {
	// try
	// {
	// Object key;
	// Object value;
	// for(Iterator iterator = list.iterator(); iterator.hasNext(); addItem(key, value))
	// {
	// Object object = iterator.next();
	// key = Ognl.getValue(keyStr, object);
	// value = Ognl.getValue(valueStr, object);
	// }
	//
	// }
	// catch(Exception e)
	// {
	// e.printStackTrace();
	// }
	// }

	private static final long serialVersionUID = 1L;
	public ArrayList arrayList;
}
