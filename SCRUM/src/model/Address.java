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
public class Address
{
    private int idAddress;
    private String address;
    private String district;
    private String city;
    private String state;
    private String uf;

    /**
     * @return the idAddress
     */
    public int getIdAddress()
    {
        return idAddress;
    }

    /**
     * @param idAddress the idAddress to set
     */
    public void setIdAddress( int idAddress )
    {
        this.idAddress = idAddress;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress( String address )
    {
        this.address = address;
    }

    /**
     * @return the district
     */
    public String getDistrict()
    {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict( String district )
    {
        this.district = district;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity( String city )
    {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState( String state )
    {
        this.state = state;
    }

    /**
     * @return the uf
     */
    public String getUf()
    {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf( String uf )
    {
        this.uf = uf;
    }
    
    
}
