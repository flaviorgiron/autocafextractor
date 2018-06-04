package ctr;

import bd.ConexaoFirebird;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import util.GeraLog;
import util.JConfirmMessage;

public final class GlobalParameter {

    private static GlobalParameter instance = null;
    private static Cbc cbc;
    private static Fila filaCompanyTec;
    private static String caminhoAplicacao;
    private static Connection conn;

    public static GlobalParameter getInstance() {
        try {
            if (instance == null) {
                instance = new GlobalParameter();
                cbc = new Cbc();
                filaCompanyTec = new Fila();
                conn = new ConexaoFirebird().getConnection();
            }
            return instance;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static String getCaminhoAplicacao() {
        return caminhoAplicacao;
    }

    public static Integer getDiaSemana() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public static void setCaminhoAplicacao(String aCaminhoAplicacao) {
        caminhoAplicacao = aCaminhoAplicacao;
    }

    private GlobalParameter() {
    }

    public Cbc getCbc() {
        return cbc;
    }

    public String getDataSistema() {
        try {
            return DateFormat.getDateInstance().format(new Date());
        } catch (Exception e) {
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public Fila getFilaCompanyTec() {
        return filaCompanyTec;
    }
}
