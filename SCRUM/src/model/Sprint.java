/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;


/**
 *
 * @author willian
 */
public class Sprint
{
    private int idSprint;
    private String alias;
    private String dtFrom;
    private String dtUntil;
    private String estimated;
    private String duration;
    private int status;
    private int refTeam;

    /**
     * @return the id_sprint
     */
    public int getIdSprint()
    {
        return idSprint;
    }

    public void setIdSprint( int id )
    {
        this.idSprint = id;
    }
    
    /**
     * @return the alias
     */
    public String getAlias()
    {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias( String alias )
    {
        this.alias = alias;
    }

    /**
     * @return the dt_from
     */
    public String getDtFrom()
    {
        return dtFrom;
    }

    /**
     * @param dtFrom
     */
    public void setDtFrom( String dtFrom )
    {
        this.dtFrom = dtFrom;
    }

    /**
     * @return the dt_until
     */
    public String getDtUntil()
    {
        return dtUntil;
    }

    /**
     * @param dtUntil
     */
    public void setDtUntil( String dtUntil )
    {
        this.dtUntil = dtUntil;
    }

    /**
     * @return the estimated
     */
    public String getEstimated()
    {
        return estimated;
    }

    /**
     * @param estimated the estimated to set
     */
    public void setEstimated( String estimated )
    {
        this.estimated = estimated;
    }

    /**
     * @return the duration
     */
    public String getDuration()
    {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration( String duration )
    {
        this.duration = duration;
    }

    /**
     * @return the status
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus( int status )
    {
        this.status = status;
    }

    /**
     * @return the ref_team
     */
    public int getRefTeam()
    {
        return refTeam;
    }

    /**
     * @param refTeam
     */
    public void setRefTeam( int refTeam )
    {
        this.refTeam = refTeam;
    }
    
    
}
