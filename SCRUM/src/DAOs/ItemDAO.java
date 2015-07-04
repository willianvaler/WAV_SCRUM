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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Item;
import model.Sprint;

public class ItemDAO
{
    ResultSet result;
    int insertCode;
    LogDAO log = new LogDAO();
    SprintDAO sprintManager = new SprintDAO();
    
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

    public boolean insertItem( Item item )
    {
        Sprint sprint = item.getSprint();
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "INSERT INTO sprint_items VALUES (DEFAULT, " 
                            + " '" + item.getTitle() 
                            + "', '" + item.getInfo() 
                            + "', '" + item.getClassification() 
                            + "', '" + item.getItemType() 
                            + "', '" + item.getStatus() 
                            + "', " + item.getEstimated() 
                            + ", " + item.getDuration() 
                            + ", " + sprint.getIdSprint()
                            + ", '" + item.getProccessStarted() 
                            + "', " + ( item.getProccessFinished() == null ? null : " '" + item.getProccessFinished() + "' " )
                            + ") returning idsprint_items";
            System.out.println(sql);
            
            result = st.executeQuery( sql );
            
            result.next();
            
            System.out.println("Código retornado: " + result.getInt(1));
            
            insertCode = result.getInt(1);
            
            log.createlog( "Insert Item", "", sql);
            
            return true;
        }
        catch( Exception e )
        {
//            JOptionPane.showMessageDialog(null, "Erro ao inserir item");
            log.createlog( "Item Insert Error", e.getMessage(), sql);
                return false;
        }
    }
	
	public boolean updateItem( int id, Item item )
	{
            Sprint sprint = item.getSprint();
            String sql = "";
		try
		{
			Statement st = scrum.SCRUM.conexao.createStatement();
			
			sql = "UPDATE sprint_items SET title = '" + item.getTitle() +
                                "', info = '" + item.getInfo() + 
                                "', classification = '" + item.getClassification() + 
                                "', item_type = '" + item.getItemType() +
                                "', status = " + item.getStatus() + 
                                ", estimated = " + item.getEstimated() + 
                                ", duration = " + item.getDuration() + 
                                ", proccess_started = '" + item.getProccessStarted() + 
                                "', proccess_finished = " + ( item.getProccessFinished() == null ? null :  " '" + item.getProccessFinished() + "' " ) + 
                                ", sprint = " + sprint.getIdSprint() + 
                                " where idsprint_items = " + id;
                        System.out.println(sql);
			int res = st.executeUpdate( sql );
                        log.createlog( "Update Item", "", sql);
                        return true;
		}
		catch( Exception e )
		{
//                    JOptionPane.showMessageDialog( null, "Erro ao atualizar item");
                    log.createlog( "Item Update Error ", e.getMessage(), sql);
			return false;
		}
	}
	
	public boolean deleteItem( int id )
	{
            String sql = "";
		try
		{
                    Statement st = scrum.SCRUM.conexao.createStatement();

                    sql = "DELETE FROM sprint_items WHERE idsprint_items = " + id;
                    System.out.println( sql );
                    log.createlog( "Delete Item", "", sql);
                    int result = st.executeUpdate(sql );
            return true;
		}
		catch( Exception e )
		{
                    log.createlog( "Item Delete Error", e.getMessage(), sql);
//                    JOptionPane.showMessageDialog( null, "Erro ao deletar item");
                    return false;
		}
	}
        
    public void setItemsByFilter( JTable table, String search )
    {
        String status = "";
        Object[][] itemsTable = null;

        Object[] header = new Object[ 11 ];
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

        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_items WHERE title LIKE '%" + search + "%' ";
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 11 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar itens\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_items WHERE title LIKE '%" + search + "%'";
            System.out.println(sql);
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_items" );
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
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" : dateFormat( result.getString( "proccess_finished" ) ) );
                Sprint sprint = sprintManager.getSprintById( result.getInt( "sprint" ) );
                itemsTable[i][10] = sprint.getAlias();

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de itens.\n" + e );
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
    
    public void setItemsBySprintID( JTable table, String search, Sprint sprint )
    {
        String status = "";
        Object[][] itemsTable = null;

        Object[] header = new Object[ 11 ];
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

        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_items WHERE sprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 11 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar itens\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_items WHERE sprint = " + sprint.getIdSprint();
            System.out.println(sql);
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_items" );
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
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" : dateFormat( result.getString( "proccess_finished" ) ) );
//                Sprint sprint = sprintManager.getSprintById( result.getInt( "sprint" ) );
                itemsTable[i][10] = sprint.getAlias();

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de itens por id sprint ", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de itens.\n" + e );
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
    
    public void setItemsToDo( JTable table, String search, Sprint sprint )
    {
        String status = "";
        Object[][] itemsTable = null;

        Object[] header = new Object[ 11 ];
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
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_items WHERE title LIKE '%" + search + "%' AND status = " + 1 + " AND sprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 11 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar itens\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_items WHERE title LIKE '%" + search + "%' AND status = " + 1 + " AND sprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_items" );
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
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" : dateFormat( result.getString( "proccess_finished" ) ) );
                itemsTable[i][10] = sprint.getAlias();
                
                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela ", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de itens To Do.\n" + e );
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
    
    public void setItemsInProgress( JTable table, String search, Sprint sprint )
    {
        String status = "";
        Object[][] itemsTable = null;

        Object[] header = new Object[ 11 ];
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
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_items WHERE title LIKE '%" + search + "%' AND status = " + 2 + " AND sprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 11 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar itens\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_items WHERE title LIKE '%" + search + "%' AND status = " + 2 + " AND sprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_items" );
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
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" : dateFormat( result.getString( "proccess_finished" ) ) );
                itemsTable[i][10] = sprint.getAlias();

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de itens In Progress", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de itens In Progress.\n" + e );
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
    
    
    public void setItemsDone( JTable table, String search, Sprint sprint )
    {
        String status = "";
        Object[][] itemsTable = null;

        Object[] header = new Object[ 11 ];
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
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM sprint_items WHERE title LIKE '%" + search + "%' AND status = " + 3 + " AND sprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 11 ];
        }
        catch ( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar itens\n" + e );
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM sprint_items WHERE title LIKE '%" + search + "%' AND status = " + 3 + " AND sprint = " + sprint.getIdSprint();
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint_items" );
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
                itemsTable[i][9] = ( result.getString( "proccess_finished" ) == null ? "n/d" : dateFormat( result.getString( "proccess_finished" ) ) );
                itemsTable[i][10] = sprint.getAlias();

                i++;
            }
        }
        catch ( Exception e )
        {
            log.createlog( "Erro ao preencher tabela de itens Done", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela de itens Done.\n" + e );
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
    
    public Item getItemByTitle( String title )
    {
        String sql = "";
        try
       {
           Item item = new Item();
           Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "SELECT * FROM sprint_items WHERE title LIKE '" + title + "'";
           System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                item.setIdItem(result.getInt( "idsprint_items" ) );
                item.setTitle(result.getString( "title" ) );
                item.setInfo( result.getString( "info" ) );
                item.setProccessStarted( dateFormat( result.getString( "proccess_started" ) ) );
                item.setProccessFinished( ( result.getString( "proccess_finished" ) == null ? null : dateFormat( result.getString( "proccess_finished" ) ) ) );
                item.setEstimated(result.getString( "estimated" ) );
                item.setDuration(result.getString( "duration" ) );
                item.setStatus( result.getInt( "status" ) );
                item.setItemType(result.getString( "item_type" ) );
                item.setClassification(result.getString( "classification" ) );
                item.setSprint( sprintManager.getSprintById( result.getInt( "sprint" ) ) );
                
           }
           
           return item;
       }
       catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
    
    
    public Item getItemById( int id )
    {
        String sql = "";
        try
       {
           Item item = new Item();
           Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "SELECT * FROM sprint_items WHERE idsprint_items = " + id;
           System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                item.setIdItem(result.getInt( "idsprint_items" ) );
                item.setTitle(result.getString( "title" ) );
                item.setInfo( result.getString( "info" ) );
                item.setProccessStarted( dateFormat( result.getString("proccess_started") ) );
                item.setProccessFinished( ( result.getString( "proccess_finished" ) == null ? null : dateFormat( result.getString( "proccess_finished" ) ) ) );
                item.setEstimated(result.getString( "estimated" ) );
                item.setDuration(result.getString( "duration" ) );
                item.setStatus( result.getInt( "status" ) );
                item.setItemType(result.getString( "item_type" ) );
                item.setClassification(result.getString( "classification" ) );
                item.setSprint( sprintManager.getSprintById( result.getInt( "sprint" ) ) );
                
           }
           
           return item;
       }
       catch( Exception e )
        {
           log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
    
    public boolean updateTime( Sprint sprint, Item item ){
        String sql  = "";
        
        try 
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "UPDATE sprint SET duration = " + 
                   ( Integer.parseInt( sprint.getDuration() ) + Integer.parseInt( item.getDuration() ) )
                   + "WHERE idsprint = " + sprint.getIdSprint();
           System.out.println( sql );
           int res = st.executeUpdate(sql );
           log.createlog( "", "", sql);
           return true;
        }
        
        catch( Exception e )
        {
            log.createlog( "Update Error", e.getMessage(), sql);
            return false;
        }
    }
    
}
