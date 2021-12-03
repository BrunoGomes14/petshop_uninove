/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import Models.Cliente;
import Models.ServicoHistorico;
import Models.Usuario;
import RN.RNCliente;
import RN.RNServico;
import RN.RNUsuario;
import Util.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class FrmServicoHistorico extends javax.swing.JFrame {

    ArrayList<Cliente> arrClientes;
    ArrayList<Usuario> arrUsuario;
    
    public int idUsuario = 0;
    
    
    /**
     * Creates new form FrmServicoHistorico
     */
    public FrmServicoHistorico() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("src\\resouces\\icone.png").getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistorico = new javax.swing.JTable();
        txtDtDe = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDtAte = new javax.swing.JFormattedTextField();
        cboAtendentes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboClientes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Histórico de serviços");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tblHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Serviço", "Cliente", "Pet", "Atendente", "Data Serviço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHistorico);

        try {
            txtDtDe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setText("De");

        jLabel2.setText("Até");

        try {
            txtDtAte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cboAtendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAtendentesActionPerformed(evt);
            }
        });

        jLabel3.setText("Atendente");

        jLabel4.setText("Cliente");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(txtDtDe)
                            .addComponent(jLabel2)
                            .addComponent(txtDtAte, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboAtendentes, 0, 135, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(cboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(34, 34, 34)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDtDe, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDtAte, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboAtendentes)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboAtendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAtendentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAtendentesActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        preencheLista();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        preencheTela();
    }//GEN-LAST:event_formWindowActivated

    private void preencheLista(){
        int idUsuario = 0;
        int idCliente = 0;
        Date dtInicio = null;
        Date dtFim = null;
        
        if (txtDtDe.getText().replace("/", "").trim().isEmpty()
          ||txtDtAte.getText().replace("/", "").trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "As datas são obrigatórias!");
            return;
        }
        
        dtInicio = Util.converteData(txtDtDe.getText());
        dtFim = Util.converteData(txtDtAte.getText());
        
        if (cboAtendentes.getSelectedIndex() > 0){
            idUsuario = arrUsuario.get(cboAtendentes.getSelectedIndex()).getId();
        }
        
        if (cboClientes.getSelectedIndex() > 0){
            idCliente = arrClientes.get(cboClientes.getSelectedIndex()).getId();
        }
        
        ArrayList<ServicoHistorico> servicos = new RNServico().retornaHistoricoServicos(idCliente, dtInicio, dtFim, idUsuario);
        DefaultTableModel tblHistoricoModel = (DefaultTableModel) tblHistorico.getModel();
        tblHistoricoModel.getDataVector().clear();
        tblHistoricoModel.fireTableDataChanged();
        
        for (ServicoHistorico servico : servicos){
            tblHistoricoModel.addRow(new Object[]{
                servico.getNomeServico(),
                servico.getCliente(),
                servico.getPet().getNome(),
                servico.getUsuario(),
                Util.dataHoraToString(servico.getDtPrestacaoServico())
            });
        }
    }
    
    public void preencheTela(){
        
        Calendar dtHj = Calendar.getInstance();
        Calendar dtReduz = Calendar.getInstance();
        dtReduz.add(Calendar.DAY_OF_MONTH, -5);
        
        txtDtDe.setText(Util.dataToString(dtReduz.getTime()));
        txtDtAte.setText(Util.dataToString(dtHj.getTime()));
        
        arrClientes = new RNCliente().listarTodosUsuarios("");
        arrClientes.add(0, new Cliente());
        for (Cliente cliente: arrClientes){
            cboClientes.addItem(cliente.getCliente()); 
        }
        
        arrUsuario = new RNUsuario().listarTodosUsuarios();
        arrUsuario.add(0, new Usuario());
        for (Usuario usuario: arrUsuario){
            cboAtendentes.addItem(usuario.getUsuario());
            
            if (idUsuario > 0 && idUsuario == usuario.getId()) {
                cboAtendentes.setSelectedIndex(arrUsuario.indexOf(usuario)); 
                cboAtendentes.setEnabled(false);
            }
        }
        
        preencheLista();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmServicoHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmServicoHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmServicoHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmServicoHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmServicoHistorico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cboAtendentes;
    private javax.swing.JComboBox<String> cboClientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistorico;
    private javax.swing.JFormattedTextField txtDtAte;
    private javax.swing.JFormattedTextField txtDtDe;
    // End of variables declaration//GEN-END:variables
}
