/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.presentation;

import java.util.ArrayList;
import java.util.HashSet;
import shiftmanagement.Business.ShiftManagement;
import shiftmanagement.Business.UC.UCComplementar;
import shiftmanagement.Business.UC.UCLicenciatura;
import shiftmanagement.Business.UC.UCPerfil;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class FrameConfirmaçao extends javax.swing.JFrame {
    
    
    private ShiftManagement system;
    private String nomeUC;
    private String codigoUC;
    private String regente;
    private HashSet<Professor> equipa;
    private String diaS;
    private String per;
    private int tipoUC;
    private String nomePerfil=null;
            
    /**
     * Creates new form FrameConfirmaçao
     * @param s
     * @param nome
     * @param equipa
     * @param p
     * @param codigo
     * @param tipoUC
     * @param nomePerfil
     */
    public FrameConfirmaçao(ShiftManagement s, String nome, String codigo, String p, HashSet<Professor> equipa, int tipoUC, String nomePerfil) {
        initComponents();
        this.system = s;
        this.nomeUC = nome;
        this.codigoUC = codigo;
        this.regente = p;
        this.equipa = equipa;
        this.tipoUC = tipoUC;
        this.nomePerfil = nomePerfil;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        simButton = new javax.swing.JButton();
        naoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setText("Deseja associar agora os novos turnos?");

        simButton.setText("Sim");
        simButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simButtonActionPerformed(evt);
            }
        });

        naoButton.setText("Não");
        naoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(simButton)
                        .addGap(72, 72, 72)
                        .addComponent(naoButton)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(naoButton)
                    .addComponent(simButton))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simButtonActionPerformed
        //botao sim
        if(this.tipoUC == 1) {
            UCLicenciatura uc = new UCLicenciatura(nomeUC, codigoUC, regente, equipa);
            this.system.addUcLic(uc);
        }
        if(this.tipoUC == 2){
            UCComplementar uc = new UCComplementar(nomeUC, codigoUC, regente, diaS, per, equipa);
            this.system.addUcComp(uc);
        }
        else{
            UCPerfil uc = new UCPerfil(diaS, nomeUC, codigoUC, regente, equipa);
            this.system.addUcPerfil(uc, this.nomePerfil);
        }
        DialogAddTurno novoTurno = new DialogAddTurno(this, true, this.system, this.codigoUC, this.tipoUC, this.nomePerfil);
        novoTurno.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_simButtonActionPerformed

    private void naoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naoButtonActionPerformed
       //botao nao
       this.dispose();
    }//GEN-LAST:event_naoButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton naoButton;
    private javax.swing.JButton simButton;
    // End of variables declaration//GEN-END:variables
}
