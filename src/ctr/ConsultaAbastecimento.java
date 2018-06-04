package ctr;

import bd.AbastecimentoDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import util.GeraLog;
import util.JConfirmMessage;
import view.movimentacao.SDIPrincipalGUI;

public class ConsultaAbastecimento implements Runnable {

    Cbc cbc = GlobalParameter.getInstance().getCbc();
    String leitura = "";

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                if (cbc.isConectado()) {
                    Thread.sleep(1000);
                    leitura = cbc.consultaAbastecimento();

                    if (!leitura.equals("")) {
                        System.out.println(leitura);
                        AbastecimentoDB.insertOrUpdate(leitura);
                        SDIPrincipalGUI.jLabel1.setText("Recebendo Dados --> " + leitura.substring(17, 23));
                        cbc.avancaRegistro();
                        //Thread.sleep(500);
                    } else {
                        i = i + 1;
                        //System.out.println("" + i);
                        if (i > 5) {
                            //JConfirmMessage.showMessageDialog("Fim da Sincronização.", "");
                            gerarArquivo();
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }

    private void gerarArquivo() {
        try {
            File f = new File("C:/EXPORT_AUTOCAF");
            if (!f.exists()) {
                f.mkdirs();
            }
            File[] sun = f.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
            Date dataAtual = new Date();

            String conteudo = preparaConteudo();
            String nomeArquivo = sdf.format(dataAtual);

            if (gravarTXT(f.getAbsolutePath() + "\\" + nomeArquivo + ".txt", conteudo)) {
                SDIPrincipalGUI.jLabel1.setText("Gerando arquivo de exportação.......");
                FileReader arq = new FileReader(f.getAbsolutePath() + "\\" + nomeArquivo + ".txt");
                BufferedReader lerArq = new BufferedReader(arq);

                String linha = lerArq.readLine(); // lê a primeira linha

                while (linha != null) {
                    Thread.sleep(200);
                    SDIPrincipalGUI.jLabel1.setText("Registrando exportações......");
                    if (!linha.equals("")) {
                        AbastecimentoDB.registraExportacao(linha, dataAtual);
                    }

                    linha = lerArq.readLine(); // lê da segunda até a última linha
                }

                arq.close();

                SDIPrincipalGUI.jButton1.setEnabled(true);
                SDIPrincipalGUI.jLabel1.setText("Exportação realizada com sucesso.");
                SDIPrincipalGUI.carregaGrid();
                Runtime.getRuntime().exec("explorer " + f.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    private String preparaConteudo() {
        try {
            String conteudo = "";
            Iterator iterator = AbastecimentoDB.buscaAbastecimentos().iterator();
            int cont = 0;
            while (iterator.hasNext()) {
                String a = (String) iterator.next();
                if (!a.trim().equals("")) {
                    if (cont > 0) {
                        conteudo += System.getProperty("line.separator") + a;
                    } else {
                        conteudo += a;
                    }
                }
                cont++;
            }
            return conteudo;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return "";
        }
    }

    public boolean gravarTXT(String caminhoCompleto, String texto) {
        try {
            FileWriter x = new FileWriter(caminhoCompleto, false);
            //texto += "\n\r"; // criando nova linha e recuo no arquivo              
            x.write(texto); // armazena o texto no objeto x, que aponta para o arquivo             
            x.close(); // cria o arquivo   
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
