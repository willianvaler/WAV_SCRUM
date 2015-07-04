/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author willian
 */
public class Task {
    private int idTask;
    private int refItem;
    private Sprint sprint;
    private String classification;
    private String title;
    private String info;
    private String itemType;
    private int status;
    private String estimated;
    private String duration;
    private String proccessStarted;
    private String proccessFinished;
    private User taskuser;
    private String situation;

    /**
     * @return the id_task
     */
    public int getIdTask()
    {
        return idTask;
    }

    /**
     * @param idTask
     */
    public void setIdTask( int idTask )
    {
        this.idTask = idTask;
    }

    /**
     * @return the ref_item
     */
    public int getRefItem()
    {
        return refItem;
    }

    /**
     * @param refItem
     */
    public void setRefItem( int refItem )
    {
        this.refItem = refItem;
    }

    /**
     * @return the sprint
     */
    public Sprint getSprint()
    {
        return sprint;
    }

    /**
     * @param sprint the sprint to set
     */
    public void setSprint( Sprint sprint )
    {
        this.sprint = sprint;
    }

    /**
     * @return the classification
     */
    public String getClassification()
    {
        return classification;
    }

    /**
     * @param classification the classification to set
     */
    public void setClassification( String classification )
    {
        this.classification = classification;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle( String title )
    {
        this.title = title;
    }

    /**
     * @return the info
     */
    public String getInfo()
    {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo( String info )
    {
        this.info = info;
    }

    /**
     * @return the itemType
     */
    public String getItemType()
    {
        return itemType;
    }

    /**
     * @param item_type the itemType to set
     */
    public void setItemType( String item_type )
    {
        this.itemType = item_type;
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
     * @return the proccess_started
     */
    public String getProccessStarted()
    {
        return proccessStarted;
    }

    /**
     * @param proccessStarted
     */
    public void setProccessStarted( String proccessStarted )
    {
        this.proccessStarted = proccessStarted;
    }

    /**
     * @return the proccess_finished
     */
    public String getProccessFinished()
    {
        return proccessFinished;
    }

    /**
     * @param proccessFinished
     */
    public void setProccessFinished( String proccessFinished )
    {
        this.proccessFinished = proccessFinished;
    }

    /**
     * @return the taskuser
     */
    public User getTaskuser() {
        return taskuser;
    }

    /**
     * @param taskuser the taskuser to set
     */
    public void setTaskuser(User taskuser) {
        this.taskuser = taskuser;
    }

    /**
     * @return the situation
     */
    public String getSituation() {
        return situation;
    }

    /**
     * @param situation the situation to set
     */
    public void setSituation(String situation) {
        this.situation = situation;
    }
    
    
    
}
