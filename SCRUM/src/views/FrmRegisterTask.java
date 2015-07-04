/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Item;
import model.Sprint;
import model.Task;
import DAOs.CombosDAO;
import DAOs.ItemDAO;
import DAOs.SprintDAO;
import DAOs.TaskDAO;
import DAOs.UserDAO;

/**
 *
 * @author willian
 */
public class FrmRegisterTask
        extends javax.swing.JFrame
{
    private static SprintDAO sprintManager = new SprintDAO();
    private static ItemDAO itemManager = new ItemDAO();
    private static TaskDAO taskManager = new TaskDAO();
    private static UserDAO userManager = new UserDAO();
    private  Task currentTask = new Task();
    int id;
    /**
     * Creates new form FrmRegisterTask
     */
    public FrmRegisterTask()
    {
        setIconImage( loadImageIcon("/icons/icon-scrum.png").getImage() );
        initComponents();
        setTitle( "Cadastro de Tarefas" );
        CombosDAO combos = new CombosDAO();
        combos.popularCombo("sprint", cbxSprint );
        combos.popularCombo("sprint_items", cbxItem );
        combos.popularCombo("scrum_user", cbxUser);
        
        taskManager.setTasksByFilter(tblTask, "");
    }
    
    public void setCurrentTask( Task task )
    {
        this.currentTask = task;
    }
    
    public void editContent()
    {
        id = currentTask.getIdTask();

            tfdTitle.setText( currentTask.getTitle() );
            txaInfo.setText( currentTask.getInfo() );
            String classification = currentTask.getClassification();
            String type = currentTask.getItemType();
            String situation = currentTask.getSituation();
//            String status = currentTask.get;
            tfdEstimated.setText( currentTask.getEstimated() );
            tfdDuration.setText( currentTask.getDuration() );
            
            ftfProcessStarted.setText( currentTask.getProccessStarted() );
            ftfProcessFinished.setText( currentTask.getProccessFinished() );
            
            for ( int i = 0; i < cbxSprint.getItemCount()  ; i++ )
            {
                if ( cbxSprint.getItemAt( i ).toString().equals( currentTask.getSprint().getAlias() ) )
                {
                    cbxSprint.setSelectedIndex( i );
                }
                
            }
            Item item = itemManager.getItemById( currentTask.getRefItem() );
            for ( int i = 0; i < cbxItem.getItemCount() ; i++ )
            {
                if ( cbxItem.getItemAt( i ).toString().equals( item.getTitle() ) )
                {
                    cbxItem.setSelectedIndex( i );
                }
                
            }
            //item classification
            if ( classification.equals("Baixo") ) {
                cbxClassification.setSelectedIndex( 0 );
            }
            else if ( classification.equals("Médio") ) {
                cbxClassification.setSelectedIndex( 1 );
            }
            else if ( classification.equals("Alto") ) {
                cbxClassification.setSelectedIndex( 2 );
            }
            else
            {
                cbxClassification.setSelectedIndex( 3 );
            }
            
            //item status
            if ( currentTask.getStatus() == 1 ) {
                cbxStatus.setSelectedIndex( 0 );
            }
            else if ( currentTask.getStatus() == 2 ) {
                cbxStatus.setSelectedIndex( 1 );
            }
            else{
                cbxStatus.setSelectedIndex( 2 );
            }
            
            //item type
            if ( type.equals( "Planejado" ) ) {
                cbxType.setSelectedIndex( 0 );
            }
            else{
                cbxType.setSelectedIndex( 1 );
            }
            
            for (int i = 0; i < cbxUser.getItemCount(); i++) {
                if (cbxUser.getItemAt(i).toString().equals( currentTask.getTaskuser().getName() ) ) {
                    cbxUser.setSelectedIndex(i);
                }
            }
            
            if ( situation.equals("Em pausa") ) {
                cbxSituation.setSelectedIndex(0);
            }
            
            else
            {
                cbxSituation.setSelectedIndex(1);
            }   
            
            tbdTask.setSelectedIndex( 0 );
    }
    
    private static ImageIcon loadImageIcon(String path) {
        URL imgURL = FrmRegisterTask.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    public void clear()
    {
        tfdDuration.setText("");
        tfdEstimated.setText("");
        tfdSearch.setText("");
        tfdTitle.setText("");
        ftfProcessFinished.setText("");
        ftfProcessStarted.setText("");
        
        txaInfo.setText("");
        cbxClassification.setSelectedIndex( 0 );
        cbxSprint.setSelectedIndex( 0 );
        cbxStatus.setSelectedIndex( 0 );
        cbxType.setSelectedIndex( 0 );
        cbxItem.setSelectedIndex( 0 );
        cbxSituation.setSelectedIndex(0);
        cbxUser.setSelectedIndex(0);
        
        id = 0;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbdTask = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxSprint = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbxClassification = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tfdTitle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaInfo = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        cbxType = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbxStatus = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ftfProcessStarted = new javax.swing.JFormattedTextField();
        ftfProcessFinished = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        cbxItem = new javax.swing.JComboBox();
        tfdEstimated = new javax.swing.JTextField();
        tfdDuration = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbxSituation = new javax.swing.JComboBox();
        cbxUser = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTask = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbdTask.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbdTaskStateChanged(evt);
            }
        });

        jLabel2.setText("Sprint:");

        cbxSprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSprintActionPerformed(evt);
            }
        });

        jLabel3.setText("Classificação:");

        cbxClassification.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baixo", "Médio", "Alto", "Muito Alto" }));

        jLabel4.setText("Título:");

        jLabel5.setText("Descrição:");

        txaInfo.setColumns(20);
        txaInfo.setRows(5);
        jScrollPane2.setViewportView(txaInfo);

        jLabel6.setText("Tipo:");

        cbxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Planejado", "Speed Lane" }));

        jLabel7.setText("Estado:");

        cbxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TO DO", "IN PROGRESS", "DONE" }));

        jLabel8.setText("Estimado (em minutos):");

        jLabel9.setText("Duração (em minutos):");

        jLabel10.setText("Início (dd/mm/yyyy):");

        jLabel11.setText("Fim (dd/mm/yyyy):");

        ftfProcessStarted.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        ftfProcessFinished.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel12.setText("Item:");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("*");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("*");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("*");

        jLabel22.setText("Situação: ");

        jLabel23.setText("Responsável");

        cbxSituation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Em pausa", "Iniciado" }));

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxSprint, 0, 323, Short.MAX_VALUE)
                    .addComponent(cbxClassification, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfdTitle)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(cbxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxItem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbxUser, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSituation, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ftfProcessStarted, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ftfProcessFinished, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfdEstimated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxSprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel22)
                    .addComponent(cbxSituation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel23)
                    .addComponent(cbxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(tfdTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdEstimated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(tfdDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(ftfProcessStarted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(ftfProcessFinished, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17)))))
                            .addComponent(jLabel16))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbxItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        tbdTask.addTab("Cadastrar", jPanel1);

        jLabel1.setText("Pesquisar");

        btnSearch.setText("Pesquisar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tblTask.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblTask);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(109, 109, 109)
                .addComponent(tfdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
        );

        tbdTask.addTab("Editar", jPanel2);

        btnNew.setText("Novo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("Salvar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDel.setText("Excluir");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnClose.setText("Fechar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbdTask)
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
                .addComponent(tbdTask, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnDel)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(btnClose))
                .addGap(0, 12, Short.MAX_VALUE))
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
         if ( tblTask.getSelectedRow() >= 0 )
        {
            id = (Integer) tblTask.getValueAt( tblTask.getSelectedRow(), 0);

            tfdTitle.setText((String) tblTask.getValueAt( tblTask.getSelectedRow() , 1)  );
            txaInfo.setText( (String) tblTask.getValueAt( tblTask.getSelectedRow() , 2) );
            String classification = (String) tblTask.getValueAt( tblTask.getSelectedRow() , 3);
            String type = (String) tblTask.getValueAt( tblTask.getSelectedRow() , 4);
            String status = (String) tblTask.getValueAt( tblTask.getSelectedRow() , 5);
            tfdEstimated.setText( String.valueOf( (Integer) tblTask.getValueAt( tblTask.getSelectedRow() , 6) ) );
            tfdDuration.setText( String.valueOf( (Integer) tblTask.getValueAt( tblTask.getSelectedRow() , 7) ) );
            
            ftfProcessStarted.setText( (String) tblTask.getValueAt( tblTask.getSelectedRow() , 8) );
            ftfProcessFinished.setText( ( (String) tblTask.getValueAt( tblTask.getSelectedRow() , 9) ).equals("n/d") ? "" : (String) tblTask.getValueAt( tblTask.getSelectedRow() , 9) );
            
            for ( int i = 0; i < cbxSprint.getItemCount()  ; i++ )
            {
                if ( cbxSprint.getItemAt( i ).toString().equals( (String) tblTask.getValueAt( tblTask.getSelectedRow(), 10)) )
                {
                    cbxSprint.setSelectedIndex( i );
                }
                
            }
            
            for ( int i = 0; i < cbxItem.getItemCount() ; i++ )
            {
                if ( cbxItem.getItemAt( i ).toString().equals( (String) tblTask.getValueAt( tblTask.getSelectedRow(), 11)) )
                {
                    cbxItem.setSelectedIndex( i );
                }
                
            }
            //item classification
            if ( classification.equals("Baixo") ) {
                cbxClassification.setSelectedIndex( 0 );
            }
            else if ( classification.equals("Médio") ) {
                cbxClassification.setSelectedIndex( 1 );
            }
            else if ( classification.equals("Alto") ) {
                cbxClassification.setSelectedIndex( 2 );
            }
            else
            {
                cbxClassification.setSelectedIndex( 3 );
            }
            
            //item status
            if ( status.equals( "TO DO" ) ) {
                cbxStatus.setSelectedIndex( 0 );
            }
            else if ( status.equals( "IN PROGRESS" ) ) {
                cbxStatus.setSelectedIndex( 1 );
            }
            else{
                cbxStatus.setSelectedIndex( 2 );
            }
            
            //item type
            if ( type.equals( "Planejado" ) ) {
                cbxType.setSelectedIndex( 0 );
            }
            else{
                cbxType.setSelectedIndex( 1 );
            }
            
            for (int i = 0; i < cbxUser.getItemCount(); i++) {
                if (cbxUser.getItemAt(i).toString().equals( (String) tblTask.getValueAt(tblTask.getSelectedRow(), 12) ) ) {
                    cbxUser.setSelectedIndex(i);
                }
            }
            
            String situation = (String) tblTask.getValueAt(tblTask.getSelectedRow(), 13);
            
            if ( situation.equals("Em pausa") ) {
                cbxSituation.setSelectedIndex(0);
            }
            
            else
            {
                cbxSituation.setSelectedIndex(1);
            }   
            
            tbdTask.setSelectedIndex( 0 );
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Por favor selecione um registro");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSaveActionPerformed
    {//GEN-HEADEREND:event_btnSaveActionPerformed
       Task task = new Task();

        boolean result= false, validate = false;
        //campos obrigatorios
        if ( !(tfdTitle.getText().isEmpty()) && !(txaInfo.getText().equals(""))
                && cbxSprint.getSelectedIndex() != 0 && cbxItem.getSelectedIndex() != 0 
                && cbxUser.getSelectedIndex() != 0 )
        {
            Sprint sprint =  sprintManager.getSprintByAlias( cbxSprint.getSelectedItem().toString() );
            Item item =  itemManager.getItemByTitle( cbxItem.getSelectedItem().toString() );
            item.setSprint( sprint );
            
            task.setSprint( sprint );
            task.setRefItem( item.getIdItem() );
            task.setTitle( tfdTitle.getText() );
            task.setInfo( txaInfo.getText() );
            task.setSprint( sprint );
            task.setTaskuser( userManager.getUserName( cbxUser.getSelectedItem().toString() ) );
            task.setSituation( cbxSituation.getSelectedItem().toString() );
            
            if (!tfdDuration.getText().isEmpty()) {
                task.setDuration( tfdDuration.getText() );
            }
            else
            {
                task.setDuration( null );
                
            }
            task.setEstimated( tfdEstimated.getText() );
            task.setProccessStarted( ftfProcessStarted.getText() );
            
            if (!ftfProcessFinished.getText().isEmpty()) {
                task.setProccessFinished( ftfProcessFinished.getText() );
            }
            else
            {
                task.setProccessFinished( null );
                
            }
            
            //seta o status do item
            if ( cbxStatus.getSelectedIndex() == 0 ) {
                task.setStatus( 1 );
            }
            
            else if( cbxStatus.getSelectedIndex() == 1 )
            {
                task.setStatus( 2 );
            }
            
            else
            {
                task.setStatus( 3 );
            }
     
            //seta a classificação do item
            if ( cbxClassification.getSelectedIndex() == 0 ) 
            {
                task.setClassification( "Baixo" );
            }
            
            else if ( cbxClassification.getSelectedIndex() == 1 ) 
            {
                task.setClassification( "Médio" );
            }
            
            else if ( cbxClassification.getSelectedIndex() == 0 ) 
            {
                task.setClassification( "Alto" );
            }
            
            else
            {
                task.setClassification( "Muito Alto" );
            }
            
            //seta o tipo do item
            if ( cbxType.getSelectedIndex() == 0 ) 
            {
                task.setItemType("Planejado" );
            }
            
            else
            {
                task.setItemType( "Speed Lane" );
            }
            validate = true;
        }
        
        else
        {
            JOptionPane.showMessageDialog( this, "Preencha todos os campos obrigatórios" );
            validate = false;
        }

        if ( validate && id == 0 )
        {
            result = taskManager.insertTask( task );
            taskManager.setTasksByFilter(tblTask, "" );
        }
        else if( validate && id > 0 )
        {
            result = taskManager.updateTask( id , task );
            taskManager.setTasksByFilter(tblTask, "" );
        }

        if( validate && result && id == 0)
        {
            JOptionPane.showMessageDialog( rootPane, "Tarefa inserida com sucesso." );
            clear();
        }
        else if ( validate && result && id > 0 )
        {
            JOptionPane.showMessageDialog(this, "Tarefa atualizada com sucesso" );
            clear();
        }
        else
        {
            JOptionPane.showMessageDialog( rootPane, "Erro ao inserir ou atualizar tarefa.");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDelActionPerformed
    {//GEN-HEADEREND:event_btnDelActionPerformed

       if ( tblTask.getSelectedRow() >= 0 )
        {
            int id = (Integer) tblTask.getValueAt( tblTask.getSelectedRow(), 0);

            if ( itemManager.deleteItem(id ) )
            {
                JOptionPane.showMessageDialog( null, "Registro excluido.");
                itemManager.setItemsByFilter(tblTask, "" );
            }
            else
            {
                JOptionPane.showMessageDialog( null, "Erro ao deletar tarefa." );
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

    private void tbdTaskStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbdTaskStateChanged
        if ( tbdTask.getSelectedIndex() == 0 ) {
            btnDel.setEnabled( false );
        }
        else {
            btnDel.setEnabled( true );
        }
    }//GEN-LAST:event_tbdTaskStateChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
       taskManager.setTasksByFilter( tblTask, tfdSearch.getText() );
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbxSprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSprintActionPerformed
        if (cbxSprint.getSelectedIndex() != 0) {
            cbxItem.removeAllItems();
            CombosDAO combos = new CombosDAO();
            Sprint sprint = sprintManager.getSprintByAlias( cbxSprint.getSelectedItem().toString() );
            combos.popularCombo("sprint_items", cbxItem , sprint.getIdSprint() );
        }
    }//GEN-LAST:event_cbxSprintActionPerformed

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
            java.util.logging.Logger.getLogger( FrmRegisterTask.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( InstantiationException ex )
        {
            java.util.logging.Logger.getLogger( FrmRegisterTask.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( IllegalAccessException ex )
        {
            java.util.logging.Logger.getLogger( FrmRegisterTask.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        catch ( javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger( FrmRegisterTask.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new FrmRegisterTask().setVisible( true );
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
    private javax.swing.JComboBox cbxClassification;
    private javax.swing.JComboBox cbxItem;
    private javax.swing.JComboBox cbxSituation;
    private javax.swing.JComboBox cbxSprint;
    private javax.swing.JComboBox cbxStatus;
    private javax.swing.JComboBox cbxType;
    private javax.swing.JComboBox cbxUser;
    private javax.swing.JFormattedTextField ftfProcessFinished;
    private javax.swing.JFormattedTextField ftfProcessStarted;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tbdTask;
    private javax.swing.JTable tblTask;
    private javax.swing.JTextField tfdDuration;
    private javax.swing.JTextField tfdEstimated;
    private javax.swing.JTextField tfdSearch;
    private javax.swing.JTextField tfdTitle;
    private javax.swing.JTextArea txaInfo;
    // End of variables declaration//GEN-END:variables
}
