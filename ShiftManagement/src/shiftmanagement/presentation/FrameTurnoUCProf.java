/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.presentation;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import shiftmanagement.Business.ShiftManagement;

/**
 *
 * @author Tiago
 */
public class FrameTurnoUCProf extends javax.swing.JFrame {
    
    private ShiftManagement system;
    private String codigoUC;
    private String idTurno;
    private boolean regente;
    
    /**
     * Creates new form FrameTurnoUCProf
     * @param s
     * @param codigoUC
     */
    public FrameTurnoUCProf(ShiftManagement s, String codigoUC, String turno, boolean regente) {
        initComponents();
        this.system = s;
        this.codigoUC = codigoUC;
        this.idTurno = turno;
        this.regente = regente;
        atualizaJanela();
    }
    
    private void atualizaJanela(){
        if(this.regente == false){
            this.removerButton.setVisible(false);
        }
        this.tituloLabel.setText(this.system.getNomeUc(codigoUC));
        String aluno, nfaltas;
        ArrayList<String> alunosFaltas = this.system.getAlunosFaltas(codigoUC, idTurno);
        DefaultListModel<String> dlm = new DefaultListModel<>();
        for(String s: alunosFaltas){
            aluno = s.substring(0, s.indexOf(" "));
            nfaltas = s.substring(s.indexOf("-")+2, s.length());
            dlm.addElement("Aluno: " + aluno + " com " + nfaltas + " faltas!");
        }
        faltasList.setModel(dlm);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        faltasList = new javax.swing.JList<>();
        marcarButton = new javax.swing.JButton();
        sairButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tituloLabel.setFont(new java.awt.Font("Drugs", 1, 24)); // NOI18N
        tituloLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLabel.setText("Nome UC");

        faltasList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(faltasList);

        marcarButton.setText("Marcar Falta");
        marcarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcarButtonActionPerformed(evt);
            }
        });

        sairButton.setText("Fechar");
        sairButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairButtonActionPerformed(evt);
            }
        });

        removerButton.setText("Remover Aluno");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(sairButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(marcarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(92, 92, 92))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tituloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(tituloLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(marcarButton)
                        .addGap(30, 30, 30)
                        .addComponent(removerButton)
                        .addGap(35, 35, 35)
                        .addComponent(sairButton)))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void marcarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcarButtonActionPerformed
        // botao marcar faltas
        String a = this.faltasList.getSelectedValue();
        String n, aluno;
        if(a != null){
            aluno = a.substring(a.indexOf("Aluno")+7, a.indexOf("com")-1);
            n = a.substring(a.indexOf("com")+4, a.length());
            this.system.adicionaFaltaAAluno(aluno, this.codigoUC, this.idTurno);
            atualizaJanela();
            JOptionPane.showMessageDialog(rootPane, "Falta adicionada com sucesso!", "Nova falta!", 1);
        }
        
    }//GEN-LAST:event_marcarButtonActionPerformed

    private void sairButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairButtonActionPerformed
        // botao sair
        this.dispose();
    }//GEN-LAST:event_sairButtonActionPerformed

    private void removerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerButtonActionPerformed
        // botao remover
        int faltas, aulas;
        String user;
        String aluno = this.faltasList.getSelectedValue();
        if(aluno != null){
            user = aluno.substring(aluno.indexOf(":")+2, aluno.indexOf("com")-1);
            faltas = Integer.parseInt(aluno.substring(aluno.indexOf("com")+4, aluno.indexOf("faltas")-1));
            aulas = this.system.getNumeroAulas(codigoUC, idTurno);
            if(aulas != -1 && faltas > 0.25*aulas){
                this.system.removeAlunoDeUC(codigoUC, user);
                JOptionPane.showConfirmDialog(rootPane, "Aluno removido com sucesso!");
                this.atualizaJanela();
            }
            else{
                if(aulas!=-1 && faltas<=0.25*aulas){
                    JOptionPane.showConfirmDialog(rootPane, "Aluno sem número mínimo de faltas para o remover!");
                }
            }
        }
    }//GEN-LAST:event_removerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> faltasList;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton marcarButton;
    private javax.swing.JButton removerButton;
    private javax.swing.JButton sairButton;
    private javax.swing.JLabel tituloLabel;
    // End of variables declaration//GEN-END:variables
}