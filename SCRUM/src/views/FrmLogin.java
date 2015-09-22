/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.User;
import DAOs.UserDAO;

/**
 *
 * @author willian
 */
public class FrmLogin
        extends javax.swing.JFrame
{
    UserDAO userTransaction = new UserDAO();
    /**
     * Creates new form FrmLogin
     */
    public FrmLogin()
    {
        try
        {
//              UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
            SwingUtilities.updateComponentTreeUI( this );
        }
        catch ( Exception e )
        {
            JOptionPane.showMessageDialog( null, e );
        }
        this.setTitle( "Login" );
        setIconImage( loadImageIcon("/icons/icon-scrum.png").getImage() );
        
        initComponents();
    }
    
    private static ImageIcon loadImageIcon(String path) {
        URL imgURL = FrmLogin.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblLogin = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        tfdLogin = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        tfdPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                formKeyPressed(evt);
            }
        });

        lblLogin.setText("Login:");

        lblPassword.setText("Senha:");

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnOkActionPerformed(evt);
            }
        });
        btnOk.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                btnOkKeyPressed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelActionPerformed(evt);
            }
        });

        tfdPassword.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                tfdPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword)
                            .addComponent(lblLogin))
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(tfdPassword)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogin)
                    .addComponent(tfdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(tfdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        User u;
        
        try
        {
            u = userTransaction.getUserBylogin( tfdLogin.getText() );
            boolean canLogin = false;
            
            if ( u.getPassword().equals( tfdPassword.getText() ) ) {
                canLogin = true;
            }
            else
            {
                canLogin = false;
            }
            
            if (canLogin) {
                FrmLauncherApplication launcher = new FrmLauncherApplication();
                        launcher.setCurrentUser(u);
                        launcher.setVisible( true );
                        dispose();
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Usuário ou senha inválido." );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, "Erro ao consultar usuários." );
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnOkActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyPressed
    {//GEN-HEADEREND:event_formKeyPressed
    }//GEN-LAST:event_formKeyPressed

    private void btnOkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnOkKeyPressed

    }//GEN-LAST:event_btnOkKeyPressed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
       System.exit( 0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfdPasswordKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfdPasswordKeyPressed
    {//GEN-HEADEREND:event_tfdPasswordKeyPressed
        User u;
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                try
                {
                    u = userTransaction.getUserBylogin( tfdLogin.getText() );
                    boolean canLogin = false;

                    if ( u.getPassword().equals( tfdPassword.getText() ) )
                    {
                        canLogin = true;
                    }
                    else
                    {
                        canLogin = false;
                    }

                    if ( canLogin )
                    {
                        FrmLauncherApplication launcher = new FrmLauncherApplication();
                        launcher.setCurrentUser(u);
//                ImageIcon img = new ImageIcon("/icons/icon-scrum.png");
//                launcher.setIconImage(img.getImage());
                        launcher.setVisible( true );
                        dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "Usuário ou senha inválido." );
                    }
                }
                catch ( Exception e )
                {
                    JOptionPane.showMessageDialog( null, "Usuario ou senha invalidos" );
                }
            }
    }//GEN-LAST:event_tfdPasswordKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] )
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() )
            {
                if ( "Nimbus".equals( info.getName() ) )
                {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        }
        catch ( ClassNotFoundException ex )
        {
            java.util.logging.Logger.getLogger( FrmLogin.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( InstantiationException ex )
        {
            java.util.logging.Logger.getLogger( FrmLogin.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( IllegalAccessException ex )
        {
            java.util.logging.Logger.getLogger( FrmLogin.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger( FrmLogin.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new FrmLogin().setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField tfdLogin;
    private javax.swing.JPasswordField tfdPassword;
    // End of variables declaration//GEN-END:variables
}
