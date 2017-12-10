/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.presentation;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import shiftmanagement.Business.ShiftManagement;

/**
 *
 * @author Tiago
 */
public class FrameConsultarUc extends javax.swing.JFrame {
    
    private ShiftManagement system;
    private String nomeUC;
    private String codigoUC;
    private String nomePerfil;
    private int tipoUC;//1 para lic; 2 para comp.
    
     /**
     * Creates new form FrameConsultarUcLicenciatura
     * @param system
     * @param uc
     * @param tipoUC
     */
    public FrameConsultarUc(ShiftManagement system, String uc, int tipoUC) {
        initComponents();
        this.system = system;
        this.nomeUC = uc.substring(uc.indexOf(uc.indexOf("-") + 2), uc.length());
        this.codigoUC = uc.substring(0, uc.indexOf(" "));
        this.tipoUC = tipoUC;
        this.nomePerfil = null;
        atualizaJanela();
    }
    
    /**
     *
     * @param system
     * @param uc
     * @param nomePerfil
     * @param tipoUC
     */
    public FrameConsultarUc(ShiftManagement system, String uc, String nomePerfil, int tipoUC){
        initComponents();
        this.system = system;
        this.nomeUC = uc.substring(uc.indexOf(uc.indexOf("-") + 2), uc.length());
        this.codigoUC = uc.substring(0, uc.indexOf(" "));
        this.nomePerfil = nomePerfil;
        this.tipoUC = tipoUC;
        atualizaJanela();
    }
    
    /**
     *
     */
    public void atualizaJanela(){
        nomeDaUc.setText(this.nomeUC);
        designacaoUcField.setText(this.nomeUC);
        codigoField.setText(this.codigoUC);
        String nomeProf = this.system.getProfUc(this.codigoUC, this.tipoUC, this.nomePerfil);
        regField.setText(nomeProf);
        atualizaListaProfessores();
        atualizaListaTurnos();
    }
    
    /**
     *
     */
    public void atualizaListaProfessores(){
        DefaultListModel<String> dlm = new DefaultListModel<>();
        ArrayList<String> Profs = this.system.getListaProfs(this.codigoUC, this.tipoUC, this.nomePerfil);
        for(String s : Profs){
            dlm.addElement(s);
        }
        docentesList.setModel(dlm);
    }
    
    /**
     *
     */
    public void atualizaListaTurnos(){
        DefaultListModel<String> dlm = new DefaultListModel<>();
        ArrayList<String> lista = this.system.getListaTurnos(this.codigoUC, this.tipoUC, this.nomePerfil);
        lista.forEach((s) -> {
            dlm.addElement(s);
        });
        turnosList.setModel(dlm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeDaUc = new javax.swing.JLabel();
        nomeUc = new javax.swing.JLabel();
        codigoLabel = new javax.swing.JLabel();
        profResponsavel = new javax.swing.JLabel();
        codigoField = new javax.swing.JTextField();
        regField = new javax.swing.JTextField();
        designacaoUcField = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        turnosList = new javax.swing.JList<>();
        verTurnoButton = new javax.swing.JButton();
        addTurnoButton = new javax.swing.JButton();
        removButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        docentesList = new javax.swing.JList<>();
        verProfButton = new javax.swing.JButton();
        addProfButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        fecharButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nomeDaUc.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        nomeDaUc.setText("NOME DA UC");

        nomeUc.setText("Designação");

        codigoLabel.setText("Código");

        profResponsavel.setText("Professor Regente");

        codigoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoFieldActionPerformed(evt);
            }
        });

        regField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFieldActionPerformed(evt);
            }
        });

        designacaoUcField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                designacaoUcFieldActionPerformed(evt);
            }
        });

        turnosList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(turnosList);

        verTurnoButton.setText("Ver Turno");
        verTurnoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verTurnoButtonActionPerformed(evt);
            }
        });

        addTurnoButton.setText("Adicionar Turno");
        addTurnoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTurnoButtonActionPerformed(evt);
            }
        });

        removButton.setText("Remover Turno");
        removButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addTurnoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(verTurnoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(verTurnoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addTurnoButton)
                        .addGap(12, 12, 12)
                        .addComponent(removButton)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Turnos", jPanel2);

        docentesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(docentesList);

        verProfButton.setText("Ver Professor");
        verProfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verProfButtonActionPerformed(evt);
            }
        });

        addProfButton.setText("Adicionar Professor");
        addProfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProfButtonActionPerformed(evt);
            }
        });

        removerButton.setText("Remover Professor");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verProfButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProfButton)
                    .addComponent(removerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(verProfButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addProfButton)
                .addGap(12, 12, 12)
                .addComponent(removerButton)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Equipa Docente", jPanel1);

        fecharButton.setText("Fechar");
        fecharButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nomeDaUc, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(profResponsavel)
                            .addComponent(codigoLabel)
                            .addComponent(nomeUc))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(codigoField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                            .addComponent(designacaoUcField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(regField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(fecharButton)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(nomeDaUc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeUc)
                    .addComponent(designacaoUcField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLabel)
                    .addComponent(codigoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profResponsavel)
                    .addComponent(regField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fecharButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoFieldActionPerformed

    private void regFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regFieldActionPerformed

    private void designacaoUcFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_designacaoUcFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_designacaoUcFieldActionPerformed

    private void verProfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verProfButtonActionPerformed
        // ver Professor (docentes)
        String prof = docentesList.getSelectedValue();
        if(prof != null){
            DialogVerProfessor verProf = new DialogVerProfessor(this, true, this.system, prof);
            verProf.setVisible(true);
        }
        else{
            //abrir dialog a informar que nao selecionou nenhum prof.
        }
    }//GEN-LAST:event_verProfButtonActionPerformed

    private void fecharButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharButtonActionPerformed
        // botao fechar
        this.dispose();
    }//GEN-LAST:event_fecharButtonActionPerformed

    private void addProfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProfButtonActionPerformed
        // botao adicionar professor
        DialogAddProfessor addProf = new DialogAddProfessor(this, true, this.system, this.codigoUC, this.tipoUC, this.nomePerfil);
        addProf.setVisible(true);
        atualizaJanela();
    }//GEN-LAST:event_addProfButtonActionPerformed

    private void removerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerButtonActionPerformed
        // botao remover
        String prof = docentesList.getSelectedValue();
        if(prof!=null){
            String username = prof.substring(0, prof.indexOf(" ")-1);
            this.system.removeProfDeUc(username, codigoUC, tipoUC, nomePerfil);
            atualizaJanela();
        }
 
    }//GEN-LAST:event_removerButtonActionPerformed

    private void verTurnoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verTurnoButtonActionPerformed
        // ver turno botao
        String turno = turnosList.getSelectedValue();
        if(turno != null){
            DialogVerTurno verTurno = new DialogVerTurno(this, true, this.system, turno, this.codigoUC, this.tipoUC, this.nomePerfil);
            verTurno.setVisible(true);
        }
    }//GEN-LAST:event_verTurnoButtonActionPerformed

    private void addTurnoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTurnoButtonActionPerformed
        // botao adicionar turno
        DialogAddTurno addTurno = new DialogAddTurno(this, true, this.system, this.codigoUC, this.tipoUC, this.nomePerfil);
        addTurno.setVisible(true);
        atualizaJanela();
    }//GEN-LAST:event_addTurnoButtonActionPerformed

    private void removButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removButtonActionPerformed
        // botao remover turno
        String turno = turnosList.getSelectedValue();
        if(turno != null){
            this.system.removeTurnoDeUc(turno, this.codigoUC, this.tipoUC, this.nomePerfil);
            this.system.atualizaIdTurnos(turno, this.codigoUC, this.tipoUC, this.nomePerfil);
            atualizaJanela();
        }
    }//GEN-LAST:event_removButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProfButton;
    private javax.swing.JButton addTurnoButton;
    private javax.swing.JTextField codigoField;
    private javax.swing.JLabel codigoLabel;
    private javax.swing.JTextField designacaoUcField;
    private javax.swing.JList<String> docentesList;
    private javax.swing.JButton fecharButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nomeDaUc;
    private javax.swing.JLabel nomeUc;
    private javax.swing.JLabel profResponsavel;
    private javax.swing.JTextField regField;
    private javax.swing.JButton removButton;
    private javax.swing.JButton removerButton;
    private javax.swing.JList<String> turnosList;
    private javax.swing.JButton verProfButton;
    private javax.swing.JButton verTurnoButton;
    // End of variables declaration//GEN-END:variables
}