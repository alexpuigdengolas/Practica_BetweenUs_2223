package presentation.views;

import business.Log;
import presentation.views.custom.LogTableModel;

import javax.swing.*;
import java.util.LinkedList;

public class LogsView extends JFrame {
    private LinkedList<Log> logs;


    public LogsView(LinkedList<Log> logs) {
        this.logs = logs;

        setTitle("Logs"); // titol
        setSize(700, 400); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // cerrar con la x
        setLayout(null);

        LogTableModel model = new LogTableModel(logs);
        JTable logsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(logsTable);

        setContentPane(scrollPane);
        setVisible(true);
    }
}