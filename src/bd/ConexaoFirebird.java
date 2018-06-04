package bd;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class ConexaoFirebird {

    public Connection getConnection() {
        try {
            File f = new File(".lock");
            f.createNewFile();
            //GlobalParameter.getInstance();
            GlobalParameter.setCaminhoAplicacao(f.getAbsolutePath().replace(".lock", ""));
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            //lc_ctype=WIN1252  --> Necesario para a codificação da acentuação no realtorios
            //defaultResultSetHoldable=True  --> Necessario para relatorios com subrelatorios(receita) --> problema encontrado com o jaybird
            
            //String driver = ("jdbc:firebirdsql:" + "localhost" + "/" + "3050" + ":" + "D:/CAFEX.FDB" + "?defaultResultSetHoldable=True;lc_ctype=WIN1252;allowMultiQueries=true");
            String driver = ("jdbc:firebirdsql:" + "localhost" + "/" + "3050" + ":" + GlobalParameter.getCaminhoAplicacao().replace("\\", "/") + "CAFEX.FDB" + "?defaultResultSetHoldable=True;lc_ctype=WIN1252;allowMultiQueries=true");
            //Connection con = DriverManager.getConnection(driver, "SYSDBA", "masterkey");
            Connection con = DriverManager.getConnection(driver, "SYSDBA", "masterkey");
            return con;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean executaSQL(String sql) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String array[] = sql.split(";", -1);

            for (String query : array) {
                stmt.execute(query);
            }
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }
}
