/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package crudclients.screens;

import javax.swing.*;
import crudclients.dao.ClientMapDAO;
import javax.swing.table.DefaultTableModel;
import crudclients.objects.Client;

/**
 *
 * @author caique
 */
public class HomePage extends javax.swing.JFrame {
    private ClientMapDAO clientsMap;
    private DefaultTableModel tableModel;

    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
        this.clientsMap = new ClientMapDAO();
        this.tableModel = new DefaultTableModel();
        this.tableSettings();
        this.messagesLabel.setVisible(false);
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
        clientsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        surnameInput = new javax.swing.JTextField();
        cpfInput = new javax.swing.JTextField();
        emailInput = new javax.swing.JTextField();
        telephoneInput = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        destroyButton = new javax.swing.JButton();
        cleanButton = new javax.swing.JButton();
        messagesLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        clientsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(clientsTable);

        jLabel1.setText("Nome");

        jLabel2.setText("Sobrenome");

        jLabel3.setText("CPF");

        jLabel4.setText("E-mail");

        jLabel5.setText("Telefone");

        registerButton.setText("Registrar");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Atualizar");
        updateButton.setEnabled(false);
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        destroyButton.setText("Excluir");
        destroyButton.setEnabled(false);
        destroyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destroyButtonActionPerformed(evt);
            }
        });

        cleanButton.setText("Limpar");
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        messagesLabel.setText("Mensagens");

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(registerButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(destroyButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cleanButton)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telephoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpfInput, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(surnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(messagesLabel)
                                .addGap(188, 188, 188))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(surnameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cpfInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(telephoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(messagesLabel)
                        .addGap(24, 24, 24)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton)
                    .addComponent(updateButton)
                    .addComponent(destroyButton)
                    .addComponent(cleanButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.endProcess();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        if (!this.isValidInput(this.nameInput.getText())) {
            this.messagesLabel.setText("O campo nome é obrigatório");
            this.messagesLabel.setVisible(true);
        } else if (!this.isValidInput(this.cpfInput.getText())) {
            this.messagesLabel.setText("O campo CPF é obrigatório");
            this.messagesLabel.setVisible(true);
        } else {
            this.messagesLabel.setVisible(false);
            final Client client = new Client();
            client.setName(this.nameInput.getText().trim());
            client.setSurname(this.surnameInput.getText().trim());
            client.setCPF(this.cpfInput.getText().trim());
            client.setEmail(this.emailInput.getText().trim());
            client.setTelephone(this.telephoneInput.getText().trim());
            if (this.clientsMap.register(client)) {
                this.messagesLabel.setText("Cliente registrado com sucesso");
                this.messagesLabel.setVisible(true);
                this.tableModel.addRow(new Object[]{client.getName(), client.getSurname(), client.getCPF(), client.getEmail(), client.getTelephone()});
                this.clearInputs();
            } else {
                this.messagesLabel.setText("Falha ao registrar o cliente");
                this.messagesLabel.setVisible(true);
            }
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void clientsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientsTableMouseClicked
        this.cpfInput.setEditable(false);
        this.messagesLabel.setVisible(false);
        this.registerButton.setEnabled(false);
        this.updateButton.setEnabled(true);
        this.destroyButton.setEnabled(true);
        this.cleanButton.setEnabled(true);
        String selectedCpf = this.clientsTable.getValueAt(this.clientsTable.getSelectedRow(), 2).toString();
        Client client = this.clientsMap.show(selectedCpf);
        if (client == null) {
            this.messagesLabel.setText("Ocorreu um erro");
            this.messagesLabel.setVisible(true);
        }
        
        this.nameInput.setText(client.getName() == null ? "" : client.getName());
        this.surnameInput.setText(client.getSurname() == null ? "" : client.getSurname());
        this.cpfInput.setText(client.getCPF() == null ? "" : client.getCPF());
        this.emailInput.setText(client.getEmail() == null ? "" : client.getEmail());
        this.telephoneInput.setText(client.getTelephone() == null ? "" : client.getTelephone());
    }//GEN-LAST:event_clientsTableMouseClicked

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
        this.messagesLabel.setVisible(false);
        this.clearInputs();
        this.registerButton.setEnabled(true);
        this.updateButton.setEnabled(false);
        this.destroyButton.setEnabled(false);
        this.clientsTable.clearSelection();
    }//GEN-LAST:event_cleanButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        if (!this.isValidInput(this.nameInput.getText())) {
            this.messagesLabel.setText("O campo nome é obrigatório");
            this.messagesLabel.setVisible(true);
        } else if (!this.isValidInput(this.cpfInput.getText())) {
            this.messagesLabel.setText("O campo CPF é obrigatório");
            this.messagesLabel.setVisible(true);
        } else {
            this.messagesLabel.setText("Cliente atualizado com sucesso");
            this.messagesLabel.setVisible(true);
            final boolean status = this.clientsMap.update(this.cpfInput.getText(), new Client(this.nameInput.getText(), this.surnameInput.getText(), this.cpfInput.getText(), this.emailInput.getText(), this.telephoneInput.getText()));
            if (!status) {
                this.messagesLabel.setText("Ocorreu um erro ao atualizar o cliente");
                this.messagesLabel.setVisible(true);
            }

            final int selectedRow = this.clientsTable.getSelectedRow();
            this.clientsTable.setValueAt(this.nameInput.getText(), selectedRow, 0);
            this.clientsTable.setValueAt(this.surnameInput.getText(), selectedRow, 1);
            this.clientsTable.setValueAt(this.cpfInput.getText(), selectedRow, 2);
            this.clientsTable.setValueAt(this.emailInput.getText(), selectedRow, 3);
            this.clientsTable.setValueAt(this.telephoneInput.getText(), selectedRow, 4);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void destroyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destroyButtonActionPerformed
        final int selectedRow = this.clientsTable.getSelectedRow();
        String clientCpf = this.clientsTable.getValueAt(selectedRow, 2).toString();
        final boolean status = this.clientsMap.destroy(clientCpf);
        if (!status) {
            this.messagesLabel.setText("Ocorreu um erro ao excluir o cliente");
            this.messagesLabel.setVisible(true);
        }
        this.clearInputs();
        this.registerButton.setEnabled(true);
        this.updateButton.setEnabled(false);
        this.destroyButton.setEnabled(false);
        
        this.tableModel.removeRow(selectedRow);
    }//GEN-LAST:event_destroyButtonActionPerformed

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
    
    private void tableSettings() {
        this.tableModel.addColumn("Nome");
        this.tableModel.addColumn("Sobrenome");
        this.tableModel.addColumn("CPF");
        this.tableModel.addColumn("E-mail");
        this.tableModel.addColumn("Telefone");
        this.clientsTable.setModel(this.tableModel);
    }
    
    private void clearInputs() {
        this.nameInput.setText("");
        this.surnameInput.setText("");
        this.cpfInput.setEditable(true);
        this.cpfInput.setText("");
        this.emailInput.setText("");
        this.telephoneInput.setText("");
    }
    
    /**
     * Método para validar os inputs
     */
    private boolean isValidInput(String value) {
        return !value.isEmpty();
    }
    
    /**
     * Método para encerrar a aplicação.ã
     */
    private void endProcess() {
        final Object[] options = {"Sair", "Cancelar"};
        int userResponse = JOptionPane.showOptionDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (userResponse == 0) {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cleanButton;
    private javax.swing.JTable clientsTable;
    private javax.swing.JTextField cpfInput;
    private javax.swing.JButton destroyButton;
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel messagesLabel;
    private javax.swing.JTextField nameInput;
    private javax.swing.JButton registerButton;
    private javax.swing.JTextField surnameInput;
    private javax.swing.JTextField telephoneInput;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
