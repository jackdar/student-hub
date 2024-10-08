/*
 * Created by Jack Darlington | 2023
 */
package com.jackdarlington.studenthub.ui;

import com.jackdarlington.studenthub.main.Model;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;

/**
 *
 * @author Jack
 */
public class LogInUI extends javax.swing.JFrame {
    
    Font titleFont;
    
    /**
     * Creates new form StudentHub
     */
    public LogInUI() {
        
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/DarumadropOne-Regular.ttf"));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(LogInUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        genv.registerFont(titleFont);
        titleFont = titleFont.deriveFont(48f);
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        loginLabelTitle = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        loginEmailLabel = new javax.swing.JLabel();
        loginPasswordLabel = new javax.swing.JLabel();
        loginEmailField = new javax.swing.JTextField();
        loginPasswordField = new javax.swing.JTextField();
        loginSettingsButton = new javax.swing.JButton();
        backgroundPanel = new javax.swing.JPanel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Hub");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginPanel.setBackground(new Color(0,0,0,200));
        loginPanel.setMinimumSize(new java.awt.Dimension(350, 450));
        loginPanel.setPreferredSize(new java.awt.Dimension(500, 600));

        loginLabelTitle.setBackground(new java.awt.Color(255, 255, 255));
        loginLabelTitle.setFont(titleFont);
        loginLabelTitle.setText("Student Hub");

        loginButton.setText("Log In");
        loginButton.setMaximumSize(new java.awt.Dimension(72, 30));
        loginButton.setMinimumSize(new java.awt.Dimension(72, 30));
        loginButton.setPreferredSize(new java.awt.Dimension(72, 30));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        loginEmailLabel.setText("Email");

        loginPasswordLabel.setText("Password");

        loginSettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cog-wheel-silhouette-20px.png"))); // NOI18N
        loginSettingsButton.setMaximumSize(new java.awt.Dimension(50, 50));
        loginSettingsButton.setMinimumSize(new java.awt.Dimension(50, 50));
        loginSettingsButton.setPreferredSize(new java.awt.Dimension(50, 50));
        loginSettingsButton.setRolloverEnabled(false);
        loginSettingsButton.setSize(new java.awt.Dimension(50, 50));
        loginSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginSettingsButtonActionPerformed(evt);
            }
        });

        loginLabelTitle.setForeground(Color.WHITE);
        loginEmailField.putClientProperty("JTextField.verticalAlignment", SwingConstants.CENTER);
        loginPasswordField.putClientProperty("JTextField.verticalAlignment", SwingConstants.CENTER);

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addGap(0, 92, Short.MAX_VALUE)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                                .addComponent(loginLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(loginEmailField)
                                    .addComponent(loginPasswordLabel)
                                    .addComponent(loginEmailLabel)
                                    .addComponent(loginPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(99, 99, 99))))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(loginSettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(loginLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(loginEmailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(loginPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(loginSettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(loginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, -1));

        bg.setFont(bg.getFont());
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        bg.setToolTipText("");

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(bg)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String emailString = loginEmailField.getText();
        String passwordString = loginPasswordField.getText();
        
        System.out.println("Logging in as " + emailString);
    }//GEN-LAST:event_loginButtonActionPerformed

    private void loginSettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginSettingsButtonActionPerformed
        new SettingsUI().setVisible(true);
    }//GEN-LAST:event_loginSettingsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JLabel bg;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginEmailField;
    private javax.swing.JLabel loginEmailLabel;
    private javax.swing.JLabel loginLabelTitle;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField loginPasswordField;
    private javax.swing.JLabel loginPasswordLabel;
    private javax.swing.JButton loginSettingsButton;
    // End of variables declaration//GEN-END:variables
}
