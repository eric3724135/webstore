//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.15 at 10:09:33 �W�� TST 
//


package com.ericstudio.webstore.domain.brick.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apiKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="theme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="owned" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wanted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "apiKey",
    "theme",
    "userHash",
    "owned",
    "wanted"
})
@XmlRootElement(name = "getYearsForUser")
public class GetYearsForUser {

    protected String apiKey;
    protected String theme;
    protected String userHash;
    protected String owned;
    protected String wanted;

    /**
     * Gets the value of the apiKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the value of the apiKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiKey(String value) {
        this.apiKey = value;
    }

    /**
     * Gets the value of the theme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Sets the value of the theme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTheme(String value) {
        this.theme = value;
    }

    /**
     * Gets the value of the userHash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserHash() {
        return userHash;
    }

    /**
     * Sets the value of the userHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserHash(String value) {
        this.userHash = value;
    }

    /**
     * Gets the value of the owned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwned() {
        return owned;
    }

    /**
     * Sets the value of the owned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwned(String value) {
        this.owned = value;
    }

    /**
     * Gets the value of the wanted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWanted() {
        return wanted;
    }

    /**
     * Sets the value of the wanted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWanted(String value) {
        this.wanted = value;
    }

}
