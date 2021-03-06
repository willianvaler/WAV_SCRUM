/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Team;
import model.User;
import DAOs.TeamDAO;
import DAOs.UserDAO;

/**
 *
 * @author willian
 */
public class FrmRegisterTeam
        extends javax.swing.JFrame
{
    TeamDAO teamManager = new TeamDAO();
    UserDAO userManager = new UserDAO();
    int id;
    /**
     * Creates new form FrmRegisterTeam
     */
    public FrmRegisterTeam()
    {
        setIconImage( loadImageIcon("/icons/icon-scrum.png").getImage() );
        initComponents();
        setTitle( "Cadastro de Equipe" );
        teamManager.setItensByFilter( tblTeam, "" );
        userManager.setItensByFilter( tblScrum, "" );
        id = 0;
//        setIconImage(null);
    }
    
    private static ImageIcon loadImageIcon(String path) {
        URL imgURL = FrmRegisterTeam.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    public void clear()
    {
        tfdName.setText( "" );
        tfdScrum.setText( "" );
        userManager.setItensByFilter( tblScrum, "" );
        
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

        tbdTeam = new javax.swing.JTabbedPane();
        pnlRegister = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfdName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfdScrum = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblScrum = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        pnlEdit = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdSearchTeam = new javax.swing.JTextField();
        btnSearchTeam = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTeam = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbdTeam.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                tbdTeamStateChanged(evt);
            }
        });

        jLabel2.setText("Nome da Equipe");

        jLabel3.setText("Selecionar Scrum Master");

        tfdScrum.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                tfdScrumKeyPressed(evt);
            }
        });

        btnSearch.setText("Pesquisar");
        btnSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSearchActionPerformed(evt);
            }
        });

        tblScrum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblScrum);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("*");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("*");

        javax.swing.GroupLayout pnlRegisterLayout = new javax.swing.GroupLayout(pnlRegister);
        pnlRegister.setLayout(pnlRegisterLayout);
        pnlRegisterLayout.setHorizontalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(88, 88, 88)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfdScrum, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(tfdName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addGap(60, 60, 60))
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
        );
        pnlRegisterLayout.setVerticalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfdScrum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch)
                        .addComponent(jLabel32)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tbdTeam.addTab("Cadastrar", pnlRegister);

        jLabel1.setText("Pesquisar Equipe");

        tfdSearchTeam.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                tfdSearchTeamKeyPressed(evt);
            }
        });

        btnSearchTeam.setText("Pesquisar");
        btnSearchTeam.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSearchTeamActionPerformed(evt);
            }
        });

        tblTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTeam);

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(tfdSearchTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(btnSearchTeam)
                .addGap(51, 51, 51))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdSearchTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchTeam))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tbdTeam.addTab("Editar", pnlEdit);

        btnNew.setText("Novo");
        btnNew.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("Salvar");
        btnSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSaveActionPerformed(evt);
            }
        });

        btnDel.setText("Excluir");
        btnDel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDelActionPerformed(evt);
            }
        });

        btnClose.setText("Fechar");
        btnClose.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbdTeam)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbdTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnDel)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(btnClose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnNewActionPerformed
    {//GEN-HEADEREND:event_btnNewActionPerformed
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEditActionPerformed
    {//GEN-HEADEREND:event_btnEditActionPerformed
        if ( tblTeam.getSelectedRow() >= 0 )
        {
            id = (Integer) tblTeam.getValueAt( tblTeam.getSelectedRow(), 0);
            tfdName.setText( (String) tblTeam.getValueAt( tblTeam.getSelectedRow(), 1) );
            int scrumId = Integer.parseInt( (String) tblTeam.getValueAt( tblTeam.getSelectedRow(), 3) ) ;
            User u = userManager.getUserbyId( scrumId );
            tfdScrum.setText( u.getName() );

            tbdTeam.setSelectedIndex( 0 );
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Por favor selecione um registro");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSaveActionPerformed
    {//GEN-HEADEREND:event_btnSaveActionPerformed
        boolean result= false, validate = false;
        Team team = new Team();
        int scrumId = 0;
        if ( !tfdName.getText().equals( "" ) && tblScrum.getSelectedRow() >= 0 )
        {
            team.setName( tfdName.getText() );
            scrumId  = (Integer) tblScrum.getValueAt( tblScrum.getSelectedRow(), 0);
            team.setTotalParticipants( 1 );
            team.setScrumMaster( scrumId );
            validate = true;
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Os campos: Nome não pode estar em branco e é preciso selecionar um Scrum Master" );
        }
        

        boolean validateDuplicatedTeam = teamManager.validateDuplicatedTeamByName(tfdName.getText() );
        if ( !validateDuplicatedTeam && id == 0 ) {
            JOptionPane.showMessageDialog( this, "Equipe já existe" );
            validateDuplicatedTeam = false;
        }
        
        if( validate && id == 0 && validateDuplicatedTeam )
        {
            result = teamManager.insertTeam(team, scrumId );
            teamManager.setItensByFilter(tblTeam, "");
            clear();
        }
        else if ( id > 0 )
        {
            result = teamManager.updateTeam(id, team);
            teamManager.setItensByFilter(tblTeam, "");
            clear();
        }
        else
        {
            JOptionPane.showMessageDialog( rootPane, "Erro ao inserir ou atualizar equipe.");
        }
        
        
        if ( result && id == 0 ) 
        {
            JOptionPane.showMessageDialog( rootPane, "Equipe inserida com sucesso." );
        }
        else if (id > 0 ) 
        {
            JOptionPane.showMessageDialog(this, "Equipe atualizada atualizado com sucesso" );
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Erro ao inserir ou atualiza Equipe");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDelActionPerformed
    {//GEN-HEADEREND:event_btnDelActionPerformed

        if ( tblTeam.getSelectedRow() >= 0 )
        {
            int id = (Integer) tblTeam.getValueAt( tblTeam.getSelectedRow(), 0);

            if ( teamManager.deleteTeam( id ) )
            {
                JOptionPane.showMessageDialog( null, "Registro excuido.");
                teamManager.setItensByFilter( tblTeam, "" );
            }
            else
            {
                JOptionPane.showMessageDialog( null, "Erro ao deletar usuário." );
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Por favor selecione um registro");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCloseActionPerformed
    {//GEN-HEADEREND:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSearchTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTeamActionPerformed
        teamManager.setItensByFilter( tblTeam, tfdSearchTeam.getText() );
    }//GEN-LAST:event_btnSearchTeamActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        userManager.setItensByFilter( tblScrum, tfdScrum.getText() );
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbdTeamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbdTeamStateChanged
        if ( tbdTeam.getSelectedIndex() == 0) {
            btnDel.setEnabled(false);
        } else {
            btnDel.setEnabled(true);
        }
    }//GEN-LAST:event_tbdTeamStateChanged

    private void tfdScrumKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfdScrumKeyPressed
    {//GEN-HEADEREND:event_tfdScrumKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            userManager.setItensByFilter( tblScrum, tfdScrum.getText() );
        }
    }//GEN-LAST:event_tfdScrumKeyPressed

    private void tfdSearchTeamKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfdSearchTeamKeyPressed
    {//GEN-HEADEREND:event_tfdSearchTeamKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            teamManager.setItensByFilter( tblTeam, tfdSearchTeam.getText() );
        }
    }//GEN-LAST:event_tfdSearchTeamKeyPressed

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
            java.util.logging.Logger.getLogger( FrmRegisterTeam.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( InstantiationException ex )
        {
            java.util.logging.Logger.getLogger( FrmRegisterTeam.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( IllegalAccessException ex )
        {
            java.util.logging.Logger.getLogger( FrmRegisterTeam.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger( FrmRegisterTeam.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new FrmRegisterTeam().setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchTeam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlRegister;
    private javax.swing.JTabbedPane tbdTeam;
    private javax.swing.JTable tblScrum;
    private javax.swing.JTable tblTeam;
    private javax.swing.JTextField tfdName;
    private javax.swing.JTextField tfdScrum;
    private javax.swing.JTextField tfdSearchTeam;
    // End of variables declaration//GEN-END:variables
}
