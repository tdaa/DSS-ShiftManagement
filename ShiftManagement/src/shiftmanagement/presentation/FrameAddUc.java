/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.presentation;

import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import shiftmanagement.Business.ShiftManagement;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class FrameAddUc extends javax.swing.JFrame {
    
    private ShiftManagement system;
    private boolean jaDefinido = false;
    private String regente;
    private HashSet<Professor> equipa;
    private int tipoUC;
    private String nomePerfil;
    
    /**
     * Creates new form FrameAddUcLic
     * @param s
     * @param tipoUC
     */
    public FrameAddUc(ShiftManagement s, int tipoUC) {
        initComponents();
        this.system = s;
        this.tipoUC = tipoUC;
        this.nomePerfil = null;
        this.equipa = new HashSet<>();
        atualizaLista();
        atualizaJanela();
    }
    
    public FrameAddUc(ShiftManagement s, int tipoUC, String nomePerfil){
        initComponents();
        this.system = s;
        this.tipoUC = tipoUC;
        this.nomePerfil = nomePerfil;
        this.equipa = new HashSet<>();
        atualizaLista();
        atualizaJanela();
    }
    
    //username + nome
    private void atualizaLista(){
        DefaultListModel<String> dlm = new DefaultListModel<>();
        this.system.getListaTodosProfs().forEach((s) -> {
            dlm.addElement(s);
        });
        profList.setModel(dlm);
    }
    
    private void atualizaJanela(){
        searchBox.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = searchBox.getText();
                filterModel((DefaultListModel<String>)profList.getModel(), filter);
            }
        });
        
        if(this.tipoUC==3){
           this.comboPer.setVisible(false);
        }
        if(this.tipoUC==1){
            this.diaSCombo.setVisible(false);
            this.comboPer.setVisible(false);
            this.perLabel.setVisible(false);
            this.diaLabel.setVisible(false);
        }
    }
    
    public void filterModel(DefaultListModel<String> model, String filter) {
        this.system.getListaTodosProfs().forEach((s) -> {
            if (!s.startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        });
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        codigoField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        searchBox = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        profList = new javax.swing.JList<>();
        regButton = new javax.swing.JButton();
        equipaButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        diaLabel = new javax.swing.JLabel();
        perLabel = new javax.swing.JLabel();
        diaSCombo = new javax.swing.JComboBox<>();
        comboPer = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Drugs", 0, 36)); // NOI18N
        jLabel1.setText("Nova UC");

        jLabel2.setText("Nome");

        jLabel3.setText("Código");

        jLabel4.setText("Professores");

        profList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(profList);

        regButton.setText("Definir como professor regente");
        regButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regButtonActionPerformed(evt);
            }
        });

        equipaButton.setText("Adicionar a equipa docente");
        equipaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipaButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Confirmar operações");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        diaLabel.setText("Dia Semana");

        perLabel.setText("Per");

        diaSCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboPer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(equipaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(confirmButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nomeField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(codigoField))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(diaLabel)
                            .addComponent(perLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(diaSCombo, 0, 162, Short.MAX_VALUE)
                            .addComponent(comboPer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(regButton))
                .addGap(73, 73, 73))
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 88, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(diaLabel)
                        .addComponent(diaSCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(codigoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(perLabel)
                    .addComponent(comboPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regButton)
                    .addComponent(confirmButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equipaButton)
                    .addComponent(cancelButton))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regButtonActionPerformed
        // botao definir regente
        if(this.jaDefinido == false){
            String s;
            if((s = this.profList.getSelectedValue()) != null){
                this.regente = s.substring(0, s.indexOf(" "));
                this.jaDefinido = true;
                javax.swing.JOptionPane.showMessageDialog(this, "Professor associado!", "Feito.", 0);
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Nenhum professor selecionado!", "Sem elementos selecionados.", 0);
            }
        }
        else{
            javax.swing.JOptionPane.showMessageDialog(this, "Professor já definido!", "Impossivel definir nova associação.", 0);
        }
    }//GEN-LAST:event_regButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // botao confirmar
        String nome = nomeField.getText();
        String codigo = codigoField.getText();
        if(this.tipoUC == 2){
            String per = (String) this.comboPer.getSelectedItem();
            String diaS = (String) this.diaSCombo.getSelectedItem();
            if(nome!=null && codigo!=null && this.comboPer.getSelectedItem()!=null && this.diaSCombo.getSelectedItem()!=null){
                FrameConfirmaçao confirmaUc = new FrameConfirmaçao(system, nome, codigo, regente, equipa, this.tipoUC, this.nomePerfil, per, diaS);
                confirmaUc.setVisible(true);
                this.dispose();
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor preencher todos os campos!", "Tudo.", 0);
            }
        }
        if(this.tipoUC == 3){
            String diaS = (String) this.diaSCombo.getSelectedItem();
            if(nome!=null && codigo!=null && this.diaSCombo.getSelectedItem()!=null){
                FrameConfirmaçao confirmaUc = new FrameConfirmaçao(system, nome, codigo, regente, equipa, this.tipoUC, this.nomePerfil, null, diaS);
                confirmaUc.setVisible(true);
                this.dispose();
            }
        }
        if(this.tipoUC==1){
            if(nome!=null && codigo!=null){
                FrameConfirmaçao confirmaUc = new FrameConfirmaçao(system, nome, codigo, regente, equipa, this.tipoUC, this.nomePerfil, null, null);
                confirmaUc.setVisible(true);
                this.dispose();
            }        
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void equipaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipaButtonActionPerformed
        // add prof aos docentes
        String s = this.profList.getSelectedValue();
        if(s != null){
            s = s.substring(0, s.indexOf(" "));
            Professor p = this.system.getProfPorUsername(s);
            if(!this.equipa.contains(p)){
                this.equipa.add(p);
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Professor já faz parte da equipa docente.", "Já inscrito.", 0);
            }
        }
    }//GEN-LAST:event_equipaButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // botao cancelar
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField codigoField;
    private javax.swing.JComboBox<String> comboPer;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel diaLabel;
    private javax.swing.JComboBox<String> diaSCombo;
    private javax.swing.JButton equipaButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomeField;
    private javax.swing.JLabel perLabel;
    private javax.swing.JList<String> profList;
    private javax.swing.JButton regButton;
    private javax.swing.JTextField searchBox;
    // End of variables declaration//GEN-END:variables
}
