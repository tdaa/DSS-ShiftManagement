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
        profButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        jLabel1.setText("Admin");

        ucButton.setText("Unidades Curriculares");
        ucButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ucButtonActionPerformed(evt);
            }
        });

        alunosButton.setText("Alunos");

        horarioButton.setText("Elaborar Horário");
        horarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horarioButtonActionPerformed(evt);
            }
        });

        profButton.setText("Professores");
        profButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ucButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(horarioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(alunosButton, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(profButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel1)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ucButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alunosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
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

    private void profButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profButtonActionPerformed
        // botao professores
    }//GEN-LAST:event_profButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alunosButton;
    private javax.swing.JButton horarioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton profButton;
    private javax.swing.JButton ucButton;
    // End of variables declaration//GEN-END:variables
}
