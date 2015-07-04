/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author willian
 */
public class Team {
    private int idTeam;
    private String name;
    private int totalParticipants;
    private int scrumMaster; //id user 

    /**
     * @return the idI_item
     */
    public int getIdTeam()
    {
        return idTeam;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @return the total_participants
     */
    public int getTotalParticipants()
    {
        return totalParticipants;
    }

    /**
     * @param totalParticipants
     */
    public void setTotalParticipants( int totalParticipants )
    {
        this.totalParticipants = totalParticipants;
    }

    /**
     * @return the scrum_master
     */
    public int getScrumMaster()
    {
        return scrumMaster;
    }

    /**
     * @param scrumMaster
     */
    public void setScrumMaster( int scrumMaster )
    {
        this.scrumMaster = scrumMaster;
    }

    /**
     * @param idItem
     */
    public void setIdItem( int idItem )
    {
        this.idTeam = idItem;
    }
    
    
}
