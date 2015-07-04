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
import model.Sprint;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class SprintDAO
{
    ResultSet result;
    int insertCode;
    LogDAO log = new LogDAO();
    
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
    
    public boolean insertSprint( Sprint sprint )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "INSERT INTO sprint VALUES (DEFAULT, '" +
                            sprint.getAlias() + "', '" + sprint.getDtFrom() + "', '" + sprint.getDtUntil() +
                            "', " + sprint.getEstimated() + ", " + sprint.getDuration() +
                            ", " + sprint.getRefTeam() + ") returning idsprint";
            result = st.executeQuery( sql );
            
            result.next();
            
            System.out.println("Código retornado: " + result.getInt(1));
            
            insertCode = result.getInt(1);
            log.createlog( "Insert Sprint", "", sql);
                    
            return true;
        }
        catch( Exception e )
        {
            log.createlog( "Sprint Insert Error", e.getMessage(), sql);
                return false;
        }
    }
	
	public boolean updateSprint( Sprint sprint )
	{
            String sql = "";
		try
		{
			Statement st = scrum.SCRUM.conexao.createStatement();
			
			sql = "UPDATE sprint SET alias = '" + sprint.getAlias() + 
                                "', dt_from = '" + sprint.getDtFrom() + 
                                "', dt_until = '" + sprint.getDtUntil() +
                                "', estimated = " + sprint.getEstimated() + 
                                ", duration = " + sprint.getDuration() +
//                                ", status = " + sprint.getStatus() + 
                                ", ref_team = " + sprint.getRefTeam() +
                                " WHERE idsprint = " + sprint.getIdSprint();
			int res = st.executeUpdate( sql );
                        log.createlog( "Update Sprint", "", sql);
                        return true;
		}
		catch( Exception e )
		{
                    log.createlog( "Sprint Update Error", e.getMessage(), sql);
			return false;
		}
	}
	
	public boolean deleteSprint( int id )
	{
            String sql = "";
		try
		{
                    Statement st = scrum.SCRUM.conexao.createStatement();

                    sql = "DELETE FROM sprint WHERE idsprint = " + id;
                    System.out.println( sql );
                    log.createlog( "Delete Sprint", "", sql);
                    int result = st.executeUpdate(sql );
            return true;
		}
		catch( Exception e )
		{
                    log.createlog( "Sprint Delete Error", e.getMessage(), sql);
                    return false;
		}
	}
        
    public void setSprintsByFilter( JTable table, String search )
    {
        Object [][] itemsTable = null;
        
        Object[] header = new Object[6];
        header[0] = "ID";
        header[1] = "Nome";
        header[2] = "Data Início";
        header[3] = "Data Fim";
        header[4] = "Horas Estimadas";
        header[5] = "Duração";
//        header[6] = "Estado";
        String sql = "";
        
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            
            sql = "SELECT COUNT(*) FROM sprint WHERE alias LIKE '%" + search + "%'";
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 6];
        }
        catch(Exception e)
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar Sprint." );
        }
        int i = 0;
        
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            
            sql = "SELECT * FROM sprint WHERE alias LIKE '%" + search + "%' ORDER BY idsprint";
            System.out.println(sql);
            result = st.executeQuery( sql );
            while( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idsprint" );
                itemsTable[i][1] = result.getString( "alias" );
                itemsTable[i][2] = dateFormat( result.getString( "dt_from" ) );
                itemsTable[i][3] = dateFormat( result.getString( "dt_until" ) );
                itemsTable[i][4] = result.getInt("estimated" );
                itemsTable[i][5] = result.getInt("duration" );
//                itemsTable[i][6] = result.getInt( "status" );
                
                i++;
            }
        }
        catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela.\n" + e );
        }
        
        table.setModel(new DefaultTableModel(itemsTable, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        });
    }
    
    public void reportSprintItems( int cod )
    {
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/reportSprintItems.jrxml"));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();
            parametros.put("SprintId", cod);
//            parametros.put("codigoEstado2", 21);

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            JasperExportManager.exportReportToPdfFile(impressao, "C:/Users/Willian/arquivo.pdf");

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            log.createlog( "Report Error", e.getMessage(), "Erro ao gerar relatório");
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }
	
    
    public Sprint getSprintByAlias( String alias )
    {
        SimpleDateFormat outputFormat = new SimpleDateFormat( "dd/MM/yyyy" );
        SimpleDateFormat inputFormat = new SimpleDateFormat( "yyyy-MM-dd" );
            
        String sql = "";
        try
       {
           Sprint sprint = new Sprint();
           Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "SELECT * FROM sprint WHERE alias LIKE '" + alias + "'";
           System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                 String date1 = ( result.getString( "dt_from" ) );
                 String date2 = ( result.getString( "dt_until" ) );
                 Date dateFrom = null;
                 Date dateUntil = null;
                try 
                {
                    dateFrom = inputFormat.parse(date1);
                    dateUntil = inputFormat.parse(date2); 
                } 
                
                catch (ParseException ex) 
                {
                    ex.printStackTrace();
                }
            
                sprint.setIdSprint(result.getInt( "idsprint" ) );
                sprint.setAlias(result.getString( "alias" ) );
                sprint.setDtFrom( outputFormat.format( dateFrom ) );
                sprint.setDtUntil( outputFormat.format( dateUntil ) );
                sprint.setEstimated(result.getString( "estimated" ) );
                sprint.setDuration(result.getString( "duration" ) );
//                sprint.setStatus(result.getInt( "status" ) );
                sprint.setRefTeam(result.getInt( "ref_team" ) );
           }
           
           return sprint;
       }
       catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
    
    
    
    public Sprint getSprintById( int id )
    {
        SimpleDateFormat outputFormat = new SimpleDateFormat( "dd/MM/yyyy" );
        SimpleDateFormat inputFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        String sql = "";    
        try
       {
           Sprint sprint = new Sprint();
           Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "SELECT * FROM sprint WHERE idsprint = " + id;
           System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                 String date1 = ( result.getString( "dt_from" ) );
                 String date2 = ( result.getString( "dt_until" ) );
                 
                 Date dateFrom = null;
                 Date dateUntil = null;
                try 
                {
                    dateFrom = inputFormat.parse(date1);
                    dateUntil = inputFormat.parse(date2); 
                } 
                
                catch (ParseException ex) 
                {
                   log.createlog( "Convert Error", ex.getMessage(), sql);
                }
            
                sprint.setIdSprint(result.getInt( "idsprint" ) );
                sprint.setAlias(result.getString( "alias" ) );
                sprint.setDtFrom( outputFormat.format( dateFrom ) );
                sprint.setDtUntil( outputFormat.format( dateUntil ) );
                sprint.setEstimated(result.getString( "estimated" ) );
                sprint.setDuration(result.getString( "duration" ) );
//                sprint.setStatus(result.getInt( "status" ) );
                sprint.setRefTeam(result.getInt( "ref_team" ) );
           }
           
           return sprint;
       }
       catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
}
