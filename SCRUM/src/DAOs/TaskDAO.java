/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

/**
 *
 * @author willian
 */

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ConexaoBD;
import model.Item;
import model.Sprint;
import model.Task;
import model.User;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.data.gantt.TaskSeries;

public class TaskDAO
{
    ResultSet result;
    int insertCode;
    LogDAO log = new LogDAO();
    ItemDAO itemManager = new ItemDAO();
    SprintDAO sprintManager = new SprintDAO();
    UserDAO userManager = new UserDAO();
    
    
    
    public String dateFormat( String date )
    {
        SimpleDateFormat outputFormat = new SimpleDateFormat( "dd/MM/yyyy" );
        SimpleDateFormat inputFormat = new SimpleDateFormat( "yyyy-MM-dd" );

        String date1 = (date);
        Date dt = null;
        try
        {
            dt = inputFormat.parse( date1 );
            return outputFormat.format( dt );
        }
        catch ( ParseException ex )
        {
            log.createlog( "Parse Date Error ", ex.getMessage(), " Erro de conversão de data");
            return null;
        }
    }
    
    /**
     * insertTask
     * 
     * @param task
     * @return 
     */
    public boolean insertTask( Task task )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "INSERT INTO sprint_tasks VALUES (DEFAULT " +
                            ", '" + task.getTitle() + 
                            "', '" + task.getInfo() +
                            "', " + task.getRefItem() + 
                            ", '" + task.getClassification() +
                            "', '" + task.getItemType()+ 
                            "', " + task.getStatus() + 
                            ", " + task.getEstimated()+ 
                            ", " + task.getDuration()+ 
                            ", '" + task.getProccessStarted()+ 
                            "', " + ( task.getProccessFinished() == null || task.getProccessFinished().equals("null") ? null : task.getProccessFinished() )+ 
                            ", " + task.getSprint().getIdSprint() +  
                            ", " + task.getTaskuser().getId() +
                            ", '" + task.getSituation() +
                            "' ) returning idsprint_tasks";
            System.out.println(sql);
            result = st.executeQuery( sql );
            result.next();
            System.out.println("Código retornado: " + result.getInt(1));
            log.createlog( "Insert Task", "", sql);
            insertCode = result.getInt(1);
            return true;
        }
        catch( Exception e )
        {
//            JOptionPane.showMessageDialog( null, "Erro ao inserir tarefa");
            log.createlog( "Task Insert Error", e.getMessage(), sql);
            return false;
        }
    }
	
    /**
     * updateTask
     * 
     * @param id
     * @param task
     * @return 
     */
	public boolean updateTask( int id, Task task )
	{
            String sql = "";
		try
		{
			Statement st = scrum.SCRUM.conexao.createStatement();
                        
			sql = "UPDATE sprint_tasks SET " + 
                                " ref_item = " + task.getRefItem() + 
                                ", classification = '" + task.getClassification() + 
                                "', title = '" + task.getTitle() +
                                "', info = '" + task.getInfo() +  
                                "', item_type = '" + task.getItemType() +
                                "', status = " + task.getStatus() + 
                                ", estimated = " + task.getEstimated() + 
                                ", duration = " + task.getDuration() + 
                                ", proccess_started = '" + task.getProccessStarted()+ 
                                "', proccess_finished = " + ( task.getProccessFinished() == null || task.getProccessFinished().equals("null") ? null : " '" + task.getProccessFinished() + "' " )+ 
                                ", idsprint = " + task.getSprint().getIdSprint() +
                                ", scrum_user = " + task.getTaskuser().getId() +
                                ", status2 = '" + task.getSituation() +
                                "' WHERE idsprint_tasks = " + id;
                        System.out.println(sql);
			int res = st.executeUpdate( sql );
                        log.createlog( "Update Task", "", sql);
                        updateTime( itemManager.getItemById(task.getRefItem()) , task);
                        return true;
		}
		catch( Exception e )
		{
//                    JOptionPane.showMessageDialog( null, "Erro ao atualizar tarefa");
                    log.createlog( "Task Update Error", e.getMessage(), sql);
			return false;
		}
	}
	
	public boolean deleteTask( int id )
	{
            String sql = "";
		try
		{
                    Statement st = scrum.SCRUM.conexao.createStatement();

                    sql = "DELETE FROM sprint_tasks WHERE idsprint_taks = " + id;
                    
                    System.out.println( sql );
                    log.createlog( "Delete Task", "", sql);
                    int result = st.executeUpdate(sql );
                    
            return true;
		}
		catch( Exception e )
		{
                    log.createlog( "Task Delete Error", e.getMessage(), sql);
//                    JOptionPane.showMessageDialog( null, "Erro ao deletar tarefa");
                    return false;
		}
	}
        
    /**
     * setTasksByFilter
     * 
     * @param table
     * @param search 
     */
    public void setTasksByFilter( JTable table, String search )
    {
        String status = "";
         
        Object [][] itemsTable = null;
        
        Object[] header = new Object[ 14 ];
        header[0] = "ID";
        header[1] = "Título";
        header[2] = "Info";
        header[3] = "Classificação";
        header[4] = "Tipo";
        header[5] = "Estado";
        header[6] = "Estimado(minutos)";
        header[7] = "Duração(minutos)";
        header[8] = "Início";
        header[9] = "Fim";
        header[10] = "Sprint";
        header[11] = "Item";
        header[12] = "Responsável";
        header[13] = "Situação";
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_tasks WHERE title LIKE '%" + search + "%' ";
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 14 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar tarefas\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_tasks WHERE title LIKE '%" + search + "%'";
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_tasks" );
                itemsTable[i][1] = result.getString( "title" );
                itemsTable[i][2] = result.getString( "info" );
                itemsTable[i][3] = result.getString( "classification" );
                itemsTable[i][4] = result.getString( "item_type" );
                
                if ( result.getInt("status" ) == 1 ) {
                    status = "TO DO";
                }
                else if (result.getInt("status" ) == 2) {
                    status = "IN PROGRESS";
                }
                else if (result.getInt("status" ) == 3) {
                    status =  "DONE";
                }
                else
                {
                    status = "n/d";
                }
                itemsTable[i][5] = status;
                itemsTable[i][6] = result.getInt( "estimated" );
                itemsTable[i][7] = result.getInt( "duration" );
                itemsTable[i][8] = dateFormat( result.getString( "proccess_started" ) );
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" :dateFormat( result.getString( "proccess_finished" ) ) );
                Sprint sprint = sprintManager.getSprintById( result.getInt( "idsprint" ) );
                itemsTable[i][10] = sprint.getAlias();
                Item item = itemManager.getItemById( result.getInt( "ref_item" ) );
                itemsTable[i][11] = item.getTitle();
                User user = userManager.getUserbyId(result.getInt("scrum_user") );
                itemsTable[i][12] = user.getName();
                itemsTable[i][13] = result.getString("status2");

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de tarefas", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de tarefas.\n" + e );
        }

        table.setModel( new DefaultTableModel( itemsTable, header )
        {
            @Override
            public boolean isCellEditable( int row, int column )
            {
                return false;
            }
        } );
    }
    
    /**
     * setTasks
     * 
     * @param table
     * @param idStatus
     * @param sprint 
     */
     public void setTasks( JTable table, int idStatus, Sprint sprint )
    {
        String status = "";
        
        Object [][] itemsTable = null;
        
        Object[] header = new Object[ 14 ];
        header[0] = "ID";
        header[1] = "Título";
        header[2] = "Info";
        header[3] = "Classificação";
        header[4] = "Tipo";
        header[5] = "Estado";
        header[6] = "Estimado(minutos)";
        header[7] = "Duração(minutos)";
        header[8] = "Início";
        header[9] = "Fim";
        header[10] = "Sprint";
        header[11] = "Item";
        header[12] = "Responsável";
        header[13] = "Situação";
        
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_tasks WHERE status = " + idStatus + " AND idsprint = " + sprint.getIdSprint();
            System.out.println(sql);
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 14 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar tarefas\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_tasks WHERE status = " + idStatus + " AND idsprint = " + sprint.getIdSprint();
            System.out.println(sql);
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_tasks" );
                itemsTable[i][1] = result.getString( "title" );
                itemsTable[i][2] = result.getString( "info" );
                itemsTable[i][3] = result.getString( "classification" );
                itemsTable[i][4] = result.getString( "item_type" );
                
                if ( result.getInt("status" ) == 1 ) {
                    status = "TO DO";
                }
                else if (result.getInt("status" ) == 2) {
                    status = "IN PROGRESS";
                }
                else if (result.getInt("status" ) == 3) {
                    status =  "DONE";
                }
                else
                {
                    status = "n/d";
                }
                itemsTable[i][5] = status;
                itemsTable[i][6] = result.getInt( "estimated" );
                itemsTable[i][7] = result.getInt( "duration" );
                itemsTable[i][8] = dateFormat( result.getString( "proccess_started" ) );
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" :dateFormat( result.getString( "proccess_finished" ) ) );
                itemsTable[i][10] = sprint.getAlias();
                Item item = itemManager.getItemById( result.getInt( "ref_item" ) );
                itemsTable[i][11] = item.getTitle();
                User user = userManager.getUserbyId(result.getInt("scrum_user") );
                itemsTable[i][12] = user.getName();
                itemsTable[i][13] = result.getString("status2");
                
                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de tarefas", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de tarefas.\n" + e );
        }

        table.setModel( new DefaultTableModel( itemsTable, header )
        {
            @Override
            public boolean isCellEditable( int row, int column )
            {
                return false;
            }
        } );
    }
     
     
     /**
     * setTasks
     * 
     * @param table
     * @param idStatus
     * @param sprint 
     */
     public void setAllTasks( JTable table, Sprint sprint )
    {
        String status = "";
        
        Object [][] itemsTable = null;
        
        Object[] header = new Object[ 14 ];
        header[0] = "ID";
        header[1] = "Título";
        header[2] = "Info";
        header[3] = "Classificação";
        header[4] = "Tipo";
        header[5] = "Estado";
        header[6] = "Estimado(minutos)";
        header[7] = "Duração(minutos)";
        header[8] = "Início";
        header[9] = "Fim";
        header[10] = "Sprint";
        header[11] = "Item";
        header[12] = "Responsável";
        header[13] = "Situação";
        
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_tasks WHERE idsprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 14 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar tarefas\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_tasks WHERE idsprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_tasks" );
                itemsTable[i][1] = result.getString( "title" );
                itemsTable[i][2] = result.getString( "info" );
                itemsTable[i][3] = result.getString( "classification" );
                itemsTable[i][4] = result.getString( "item_type" );
                
                if ( result.getInt("status" ) == 1 ) {
                    status = "TO DO";
                }
                else if (result.getInt("status" ) == 2) {
                    status = "IN PROGRESS";
                }
                else if (result.getInt("status" ) == 3) {
                    status =  "DONE";
                }
                else
                {
                    status = "n/d";
                }
                itemsTable[i][5] = status;
                itemsTable[i][6] = result.getInt( "estimated" );
                itemsTable[i][7] = result.getInt( "duration" );
                itemsTable[i][8] = dateFormat( result.getString( "proccess_started" ) );
                itemsTable[i][9] =  ( result.getString( "proccess_finished" ) == null ? "n/d" :dateFormat( result.getString( "proccess_finished" ) ) );
                itemsTable[i][10] = sprint.getAlias();
                Item item = itemManager.getItemById( result.getInt( "ref_item" ) );
                itemsTable[i][11] = item.getTitle();
                User user = userManager.getUserbyId(result.getInt("scrum_user") );
                itemsTable[i][12] = user.getName();
                itemsTable[i][13] = result.getString("status2");

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de tarefas", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de tarefas.\n" + e );
        }

        table.setModel( new DefaultTableModel( itemsTable, header )
        {
            @Override
            public boolean isCellEditable( int row, int column )
            {
                return false;
            }
        } );
    }
     /**
      * setTasksByItem
      * 
      * @param table
      * @param sprint
      * @param item 
      */
     public void setTasksByItem( JTable table, Sprint sprint,Item item )
    {
        String status = "";
        
        Object [][] itemsTable = null;
        
        Object[] header = new Object[ 14 ];
        header[0] = "ID";
        header[1] = "Título";
        header[2] = "Info";
        header[3] = "Classificação";
        header[4] = "Tipo";
        header[5] = "Estado";
        header[6] = "Estimado(minutos)";
        header[7] = "Duração(minutos)";
        header[8] = "Início";
        header[9] = "Fim";
        header[10] = "Sprint";
        header[11] = "Item";
        header[12] = "Responsável";
        header[13] = "Situação";
        
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_tasks WHERE idsprint = " + sprint.getIdSprint() + " AND ref_item =" + item.getIdItem();
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 14 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar tarefas\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_tasks WHERE idsprint = " + sprint.getIdSprint() + " AND ref_item =" + item.getIdItem();
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_tasks" );
                itemsTable[i][1] = result.getString( "title" );
                itemsTable[i][2] = result.getString( "info" );
                itemsTable[i][3] = result.getString( "classification" );
                itemsTable[i][4] = result.getString( "item_type" );
                
                if ( result.getInt("status" ) == 1 ) {
                    status = "TO DO";
                }
                else if (result.getInt("status" ) == 2) {
                    status = "IN PROGRESS";
                }
                else if (result.getInt("status" ) == 3) {
                    status =  "DONE";
                }
                else
                {
                    status = "n/d";
                }
                itemsTable[i][5] = status;
                itemsTable[i][6] = result.getInt( "estimated" );
                itemsTable[i][7] = result.getInt( "duration" );
                itemsTable[i][8] = dateFormat( result.getString( "proccess_started" ) );
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" :dateFormat( result.getString( "proccess_finished" ) ) );
                itemsTable[i][10] = sprint.getAlias();
//                Item item = itemManager.getItemById( result.getInt( "ref_item" ) );
                itemsTable[i][11] = item.getTitle();
                User user = userManager.getUserbyId(result.getInt("scrum_user") );
                itemsTable[i][12] = user.getName();
                itemsTable[i][13] = result.getString("status2");

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de tarefas", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de tarefas.\n" + e );
        }

        table.setModel( new DefaultTableModel( itemsTable, header )
        {
            @Override
            public boolean isCellEditable( int row, int column )
            {
                return false;
            }
        } );
    }
     
     
     public void setTasksByItem( JTable table, Sprint sprint,Item item, int status2 )
    {
        String status = "";
        
        Object [][] itemsTable = null;
        
        Object[] header = new Object[ 14 ];
        header[0] = "ID";
        header[1] = "Título";
        header[2] = "Info";
        header[3] = "Classificação";
        header[4] = "Tipo";
        header[5] = "Estado";
        header[6] = "Estimado(minutos)";
        header[7] = "Duração(minutos)";
        header[8] = "Início";
        header[9] = "Fim";
        header[10] = "Sprint";
        header[11] = "Item";
        header[12] = "Responsável";
        header[13] = "Situação";
        
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_tasks WHERE idsprint = " + sprint.getIdSprint() + " AND ref_item =" + item.getIdItem() + " AND status = " + status2 ;
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 14 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar tarefas\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_tasks WHERE idsprint = " + sprint.getIdSprint() + " AND ref_item =" + item.getIdItem()  + " AND status = " + status2;
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_tasks" );
                itemsTable[i][1] = result.getString( "title" );
                itemsTable[i][2] = result.getString( "info" );
                itemsTable[i][3] = result.getString( "classification" );
                itemsTable[i][4] = result.getString( "item_type" );
                
                if ( result.getInt("status" ) == 1 ) {
                    status = "TO DO";
                }
                else if (result.getInt("status" ) == 2) {
                    status = "IN PROGRESS";
                }
                else if (result.getInt("status" ) == 3) {
                    status =  "DONE";
                }
                else
                {
                    status = "n/d";
                }
                itemsTable[i][5] = status;
                itemsTable[i][6] = result.getInt( "estimated" );
                itemsTable[i][7] = result.getInt( "duration" );
                itemsTable[i][8] = dateFormat( result.getString( "proccess_started" ) );
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" :dateFormat( result.getString( "proccess_finished" ) ) );
                itemsTable[i][10] = sprint.getAlias();
//                Item item = itemManager.getItemById( result.getInt( "ref_item" ) );
                itemsTable[i][11] = item.getTitle();
                User user = userManager.getUserbyId(result.getInt("scrum_user") );
                itemsTable[i][12] = user.getName();
                itemsTable[i][13] = result.getString("status2");

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de tarefas", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de tarefas.\n" + e );
        }

        table.setModel( new DefaultTableModel( itemsTable, header )
        {
            @Override
            public boolean isCellEditable( int row, int column )
            {
                return false;
            }
        } );
    }
     
     public  Task getTaskById( int id )
     {
         String sql = "";
         Task task = new Task();
         try
         {
            Statement st = scrum.SCRUM.conexao.createStatement(); 
            
            sql = "SELECT * FROM sprint_tasks WHERE idsprint_tasks = " + id;
             System.out.println(sql);
            result = st.executeQuery(sql);
            while(result.next()){
                task.setIdTask(id);
                task.setClassification( result.getString("classification") );
                task.setTitle(result.getString("title"));
                task.setInfo(result.getString("info"));
                task.setSprint( sprintManager.getSprintById( result.getInt( "idsprint" ) ) );
                task.setProccessStarted( dateFormat( result.getString( "proccess_started" ) ) );
                task.setProccessFinished( ( result.getString( "proccess_finished" ) == null ? null : dateFormat( result.getString( "proccess_finished" ) ) ) );
                task.setDuration( String.valueOf( result.getInt("duration") ) );
                task.setEstimated( String.valueOf( result.getInt("estimated") ) );
                task.setItemType( result.getString( "item_type" ) );
                task.setRefItem( result.getInt("ref_item") );
                task.setStatus( result.getInt("status" ) );
                task.setTaskuser(userManager.getUserbyId(result.getInt("scrum_user")));
                task.setSituation(result.getString("status2"));
            }
            return task;
         }
         catch( Exception e ){
             log.createlog("", e.getMessage(), sql );
             return null;
         }
     }
     
    /**
     * updateTime
     * 
     * @param item
     * @param task
     * @return 
     */
    public boolean updateTime( Item item, Task task ){
        String sql = "";
        
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "UPDATE sprint_items SET duration = " + ( Integer.parseInt( item.getDuration() ) + Integer.parseInt( task.getDuration() ) )
                    + " WHERE idsprint_items =  " + item.getIdItem();
            System.out.println( sql );
            
            int cod = st.executeUpdate(sql );
            
            
            return true;
        }
        
        catch( Exception e )
        {
            log.createlog( " Update Error ", e.getMessage(), sql );
            return false;
        }
    }
    
    
    public void reportTeamTasks( int idteam, int idsprint )
    {
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/tasksbyTeam.jrxml"));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();
            parametros.put("idteam", idteam);
            parametros.put("idsprint", idsprint);

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());


            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            log.createlog( "Report Error", e.getMessage(), "Erro ao gerar relatório" );
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }
	
    
    public void reportTasksPeriod( String dataIni, String dataFim )
    {
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/reportTaskPeriod.jrxml"));
            SimpleDateFormat outputFormat = new SimpleDateFormat( "dd/MM/yyyy" );
            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();
            Date date = outputFormat.parse(dataIni);
            Date date2 = outputFormat.parse(dataFim);
            parametros.put("dataIni", date);
            parametros.put("dataFim", date2);

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());


            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            log.createlog( "Report Error", e.getMessage(), "Erro ao gerar relatório" );
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }
}
