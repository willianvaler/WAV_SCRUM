/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.ConexaoBD;
import model.User;
import DAOs.CombosDAO;
import DAOs.ItemDAO;
import DAOs.SprintDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.Sprint;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import parts.Backlog;
import parts.GraphicContent;
import parts.Tasks;
//import parts.BurndownContent;

/**
 *
 * @author WAV
 */
public class FrmLauncherApplication
        extends javax.swing.JFrame
{
    private User currentUser;
    Sprint currentSprint;
    private final SprintDAO sprintManager = new SprintDAO();
    private final ItemDAO itemTransaction = new ItemDAO();
    
    
    /**
     * FrmLauncherApplication
     * 
     */
    public FrmLauncherApplication()
    {
        setExtendedState(FrmLauncherApplication.MAXIMIZED_BOTH);
        setTitle( "SiMeScFP by WAV 1.0.9a" );
        setIconImage( loadImageIcon("/icons/icon-scrum.png").getImage() );
//        
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
        
        initComponents();
        
        CombosDAO combos = new CombosDAO();
        combos.popularCombo("sprint", cbxSprints);
        cbxSprints.setSelectedIndex( cbxSprints.getItemCount() -1 );
        
        currentSprint = sprintManager.getSprintByAlias( cbxSprints.getSelectedItem().toString() );
        
//        Backlog backlog = new Backlog( currentSprint );
//        backlog.setContent( pnlContent, currentSprint );
        
        GraphicContent graphic = new GraphicContent();
        graphic.setContent(pnlContent);
    }
    
    public void setSprints(){
        CombosDAO combos = new CombosDAO();
        combos.popularCombo("sprint", cbxSprints);
    }
    
    /**
     * setRestrictions
     * 
     */
    private void setRestrictions(){
        if ( currentUser.getUserType() == 5 )
        {
            menuRegisterItem.setEnabled( true );
            menuRegisterSprint.setEnabled( true );
            menuRegisterTask.setEnabled( true );
            menuRegisterTeam.setEnabled( true );
            menuRegisterUser.setEnabled( true );
            menuReportSprintItems.setEnabled( true );
            menuReportTeamUsers.setEnabled( true );
            menuReportUsers.setEnabled( true );
            menuAbout.setEnabled( true );
        }
        else if ( currentUser.getUserType() == 4 )
        {
            menuRegisterItem.setEnabled( true );
            menuRegisterSprint.setEnabled( true );
            menuRegisterTask.setEnabled( true );
            menuRegisterTeam.setEnabled( false );
            menuRegisterUser.setEnabled( false );
            menuReportSprintItems.setEnabled( true );
            menuReportTeamUsers.setEnabled( true );
            menuReportUsers.setEnabled( true );
            menuAbout.setEnabled( true );
        }
        else if ( currentUser.getUserType() == 3 )
        {
            menuRegisterItem.setEnabled( true );
            menuRegisterSprint.setEnabled( false );
            menuRegisterTask.setEnabled( true );
            menuRegisterTeam.setEnabled( false );
            menuRegisterUser.setEnabled( false );
            menuReportSprintItems.setEnabled( true );
            menuReportTeamUsers.setEnabled( false );
            menuReportUsers.setEnabled( false );
            menuAbout.setEnabled( true );
        }
        else if ( currentUser.getUserType() == 2 )
        {
            menuRegisterItem.setEnabled( false );
            menuRegisterSprint.setEnabled( false );
            menuRegisterTask.setEnabled( false );
            menuRegisterTeam.setEnabled( false );
            menuRegisterUser.setEnabled( false );
            menuReportSprintItems.setEnabled( false );
            menuReportTeamUsers.setEnabled( false );
            menuReportUsers.setEnabled( false );
            menuAbout.setEnabled( true );
        }
    }
    
    /**
     * loadImageIcon
     * 
     * @param path String
     * @return ImageIcon
     */
    private static ImageIcon loadImageIcon(String path) {
        URL imgURL = FrmLauncherApplication.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * setCurrentUser
     * 
     * @param user User
     * @ignored 
     */
    public void setCurrentUser ( User user )
    {
        this.currentUser = user;
        
         setRestrictions();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxSprints = new javax.swing.JComboBox();
        btnSprintSelector = new javax.swing.JButton();
        lblBacklog = new javax.swing.JLabel();
        lblTasks = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        lblGraphic = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuRegisterSprint = new javax.swing.JMenuItem();
        menuRegisterItem = new javax.swing.JMenuItem();
        menuRegisterTask = new javax.swing.JMenuItem();
        menuRegisterTeam = new javax.swing.JMenuItem();
        menuRegisterUser = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuReportUsers = new javax.swing.JMenuItem();
        menuReportTeamUsers = new javax.swing.JMenuItem();
        menuReportSprintItems = new javax.swing.JMenuItem();
        menuReportTasksbyTeam = new javax.swing.JMenuItem();
        menuReportTaskPeriod = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnSprintSelector.setText("Selecionar Sprint");
        btnSprintSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSprintSelectorActionPerformed(evt);
            }
        });

        lblBacklog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sprint.png"))); // NOI18N
        lblBacklog.setText("Backlog");
        lblBacklog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBacklog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblBacklog.setPreferredSize(new java.awt.Dimension(128, 128));
        lblBacklog.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblBacklog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBacklogMouseClicked(evt);
            }
        });

        lblTasks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/content_scrum2.png"))); // NOI18N
        lblTasks.setText("Tasks");
        lblTasks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTasks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTasks.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblTasks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTasksMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );

        lblGraphic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1304143762_shine_14.png"))); // NOI18N
        lblGraphic.setText("Gráfico");
        lblGraphic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblGraphic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblGraphic.setPreferredSize(new java.awt.Dimension(128, 128));
        lblGraphic.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblGraphic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGraphicMouseClicked(evt);
            }
        });

        jMenu1.setText("Arquivo");

        menuRegisterSprint.setText("Cadastrar Sprint");
        menuRegisterSprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegisterSprintActionPerformed(evt);
            }
        });
        jMenu1.add(menuRegisterSprint);

        menuRegisterItem.setText("Cadastrar Item");
        menuRegisterItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegisterItemActionPerformed(evt);
            }
        });
        jMenu1.add(menuRegisterItem);

        menuRegisterTask.setText("Cadastrar Tarefas");
        menuRegisterTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegisterTaskActionPerformed(evt);
            }
        });
        jMenu1.add(menuRegisterTask);

        menuRegisterTeam.setText("Cadastrar Equipe");
        menuRegisterTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegisterTeamActionPerformed(evt);
            }
        });
        jMenu1.add(menuRegisterTeam);

        menuRegisterUser.setText("Cadastrar Usuário");
        menuRegisterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegisterUserActionPerformed(evt);
            }
        });
        jMenu1.add(menuRegisterUser);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Trocar Usuário");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Visualizar");

        menuReportUsers.setText("Relatório de usuários");
        menuReportUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportUsersActionPerformed(evt);
            }
        });
        jMenu3.add(menuReportUsers);

        menuReportTeamUsers.setText("Relatório de usuários por equipe");
        menuReportTeamUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportTeamUsersActionPerformed(evt);
            }
        });
        jMenu3.add(menuReportTeamUsers);

        menuReportSprintItems.setText("Relatório de itens por sprint");
        menuReportSprintItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportSprintItemsActionPerformed(evt);
            }
        });
        jMenu3.add(menuReportSprintItems);

        menuReportTasksbyTeam.setText("Relatório de tarefas por equipe");
        menuReportTasksbyTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportTasksbyTeamActionPerformed(evt);
            }
        });
        jMenu3.add(menuReportTasksbyTeam);

        menuReportTaskPeriod.setText("Relatório de tarefas por período");
        menuReportTaskPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReportTaskPeriodActionPerformed(evt);
            }
        });
        jMenu3.add(menuReportTaskPeriod);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ajuda");

        menuAbout.setText("Sobre");
        menuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAboutActionPerformed(evt);
            }
        });
        jMenu4.add(menuAbout);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxSprints, 0, 983, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSprintSelector))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBacklog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTasks)
                            .addComponent(lblGraphic, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSprints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSprintSelector))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGraphic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblBacklog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTasks)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * menuRegisterSprintActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuRegisterSprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegisterSprintActionPerformed
        FrmRegisterSprint registerSprint = new FrmRegisterSprint();
        registerSprint.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        registerSprint.setVisible( true );
//        registerSprint.addWindowListener(new WindowAdapter() {  
//            @Override 
//            public void windowClosing(WindowEvent e) {  
//                dispose();
//                
//            }
//        });
        
//        registerSprint.isActive();
    }//GEN-LAST:event_menuRegisterSprintActionPerformed

    
    /**
     * menuRegisterTaskActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuRegisterTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegisterTaskActionPerformed
        FrmRegisterTask registerTask = new FrmRegisterTask();
        registerTask.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        registerTask.setVisible( true );
    }//GEN-LAST:event_menuRegisterTaskActionPerformed

    /**
     * menuRegisterUserActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuRegisterUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegisterUserActionPerformed
        FrmRegisterUser registeruser = new FrmRegisterUser();
        registeruser.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        registeruser.setVisible( true );
    }//GEN-LAST:event_menuRegisterUserActionPerformed

    /**
     * menuReportUsersActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuReportUsersActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuReportUsersActionPerformed
    {//GEN-HEADEREND:event_menuReportUsersActionPerformed
            // chamada de relatório, SEM parâmetros
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/usersReport.jrxml"));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            JasperExportManager.exportReportToPdfFile(impressao, "C:/Users/Willian/arquivo.pdf");
            
            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }//GEN-LAST:event_menuReportUsersActionPerformed

    /**
     * menuRegisterTeamActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuRegisterTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegisterTeamActionPerformed
        FrmRegisterTeam registerTeam = new FrmRegisterTeam();
        registerTeam.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        registerTeam.setVisible( true );
    }//GEN-LAST:event_menuRegisterTeamActionPerformed

    /**
     * menuReportTeamUsersActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuReportTeamUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportTeamUsersActionPerformed
        FrmReportUsersTeam reportuser = new FrmReportUsersTeam();
        reportuser.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        reportuser.setVisible( true );
    }//GEN-LAST:event_menuReportTeamUsersActionPerformed

    /**
     * menuReportSprintItemsActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuReportSprintItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportSprintItemsActionPerformed
       FrmReportSprintItems reportItems = new FrmReportSprintItems();
       reportItems.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       reportItems.setVisible(true);
    }//GEN-LAST:event_menuReportSprintItemsActionPerformed

    /**
     * btnSprintSelectorActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void btnSprintSelectorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSprintSelectorActionPerformed
    {//GEN-HEADEREND:event_btnSprintSelectorActionPerformed
        if ( cbxSprints.getSelectedIndex() == 0 )
        {
            JOptionPane.showMessageDialog( this, " É preciso selecionar um Sprint ");
        }
        else
        {
            Backlog backlog = new Backlog( currentSprint );
        backlog.setContent( pnlContent, currentSprint );

            currentSprint = sprintManager.getSprintByAlias( cbxSprints.getSelectedItem().toString() );
        }
        
    }//GEN-LAST:event_btnSprintSelectorActionPerformed

    /**
     * menuRegisterItemActionPerformed
     * 
     * @param evt java.awt.event.ActionEvent
     */
    private void menuRegisterItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegisterItemActionPerformed
        FrmRegisterItem itemRegister = new FrmRegisterItem();
        itemRegister.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        itemRegister.setVisible( true );
    }//GEN-LAST:event_menuRegisterItemActionPerformed

    /**
     * lblBacklogMouseClicked
     * 
     * @param evt java.awt.event.MouseEvent
     */
    private void lblBacklogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBacklogMouseClicked
        Backlog backlog = new Backlog( currentSprint );
        backlog.setContent( pnlContent, currentSprint );
    }//GEN-LAST:event_lblBacklogMouseClicked

    /**
     * lblTasksMouseClicked
     * 
     * @param evt java.awt.event.MouseEvent
     */
    private void lblTasksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTasksMouseClicked
       Tasks tasks = new Tasks( currentSprint );
        tasks.setContent( pnlContent,currentSprint );
    }//GEN-LAST:event_lblTasksMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        currentSprint = null;
        currentUser = null;
        
        new FrmLogin().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuAboutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuAboutActionPerformed
    {//GEN-HEADEREND:event_menuAboutActionPerformed
        JOptionPane.showMessageDialog( this, "Sistema de controle de tarefas de metodologia Scrum.");
    }//GEN-LAST:event_menuAboutActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
//        setSprints();
    }//GEN-LAST:event_formFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here: 
//        setSprints();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        setSprints();
    }//GEN-LAST:event_formWindowClosed

    private void menuReportTasksbyTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportTasksbyTeamActionPerformed
        FrmReportTaskByTeam reportTasks = new FrmReportTaskByTeam();
        reportTasks.setVisible(true);
        reportTasks.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_menuReportTasksbyTeamActionPerformed

    private void menuReportTaskPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReportTaskPeriodActionPerformed
        FrmReportTaskPeriod reportTasksPeriod = new FrmReportTaskPeriod();
        reportTasksPeriod.setVisible(true);
        reportTasksPeriod.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_menuReportTaskPeriodActionPerformed

    private void lblGraphicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGraphicMouseClicked
        GraphicContent graphic = new GraphicContent();
        graphic.setContent(pnlContent);
        
    }//GEN-LAST:event_lblGraphicMouseClicked
    
    
    
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
            java.util.logging.Logger.getLogger( FrmLauncherApplication.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( InstantiationException ex )
        {
            java.util.logging.Logger.getLogger( FrmLauncherApplication.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( IllegalAccessException ex )
        {
            java.util.logging.Logger.getLogger( FrmLauncherApplication.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger( FrmLauncherApplication.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                FrmLauncherApplication launcher =new FrmLauncherApplication();
                
                launcher.setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSprintSelector;
    private javax.swing.JComboBox cbxSprints;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblBacklog;
    private javax.swing.JLabel lblGraphic;
    private javax.swing.JLabel lblTasks;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuRegisterItem;
    private javax.swing.JMenuItem menuRegisterSprint;
    private javax.swing.JMenuItem menuRegisterTask;
    private javax.swing.JMenuItem menuRegisterTeam;
    private javax.swing.JMenuItem menuRegisterUser;
    private javax.swing.JMenuItem menuReportSprintItems;
    private javax.swing.JMenuItem menuReportTaskPeriod;
    private javax.swing.JMenuItem menuReportTasksbyTeam;
    private javax.swing.JMenuItem menuReportTeamUsers;
    private javax.swing.JMenuItem menuReportUsers;
    private javax.swing.JPanel pnlContent;
    // End of variables declaration//GEN-END:variables
}
