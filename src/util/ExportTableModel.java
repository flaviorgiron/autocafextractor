package util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.ListaExportados;

public class ExportTableModel extends AbstractTableModel {

    private static List<ListaExportados> linhas;
    private static String[] cColunas = new String[]{"Data/Hora Exportacão", "Qtde Export.", ""};

    public ExportTableModel() {
        linhas = new ArrayList<>();
    }

    public ExportTableModel(List<ListaExportados> listaExport) {
        linhas = new ArrayList<>(listaExport);
    }

    @Override
    public int getColumnCount() {
        return cColunas.length;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return cColunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return String.class; // data hora
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("ExportTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ListaExportados s = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return s.getDataHora();
            case 1:
                return s.getQtdeExportada().toString();
            case 2:
                return "";
            default:
                throw new IndexOutOfBoundsException("ExportTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public ListaExportados getString(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaString(List<ListaExportados> strings) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(strings);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}
