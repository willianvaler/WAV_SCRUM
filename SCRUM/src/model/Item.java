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
public class Item {
    private int IdItem;
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

    /**
     * @return the IdItem
     */
    public int getIdItem()
    {
        return IdItem;
    }

    /**
     * @param IdItem the IdItem to set
     */
    public void setIdItem( int IdItem )
    {
        this.IdItem = IdItem;
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
     * @param itemType the itemType to set
     */
    public void setItemType( String itemType )
    {
        this.itemType = itemType;
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
     * @return the proccessStarted
     */
    public String getProccessStarted()
    {
        return proccessStarted;
    }

    /**
     * @param proccessStarted the proccessStarted to set
     */
    public void setProccessStarted( String proccessStarted )
    {
        this.proccessStarted = proccessStarted;
    }

    /**
     * @return the proccessFinished
     */
    public String getProccessFinished()
    {
        return proccessFinished;
    }

    /**
     * @param proccessFinished the proccessFinished to set
     */
    public void setProccessFinished( String proccessFinished )
    {
        this.proccessFinished = proccessFinished;
    }
    
    
    
}
