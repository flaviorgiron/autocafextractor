package model;

public class ListaExportados {

    private String dataHora;
    private Integer qtdeExportada;

    public String getDataHora() {
        return dataHora;
    }

    public ListaExportados() {
    }

    public ListaExportados(String dataHora, Integer qtdeExportada) {
        this.dataHora = dataHora;
        this.qtdeExportada = qtdeExportada;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getQtdeExportada() {
        return qtdeExportada;
    }

    public void setQtdeExportada(Integer qtdeExportada) {
        this.qtdeExportada = qtdeExportada;
    }
}
