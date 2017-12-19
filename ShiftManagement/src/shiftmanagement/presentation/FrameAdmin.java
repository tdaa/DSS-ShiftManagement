/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.presentation;

import shiftmanagement.Business.ShiftManagement;

/**
 *
 * @author Tiago
 */
public class FrameAdmin extends javax.swing.JFrame {
    
    private ShiftManagement system;
    private String curso;

    /**
     * Creates new form FrameAdmin
     */
    public FrameAdmin(ShiftManagement s, String curso) {
        initComponents();
        this.system = s;
        this.curso = curso;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ucButton = new javax.swing.JButton();
        alunosButton = new javax.swing.JButton();
        horarioButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Drugs", 0, 48)); // NOI18N
        jLabel1.setText("Menu Admin");

        ucButton.setText("Unidades Curriculares");
        ucButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ucButtonActionPerformed(evt);
            }
        });

        alunosButton.setText("Utilizadores");
        alunosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alunosButtonActionPerformed(evt);
            }
        });

        horarioButton.setText("Elaborar Horário");
        horarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horarioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(230, 230, 230))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ucButton)
                        .addGap(46, 46, 46)
                        .addComponent(alunosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(horarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ucButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alunosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ucButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ucButtonActionPerformed
        //botao uc's
        if(this.curso.equals("Mestrado Integrado")){
            FrameMenuUCSMI menuUcsMi = new FrameMenuUCSMI(this.system);
            menuUcsMi.setVisible(true);
        }
        else{
            FrameMenuUCSL menuUcsL = new FrameMenuUCSL(this.system);
            menuUcsL.setVisible(true);
        }
    }//GEN-LAST:event_ucButtonActionPerformed

    private void horarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horarioButtonActionPerformed
        //botao horario
    }//GEN-LAST:event_horarioButtonActionPerformed

    private void alunosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alunosButtonActionPerformed
        // botao alunos
        FrameUtilizadores janelaAluno = new FrameUtilizadores(this.system);
        janelaAluno.setVisible(true);
    }//GEN-LAST:event_alunosButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alunosButton;
    private javax.swing.JButton horarioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton ucButton;
    // End of variables declaration//GEN-END:variables
}
