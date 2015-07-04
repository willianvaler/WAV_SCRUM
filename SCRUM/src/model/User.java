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
public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String login;
    private String password;
    private int userType;
    private int refAddress;
    private int refTeam;

    public void setId( int id )
    {
       this.id = id;
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the refAddress
     */
    public int getRefAddress() {
        return refAddress;
    }

    /**
     * @param refAddress
     */
    public void setRefAddress(int refAddress) {
        this.refAddress = refAddress;
    }

    /**
     * @return the refTeam
     */
    public int getRefTeam() {
        return refTeam;
    }

    /**
     * @param refTeam
     */
    public void setRefTeam(int refTeam) {
        this.refTeam = refTeam;
    }
    
    
}
