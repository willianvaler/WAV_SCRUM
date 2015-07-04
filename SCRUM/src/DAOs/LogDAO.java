/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import util.Arquivo;

/**
 *
 * @author willian
 */
public class LogDAO
{
    Arquivo a = new Arquivo("Log.txt");
    SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
    
    public boolean createlog( String tipoLog, String erro, String sql )
    {
        Timestamp time = new Timestamp( System.currentTimeMillis() );
        dateFormat.format( time );
        
        try
        {
            if ( a.abrirEscrita( true ) )
            {
                a.escreverLinha( dateFormat.format( time ).toString() );
                a.escreverLinha( " | " + tipoLog + " | " );
                a.escreverLinha( erro + " | " );
                a.escreverLinha( sql + "\n\n" );
                a.fecharArquivo();
            }
        }
        catch( Exception e )
        {
        
        }
        return true;
    }
    
}
