package view.movimentacao;

import bd.AbastecimentoDB;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import trayicon.TyBallon;
import util.*;
import ctr.*;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumnModel;

public class SDIPrincipalGUI extends javax.swing.JFrame {

    private Cbc cbc = null;
    public Thread realTimeConsultasCompanyTec = null;
    public Thread analisaFilaCompanyTec = null;
    public static ExportTableModel exportTableModel;

    public SDIPrincipalGUI() throws AWTException, IOException {
        try {
            initComponents();

            ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
            setIconImage(icon.getImage());

            carregaBallon();

            jTable1.setModel(new ExportTableModel());

            carregaGrid();
            jLabel1.setText("Clique em iniciar para realizar a extração dos dados.");
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Extrator de Dados");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                minimizarJanela(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo_menu.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(241, 240, 240));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jTextField1.setText("192.168.0.91");

        jTextField2.setText("2001");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Porta:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Endereço:");

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void minimizarJanela(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_minimizarJanela
        setVisible(false);
    }//GEN-LAST:event_minimizarJanela

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        finalizarSistema();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setEnabled(false);
        SDIPrincipalGUI.jLabel1.setText("Aguarde. Importação iniciada");
        iniciaConexaoCompanyTec();
    }//GEN-LAST:event_jButton1ActionPerformed

    public String hexaToTime(String timeHex) {
        try {
            long elapsedTime = Long.parseLong(timeHex, 16);
            String format = String.format("%%0%dd", 2);
            String seconds = String.format(format, elapsedTime % 60);
            String minutes = String.format(format, (elapsedTime % 3600) / 60);
            String hours = String.format(format, elapsedTime / 3600);
            //DateFormat formato = new SimpleDateFormat("HH:mm:ss");
            String dataString = hours + ":" + minutes + ":" + seconds;
            return dataString;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "");
            return null;
        }
    }

    private void finalizarSistema() {
        try {
            int y = JConfirmMessage.showOptionDialog("", "Deseja finalizar o sistema?");
            if (y == JOptionPane.YES_OPTION) {
                //GlobalParameter.getInstance().getCbc().desconetarDispositivo();
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void carregaBallon() throws AWTException {
        try {
            TyBallon ball = new TyBallon();
            ball.setImageIcon("/imagens/icontray.png", "Icone do Tray");
            //ball.setUsePopMenu(true);
            TrayIcon trayIcon = ball.create("Extrator de Abastecimentos");

            SystemTray tray = SystemTray.getSystemTray();
            tray.add(trayIcon);
            trayIcon.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        setVisible(true);
                        setState(JFrame.NORMAL);
                    }
                }
            });

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    private void iniciaConexaoCompanyTec() {
        try {
            this.cbc = GlobalParameter.getInstance().getCbc();

            if ((!cbc.isConectado()) && ("S".equals("S"))) {
                cbc.conectarDispositivo(jTextField1.getText().trim(), Integer.valueOf(jTextField2.getText().trim()));

                GeraLog ger = new GeraLog();
                ger.gravaMensagensLog(new Throwable("Conectou na Concentradora"));
                ger.close();

                Thread.sleep(2000);
            }

            if (cbc.isConectado()) {
                realTimeConsultasCompanyTec = new Thread(new ConsultaAbastecimento(), "ConsultaAbastecimento");
                realTimeConsultasCompanyTec.start();
            } else {
                JConfirmMessage.showMessageDialog("Falha ao conectar com o equipamento.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    public static ExportTableModel getModelExport() {
        if (exportTableModel == null) {
            exportTableModel = (ExportTableModel) jTable1.getModel();
        }
        return exportTableModel;
    }

    public static void carregaGrid() {
        getModelExport().limpar();
        getModelExport().addListaString(AbastecimentoDB.buscaExportados());

        TableColumnModel columnModel = jTable1.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(50);
        ButtonColumn buttonColumn = new ButtonColumn(jTable1, 2);

        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 || e.getClickCount() == 2) {
                    // Se clicado na coluna 4
                    if (jTable1.getSelectedColumn() == 2) {
                        //pega dt que foi gerado

                        String dataGeracao = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                        Integer ano, mes, dia, hora, min, seg;
                        ano = Integer.parseInt(dataGeracao.substring(8, 10));
                        mes = Integer.parseInt(dataGeracao.substring(3, 5));
                        dia = Integer.parseInt(dataGeracao.substring(0, 2));
                        hora = Integer.parseInt(dataGeracao.substring(11, 13));
                        min = Integer.parseInt(dataGeracao.substring(14, 16));
                        seg = Integer.parseInt(dataGeracao.substring(17, 19));

                        Date d = new Date(ano+100, mes-1, dia, hora, min, seg);
                        gerarArquivoNovamente(d);

                        //JConfirmMessage.showMessageDialog("teste", "");
                    }
                }
            }
        });
    }

    private static String preparaConteudoJaGerado(Date dtGerado) {
        try {
            String conteudo = "";
            Iterator iterator = AbastecimentoDB.buscaPorDataExportados(dtGerado).iterator();
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

    private static void gerarArquivoNovamente(Date dtGerado) {
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
            //Date dataAtual = new Date();

            String conteudo = preparaConteudoJaGerado(dtGerado);
            String nomeArquivo = sdf.format(dtGerado);

            if (gravarTXT(f.getAbsolutePath() + "\\" + nomeArquivo + ".txt", conteudo)) {
                SDIPrincipalGUI.jLabel1.setText("Gerando arquivo de exportação.......");
                SDIPrincipalGUI.jButton1.setEnabled(true);
                SDIPrincipalGUI.jLabel1.setText("Exportação realizada com sucesso.");
                SDIPrincipalGUI.carregaGrid();
                Runtime.getRuntime().exec("explorer " + f.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean gravarTXT(String caminhoCompleto, String texto) {
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
