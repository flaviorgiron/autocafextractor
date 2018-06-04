package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class GeraLog {

    private PrintWriter out;

    public GeraLog() {
        try {
            out = new PrintWriter(new FileWriter("Log.txt", true));
        } catch (IOException e) {
            /*
			 * Se der algum erro com o arquivo de log, imprime a stacktrace no console, mas a 
			 * aplicação continua funcionando
             */
            e.printStackTrace();
        }
    }

    public void gravaErro(Throwable erro) {
        out.print("Data do erro: ");
        out.println(new Date());
        out.print("Mensagem de erro: ");
        out.println(erro);
        out.print("Stacktrace: ");
        erro.printStackTrace(out);
    }

    public void gravaMensagensLog(Throwable msg) {
        out.print("Data da mensagem: ");
        out.println(new Date());
        out.print("Mensagem: ");
        out.println(msg);
    }

    public void close() {
        if (out != null) {
            out.flush();
            out.close();
        }
    }
}
