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
public class DialogTipoCurso extends javax.swing.JDialog {
    
    
    private ShiftManagement system;
    /**
     * Creates new form DialogTipoCurso
     */
    public DialogTipoCurso(java.awt.Frame parent, boolean modal, ShiftManagement s) {
        super(parent, modal);
        initComponents();
        this.system = s;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        licBox = new javax.swing.JCheckBox();
        miBox = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setText("Bem-Vindo Admin!");

        licBox.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        licBox.setText("Licenciatura");

        miBox.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        miBox.setText("Mestrado Integrado");
        miBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBoxActionPerformed(evt);
            }
        });

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Qual o tipo de curso?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(miBox)
                            .addComponent(licBox)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel2)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(licBox)
                .addGap(18, 18, 18)
                .addComponent(miBox)
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // confirmar button
       if(licBox.isSelected() && !miBox.isSelected()){
           this.system.setTipoCurso("Licenciatura");
       }
       if(!licBox.isSelected() && miBox.isSelected()){
           this.system.setTipoCurso("Mestrado Integrado");
       }
       if(licBox.isSelected() && miBox.isSelected()){
           javax.swing.JOptionPane.showMessageDialog(this, "Selecione apenas um campo!", "Apenas um.", 0);
       }
       if(!licBox.isSelected() && !miBox.isSelected()){
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um campo!", "Selecione um campo.", 0);
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JCheckBox licBox;
    private javax.swing.JCheckBox miBox;
    // End of variables declaration//GEN-END:variables
}
