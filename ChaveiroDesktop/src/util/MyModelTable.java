package util;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

public class MyModelTable extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Object[]> data;
	private Vector<Object> keys;
	private String[] columns;
	private Class[] type;
	private Boolean[] columnEditable;

	private JTable table;

	public MyModelTable(String... columns) {

		data = new Vector<Object[]>();
		keys = new Vector<Object>();
		this.columns = columns;
		type = new Class[columns.length];
		columnEditable = new Boolean[columns.length];

		for (int i = 0; i < type.length; i++) {

			type[i] = String.class;
			columnEditable[i] = false;

		}

	}

	public MyModelTable(int rows, int columns) {

		data = new Vector<Object[]>();
		keys = new Vector<Object>();
		type = new Class[columns];
		columnEditable = new Boolean[columns];
		for (int i = 0; i < type.length; i++) {

			type[i] = String.class;
			columnEditable[i] = false;

		}

		this.columns = new String[columns];
		for (int i = 0; i < rows; i++) {

			String[] rowsData = new String[columns];
			for (int j = 0; j < rowsData.length; j++) {
				rowsData[j] = "";
			}

			addRow(rowsData);
		}

	}

	public void setTypeColumn(int colmns, Class clazz) {

		type[colmns] = clazz;

	}

	public void setColumnEditable(int column, boolean editable) {

		columnEditable[column] = editable;

	}

	public void setColumnsEditable(boolean editable, int... column) {

		for (int i : column) {
			columnEditable[i] = editable;
		}

	}

	public int getRowCount() {

		return data.size();
	}

	public int getColumnCount() {

		return columns.length;
	}

	public Object getValueAt(int row, int col) {

		return data.get(row)[col];

	}

	public String getColumnName(int columnIndex) {

		return columns[columnIndex];
	}

	public boolean isCellEditable(int row, int col) {

		return columnEditable[col];
	}

	public Class getColumnClass(int column) {

		return type[column];
	}

	public void setValueAt(Object value, int row, int col) {

		if (type[col] == String.class) {
			data.get(row)[col] = value;
		} else if (type[col] == Boolean.class) {
			data.get(row)[col] = (Boolean) value;
		}
	}

	public void removeRow(int i) {
		data.remove(i);

	}

	public void addRow(Object... objects) {

		data.add(objects);

	}
	public void addRow() {

		String string[]  = new String[columns.length];
		data.add(string);

	}

	public void addRowAndKey(Object key, Object... objects) {

		data.add(objects);
		keys.add(key);

	}

	public void teste() {

	}

	public Object getKey(int row) {

		return keys.get(row);

	}

	public Object getKeySelected() {

		int row = getSelectedRow();
		if (row != -1) {
			return getKey(row);
		}

		return null;

	}

	public void clear() {
		keys.clear();
		data.clear();

	}

	public void setSelectionMode(int mode) {

		table.setSelectionMode(mode);
	}

	public void setSelectionMode(ListSelectionModel mode) {

		table.setSelectionModel(mode);
	}

	public void setCellSelectionEnabled(boolean enable) {

		table.setCellSelectionEnabled(enable);
	}

	public void setTableHeader(JTableHeader enable) {

		table.setTableHeader(enable);
	}

	public void setRowHeight(int rowHeight) {

		table.setRowHeight(rowHeight);
	}

	public void setRowHeight(int row, int rowHeight) {

		table.setRowHeight(row, rowHeight);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {

		table.setModel(this);

		this.table = table;
	}

	/**
	 * 
	 * 
	 */

	public void updateUI() {

		table.updateUI();
	}

	public int getSelectedRow() {

		return table.getSelectedRow();
	}

	/**
	 * 
	 * 
	 */

}
