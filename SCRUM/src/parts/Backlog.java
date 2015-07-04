/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parts;

import DAOs.ItemDAO;
import DAOs.TaskDAO;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import model.Item;
import model.Sprint;
import model.Task;
import views.FrmRegisterItem;
import views.FrmRegisterTask;

/**
 *
 * @author wav
 */
public class Backlog
        extends javax.swing.JPanel
{
    ItemDAO itemManager = new ItemDAO();
    TaskDAO taskManager = new TaskDAO();
    Sprint sprint;
    
    /**
     * Creates new form Backlog
     */
//    public Backlog()
//    {
////        initComponents();
////         itemManager.setItemsByFilter(tblItemsBacklog, "");
//    }
    public Backlog(Sprint sprint){
        
        initComponents();
        tblItemsBacklog.removeAll();
        this.sprint = sprint;
//        itemManager.setItemsBySprintID(tblItemsBacklog, "", sprint);
//        taskManager.setAllTasks( tblTasksBacklog, sprint );
        refreshContent();
    }
    
    public Item getSelectedItem()
    {
        if ( tblItemsBacklog.getSelectedRow() >= 0 ) {
            return itemManager.getItemById( (Integer) tblItemsBacklog.getValueAt(tblItemsBacklog.getSelectedRow() , 0) );
        }
        return null;
    }
    public Task getSelectedTask()
    {
        if ( tblTasksBacklog.getSelectedRow() >= 0 ) {
            return taskManager.getTaskById((Integer) tblTasksBacklog.getValueAt(tblTasksBacklog.getSelectedRow() , 0) );
        }
        return null;
    }
    
    
    public void refreshContent()
    {
//        tblItemsBacklog.removeAll();
        itemManager.setItemsBySprintID(tblItemsBacklog, "", sprint);
        
        
        taskManager.setAllTasks( tblTasksBacklog, sprint );
    }
    
    /**
     * setContent
     * 
     * @param panel JPanel
     */
    public void setContent( JPanel panel, Sprint sprint )
    {
        this.sprint = sprint;
        panel.removeAll();
//        
//        itemManager.setItemsByFilter(tblItemsBacklog, "");
//        taskManager.setAllTasks(tblTasksBacklog, sprint);
        
        Backlog backlog = new Backlog( sprint );
        backlog.setVisible( true );
        backlog.setBounds( 0, 0, panel.getWidth(), panel.getHeight() );
        
        panel.add( backlog );
        
        
        panel.revalidate();
        panel.repaint();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemsBacklog = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTasksBacklog = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        tblItemsBacklog.setModel(new javax.swing.table.DefaultTableModel(
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
        tblItemsBacklog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsBacklogMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemsBacklog);

        tblTasksBacklog.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTasksBacklog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTasksBacklogMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTasksBacklog);

        jLabel1.setText("TAREFAS");

        jLabel6.setText("ITEMS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblItemsBacklogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsBacklogMouseClicked
        
        Point p = evt.getPoint();
        int row = tblItemsBacklog.rowAtPoint(p);
        if ( evt.getClickCount() == 2  ) {
            if (tblItemsBacklog.getSelectedRow() >= 0 ) {
                
                Item item = getSelectedItem();
                FrmRegisterItem registerItem = new FrmRegisterItem();
                registerItem.setVisible(true);
                registerItem.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                
                registerItem.setCurrentItem(item);
                registerItem.editContent();
            }
        }
        
        else
        {
            Item item = getSelectedItem();
            taskManager.setTasksByItem(tblTasksBacklog, sprint, item);
        }
    }//GEN-LAST:event_tblItemsBacklogMouseClicked

    private void tblTasksBacklogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTasksBacklogMouseClicked
        Point p = evt.getPoint();
        
        int row = tblItemsBacklog.rowAtPoint(p);
        
        if ( evt.getClickCount() == 2  ) {
            if (tblTasksBacklog.getSelectedRow() >= 0) {
                Task task = getSelectedTask();
                FrmRegisterTask registerTask = new FrmRegisterTask();
                registerTask.setVisible(true);
                registerTask.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                
                registerTask.setCurrentTask(task);
                registerTask.editContent();
            }
        }
    }//GEN-LAST:event_tblTasksBacklogMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblItemsBacklog;
    private javax.swing.JTable tblTasksBacklog;
    // End of variables declaration//GEN-END:variables
}