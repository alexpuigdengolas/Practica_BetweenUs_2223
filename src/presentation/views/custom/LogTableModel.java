package presentation.views.custom;

import business.Log;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class LogTableModel extends AbstractTableModel {

    private final String[] header = {"CREWMATE", "ROOM", "INSTANT"};
    private final LinkedList<Log> data;

    public LogTableModel(LinkedList<Log> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Log log = data.get(rowIndex);
        if (log == null) {
            return null;
        }
        return switch (columnIndex) {
            case 0 -> log.getName();
            case 1 -> log.getRoom();
            case 2 -> log.getInstant();
            default -> null;
        };
    }
}
