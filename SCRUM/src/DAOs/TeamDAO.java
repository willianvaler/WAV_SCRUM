/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Team;

/**
 *
 * @author willian
 */
public class TeamDAO
{
    
    ResultSet result;
    int insertCode;
    LogDAO log = new LogDAO();
    
    public boolean insertTeam( Team team,int scrumId )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "INSERT INTO team "
                    + "VALUES ( DEFAULT,'" + team.getName() + "' , "
                    + 1 + ", " + scrumId
                    + ") returning idteam";
            result = st.executeQuery( sql );
            result.next();
            System.out.println( "Código retornado: " + result.getInt( 1 ) );
            insertCode = result.getInt( 1 );

            log.createlog( "Insert Team", "", sql);
            
            return true;
        }
        catch ( Exception e )
        {
            log.createlog( "Team Insert Error", e.getMessage(), sql);
            return false;
        }
    }
    public boolean updateTeam( int id, Team team )
    {
        String sql ="";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
        
            sql = "UPDATE team SET name = '" + team.getName() + "',"
                    + "total_participants = " + ( team.getTotalParticipants() > 1 ? team.getTotalParticipants() : 1 ) + ", " 
                    +"scrum_master = " + (team.getScrumMaster() > 0 ? team.getScrumMaster() : 1 ) + " "
                    + "WHERE idteam = " + id;
            
            System.out.println( "SQL: " + sql );
            int res = st.executeUpdate( sql );
            log.createlog( "Update Team", "", sql);
            return true;
        }
        catch( Exception e )  
        {
            log.createlog( "Team Update Error", e.getMessage(), sql);
            return false;
        }
    }
    
    public boolean deleteTeam( int id )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            
            sql = "DELETE FROM team WHERE idteam = " + id;
            System.out.println( sql );
            int result = st.executeUpdate(sql );
            log.createlog( "Delete Team", "", sql);
            return true;
        }
        catch( Exception e )
        {
            log.createlog( "Team Delete Error", e.getMessage(), sql);
            return false;
        }
    }
    
    public void setItensByFilter( JTable table, String search )
    {
        Object [][] itemsTable = null;
        Object header[] = new Object[4];
        header[0] = "ID";
        header[1] = "Nome";
        header[2] = "Total de participantes";
        header[3] = "Scrum Master";
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM team WHERE name LIKE '%" + search + "%' ";
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 4 ];
        }
        catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao consultar equipes.\n" + e );
        }
        int i = 0 ;
        
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM team WHERE name LIKE '%" + search + "%' ORDER BY idteam";
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "idteam" );
                itemsTable[i][1] = result.getString( "name" );
                itemsTable[i][2] = result.getString( "total_participants" );
                itemsTable[i][3] = result.getString( "scrum_master" );

                i++;
            }
        }
        catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "Erro ao inserir equipes na tabela de pesquisa.\n" + e );
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
    
    public boolean validateDuplicatedTeamByName( String  teamName )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "SELECT COUNT(*) FROM team WHERE name LIKE '" + teamName + "'";
            result = st.executeQuery( sql );
            result.next();
            int qt = result.getInt( 1 );
            if ( qt >= 1 )
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch( Exception e )
        {
            log.createlog( "Select Error ", e.getMessage(), sql);
            return false;
        }
    }
    
    public void updateTotalParticipants( int id )
    {
        String sql = "";
        Team team = new Team();
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "SELECT * FROM team WHERE idteam = " + id;
            result = st.executeQuery( sql );
            result.next();
            
            System.out.println(sql);
            
            team.setIdItem( result.getInt( 1 ) );
            team.setName( result.getString( 2 ) );
            team.setTotalParticipants( result.getInt( 3 ) + 1 );
            team.setScrumMaster( result.getInt( 4 ) );
            log.createlog( "Update Total Participants", "", sql);
            updateTeam(id, team);
        }
        catch( Exception e )
        {
            log.createlog( "Update Total Participants Team Error", e.getMessage(), sql);
        }
    }
    
    public void decreaseTotalParticipants( int id )
    {
        String sql = "";
        Team team = new Team();
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "SELECT * FROM team WHERE idteam = " + id;
            result = st.executeQuery( sql );
            result.next();
            
            System.out.println(sql);
            
            team.setIdItem( result.getInt( 1 ) );
            team.setName( result.getString( 2 ) );
            team.setTotalParticipants( result.getInt( 3 ) - 1 );
            team.setScrumMaster( result.getInt( 4 ) );
            log.createlog( "Update Total Participants", "", sql);
            updateTeam(id, team);
        }
        catch( Exception e )
        {
            log.createlog( "Update Total Participants Team Error", e.getMessage(), sql);
            System.out.println(e);
        }
    }
    
    public Team getTeamById( int id )
    {
        String sql = "";
        Team team = new Team();
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "SELECT * FROM team WHERE idteam = " + id;
            result = st.executeQuery( sql );
            result.next();
            
            System.out.println(sql);
            
            team.setIdItem( result.getInt( 1 ) );
            team.setName( result.getString( 2 ) );
            team.setTotalParticipants( result.getInt( 3 ) + 1 );
            team.setScrumMaster( result.getInt( 4 ) );
            
            return team;
        }
        catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
    
    
    public Team getTeamByName( String name )
    {
        String sql = "";
        Team team = new Team();
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "SELECT * FROM team WHERE name like '" + name + "'";
            result = st.executeQuery( sql );
            result.next();
            
            System.out.println(sql);
            
            team.setIdItem( result.getInt( 1 ) );
            team.setName( result.getString( 2 ) );
            team.setTotalParticipants( result.getInt( 3 ) + 1 );
            team.setScrumMaster( result.getInt( 4 ) );
            
            return team;
        }
        catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
}
