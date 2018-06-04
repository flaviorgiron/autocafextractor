package bd;

import ctr.GlobalParameter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.ListaExportados;
import util.GeraLog;
import util.JConfirmMessage;

public class AbastecimentoDB {

    public static ArrayList<String> buscaAbastecimentos() {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<String> strings = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;

            sql = "SELECT STRING FROM ABASTECIMENTOS WHERE EXPORTADO = 'N'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String s = rs.getString("STRING");
                strings.add(s);
            }
            return strings;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static ArrayList<String> buscaPorDataExportados(Date dtGerado) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<String> strings = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;
            DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            sql = "SELECT STRING FROM ABASTECIMENTOS WHERE EXPORTADO = 'S' AND DTEXPORTACAO = '" + f.format(dtGerado) + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String s = rs.getString("STRING");
                strings.add(s);
            }
            return strings;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static ArrayList<ListaExportados> buscaExportados() {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            ArrayList<ListaExportados> strings = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;
            sql = "SELECT DISTINCT(DTEXPORTACAO) AS DTEXPORTACAO,COUNT (DTEXPORTACAO) AS QTDEEXPORTADA FROM ABASTECIMENTOS WHERE EXPORTADO = 'S' GROUP BY DTEXPORTACAO ORDER BY DTEXPORTACAO DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ListaExportados s = new ListaExportados(f.format(rs.getTimestamp("DTEXPORTACAO")), rs.getInt("QTDEEXPORTADA"));
                strings.add(s);
            }
            return strings;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean insertOrUpdate(String string) {
        Connection conn = GlobalParameter.getConn();
        try {
            DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO ABASTECIMENTOS (STRING, EXPORTADO, DTIMPORTACAO, DTEXPORTACAO) VALUES ('"
                    + string
                    + "','N','"
                    + f.format(new Date()) + "'," + null + ")";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("String já importada", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean registraExportacao(String string, Date data) {
        Connection conn = GlobalParameter.getConn();
        try {
            DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            Statement stmt = conn.createStatement();
            String sql = "UPDATE ABASTECIMENTOS SET DTEXPORTACAO='" + f.format(data) + "', EXPORTADO = 'S' WHERE STRING = '" + string + "'";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("String já importada", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

}
