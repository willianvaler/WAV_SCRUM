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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ConexaoBD;
import model.Address;
import model.Team;
import model.User;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class UserDAO
{
    ResultSet result;
    int insertCode;
    LogDAO log = new LogDAO();
    TeamDAO teamManager = new TeamDAO();

    /**
     *
     * @param user
     * @param address
     * @return
     */
    public boolean insertUser( User user, Address address )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            int idAddress = insertAddress( address );

            sql = "INSERT INTO scrum_user "
                    + "VALUES ( DEFAULT,"
                    + "'" + user.getName() + "', " + "'" + user.getEmail() + "', " + "'" + user.getPhone()
                    + "', " + "'" + user.getLogin() + "', " + "'" + user.getPassword() + "', " + user.getUserType()
                    + ", " + idAddress + ", " + (user.getRefTeam() > 0 ? user.getRefTeam() : 0 ) + ") returning iduser";
            result = st.executeQuery( sql );
            result.next();
            System.out.println( "Código retornado: " + result.getInt( 1 ) );
            insertCode = result.getInt( 1 );
            log.createlog( "Insert User ", "", sql);
            return true;
        }
        catch ( Exception e )
        {
//            JOptionPane.showMessageDialog( null, " Erro ao inserir usuário no Banco de Dados.\n " + e );
            log.createlog( "Insert Error", e.getMessage(), sql);
            return false;
        }

    }

    public int insertAddress( Address address )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "INSERT INTO address VALUES ( DEFAULT, "
                    + "'" + address.getAddress() + "'," + "'" + address.getDistrict() + "',"
                    + "'" + address.getCity() + "'," + "'" + address.getUf() 
                    + "'," + "'" + address.getState() + "') returning idaddress";
            result = st.executeQuery( sql );
            result.next();
            System.out.println(sql);
            int idaddress = result.getInt( 1 );
            log.createlog( "Insert Address ", "", sql);
            return idaddress;
        }
        catch ( Exception e )
        {
//            JOptionPane.showMessageDialog( null, " Erro ao inserir Endereço no Banco de Dados.\n " + e );
            log.createlog( "Insert Error ", e.getMessage(), sql);
            return 0;
        }

    }
    
    /**
     *
     * @param id
     * @param newUser
     * @param address
     * @return
     */
    public boolean updateUser( int id, User newUser, Address address )
    {
        String sql = "";
        try
        {

            Statement st = scrum.SCRUM.conexao.createStatement();
            
            int idAddress = updateAddress( address );
            
            sql = "UPDATE scrum_user SET name = '" + newUser.getName() + "',"
                    + " email = '" + newUser.getEmail() + "',"
                    + "phone = '" + newUser.getPhone() + "',"
                    + "login = '" + newUser.getLogin() + "',"
                    + "password = '" + newUser.getPassword() + "',"
                    + "user_type  = " + newUser.getUserType() + ", " 
                    + "ref_address  = " + idAddress + ", " 
                    + "ref_team = " + (newUser.getRefTeam() > 0 ? newUser.getRefTeam() : 0 ) + " "
                    + "WHERE iduser = " + id;
            
            
            System.out.println( "SQL: " + sql );
            int res = st.executeUpdate( sql );
            
           log.createlog( "Update User", "", sql);
            
            return true;
            
        }
        catch ( Exception e )
        {
            log.createlog( " User Update Error ", e.getMessage(),sql  );
            return false;
        }

    }
    
    public int updateAddress( Address address )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            
            sql = "UPDATE address SET "
                    + "address = '" + address.getAddress() + "'," 
                    + "district = '" + address.getDistrict() + "',"
                    + "city = '" + address.getCity() + "'," 
                    + "uf = '" + address.getUf() + "'," 
                    + "state = '" + address.getState() 
                    + "' where idaddress = " + address.getIdAddress() ;
            
            int id = st.executeUpdate( sql );
            
            result.next();
            System.out.println(sql);
//            int idaddress = result.getInt( 1 );
            log.createlog( "Update Address ", "", sql);
            return id;
        }
        catch ( Exception e )
        {
//            JOptionPane.showMessageDialog( null, " Erro ao atualizar Endereço no Banco de Dados.\n " + e );
            log.createlog( "Address Update Error", e.getMessage(), sql);
            return 0;
        }

    }
    
    /**
     *
     * @param user
     * @return
     */
    public boolean deleteUser( int id )
    {
        String sql = "";
        String sql2 = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            User u = getUserbyId(id);
            sql = "DELETE FROM scrum_user WHERE iduser = " + id;
            System.out.println( sql );
            int result = st.executeUpdate(sql );
            sql2 = "DELETE FROM address where idaddress = " + u.getRefAddress();
            result = st.executeUpdate( sql );
            System.out.println(sql);
            log.createlog( " User Deleted", "",sql );
            
            return true;
        }
        catch ( Exception e )
        {
            log.createlog( " User Delete Error ", e.getMessage() , sql + "; " + sql2 );
            return false;
        }
    }

    /**
     *
     * @param table
     * @param search
     */
    public void setItensByFilter( JTable table, String search )
    {
        Object[][] itemsTable = null;

        Object[] header = new Object[ 8 ];
        header[0] = "ID";
        header[1] = "Nome";
        header[2] = "E-Mail";
        header[3] = "Telefone";
        header[4] = "Login";
        header[5] = "Senha";
        header[6] = "Tipo";
        header[7] = "ID Equipe";
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT COUNT(*) FROM scrum_user WHERE name LIKE '%" + search + "%' ";
            result = st.executeQuery( sql );
            result.next();
            System.out.println( sql );
            itemsTable = new Object[ result.getInt( 1 ) ][ 8 ];
        }
        catch ( Exception e )
        {
//            JOptionPane.showMessageDialog( null, "Erro ao consultar usuários\n" + e );
            log.createlog( "Select Error", e.getMessage(), sql);
        }
        int i = 0;

        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();

            sql = "SELECT * FROM scrum_user WHERE name LIKE '%" + search + "%' ORDER BY iduser";
            result = st.executeQuery( sql );
            while ( result.next() )
            {
                itemsTable[i][0] = result.getInt( "iduser" );
                itemsTable[i][1] = result.getString( "name" );
                itemsTable[i][2] = result.getString( "email" );
                itemsTable[i][3] = result.getString( "phone" );
                itemsTable[i][4] = result.getString( "login" );
                itemsTable[i][5] = result.getString( "password" );
                itemsTable[i][6] = result.getInt( "user_type" );
                int ref_user = result.getInt( "ref_team" );
                Team team = teamManager.getTeamById( ref_user );
                itemsTable[i][7] = team.getName();

                i++;
            }
        }
        catch ( Exception e )
        {
//            JOptionPane.showMessageDialog( null, "Erro ao preencher tabela.\n" + e );
            log.createlog( "Table Data Error", e.getMessage(), sql);
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
    
    public User getUserBylogin( String login )
    {
        String sql = "";
       try
       {
           User user = new User();
           Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "SELECT * FROM scrum_user WHERE login LIKE '" + login + "'";
           System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                user.setId( result.getInt( "iduser" ) );
                user.setName( result.getString( "name" ) );
                user.setLogin( result.getString( "login" ) );
                user.setEmail( result.getString( "email" ) );
                user.setPhone( result.getString( "phone" ) );
                user.setPassword( result.getString( "password" ) );
                user.setUserType( result.getInt( "user_type" ) );
                user.setRefAddress( result.getInt( "ref_address" ) );
                user.setRefTeam( result.getInt( "ref_team" ) );
           }
           
           return user;
       }
       catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
    
    public User getUserName( String name )
    {
        String sql = "";
       try
       {
           User user = new User();
           Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "SELECT * FROM scrum_user WHERE name LIKE '" + name + "'";
           System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                user.setId( result.getInt( "iduser" ) );
                user.setName( result.getString( "name" ) );
                user.setLogin( result.getString( "login" ) );
                user.setEmail( result.getString( "email" ) );
                user.setPhone( result.getString( "phone" ) );
                user.setPassword( result.getString( "password" ) );
                user.setUserType( result.getInt( "user_type" ) );
                user.setRefAddress( result.getInt( "ref_address" ) );
                user.setRefTeam( result.getInt( "ref_team" ) );
           }
           
           return user;
       }
       catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
    
    public User getUserbyId( int id )
    {
        String sql = "";
       try
       {
           User user = new User();
           Statement st = scrum.SCRUM.conexao.createStatement();
           
           sql = "SELECT * FROM scrum_user WHERE iduser = " + id ;
           System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                user.setId( result.getInt( "iduser" ) );
                user.setName( result.getString( "name" ) );
                user.setLogin( result.getString( "login" ) );
                user.setEmail( result.getString( "email" ) );
                user.setPhone( result.getString( "phone" ) );
                user.setPassword( result.getString( "password" ) );
                user.setUserType( result.getInt( "user_type" ) );
                user.setRefAddress( result.getInt( "ref_address" ) );
                user.setRefTeam( result.getInt( "ref_team" ) );
           }
           
           return user;
       }
       catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
    }
    
    public void setUserIdBylogin( User user )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            
            sql = "SELECT iduser FROM scrum_user WHERE name = '"+ user.getName() +"' AND login = '" + user.getLogin() + "'";
            result = st.executeQuery( sql );
            result.next();
            int id = result.getInt( 1 );
            user.setId( id );
        }
        catch(Exception e)
        { 
            log.createlog( "Select Error", e.getMessage(), sql);
//            JOptionPane.showMessageDialog( null, "erro ao consultar id do usuário" );
        }
    }
    
    public boolean validateDuplicatedUserByLogin( User user )
    {
        String sql = "";
        try
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            sql = "SELECT COUNT(*) FROM scrum_user WHERE login LIKE '" + user.getLogin() + "'";
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
            log.createlog( "Select Error", e.getMessage(), sql);
            return false;
        }
        
    }
    
    public Address getAddressByLogin( String login )
    {
        String sql = "";
        User u = getUserBylogin( login );
        Address address = new Address();
        try 
        {
            Statement st = scrum.SCRUM.conexao.createStatement();
            
            sql = "SELECT * FROM address WHERE idaddress = " + u.getRefAddress();
            System.out.println( sql );
           result = st.executeQuery( sql );
           while( result.next() )
           {
                address.setIdAddress( result.getInt( 1 ) );
                address.setAddress( result.getString( 2 ) );
                address.setDistrict( result.getString( 3 ) );
                address.setCity( result.getString( 4 ) );
                address.setUf( result.getString( 5 ) );
                address.setState( result.getString( 6 ) );

           }
           return address;
           
            
        }
        catch( Exception e )
        {
            log.createlog( "Select Error", e.getMessage(), sql);
            return null;
        }
        
    }
    
    public void reportTeamUsers( int cod )
    {
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/reportTeamUsers.jrxml"));
//            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/cidades_estados.jrxml"));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();
            parametros.put("TeamId", cod);
//            parametros.put("codigoEstado2", 21);

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

//            JasperExportManager.exportReportToPdfFile(impressao, "C:/Users/Willian/arquivo.pdf");

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            log.createlog( "Report Error", e.getMessage(), "Erro ao gerar relatório" );
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }
    
}
