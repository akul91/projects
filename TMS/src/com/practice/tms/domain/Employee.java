package com.practice.tms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
*
* @author jyothitamma.
*
*/
@Entity
@Table(name="Employee")
public class Employee {

	private long dummy ;
	/**
     * declaration of employeeId.
     */
    private long employeeId;
    /**
     * declaration of password.
     */
    private String password;
    /**
     * declaration of employeeName.
     */
    private String employeeName;
    /**
     * declaration of employeeEmail.
     */
    private String employeeEmail;
    /**
     * declaration of employeeNumber.
     */
    private long employeeNumber;
    /**
     * declaration of employeeDesignation.
     */
    private String employeeDesignation;
    /**
     * declaration of emplloyeeTier.
     */
    private String employeeTier;
    /**
     * declaration of projectManagerId.
     */
    private long projectManagerId;
    /**
     * declaration of employeeCategory.
     */
    private String employeeCategory;
    /**
     * declaration of securityQuestion.
     */
    private int  securityQuestion;
    /**
     * declaration of securityAnswer.
     */
    private String securityAnswer;

	@Id
	@GeneratedValue
	@Column(name="dummy")
	public long getDummy() {
		return dummy;
	}
	public void setDummy(long dummy) {
		this.dummy = dummy;
	}
    
/**
 * getter method for employeeId.
 * @return employeeId { @ link long}
 */
    @Column(name = "employeeId")
    public final long getEmployeeId() {
    return employeeId;
    }
    /**
     * getter method for employeeName.
     * @return employeeName { @ link String}
     */
    @Column (name = "employeeName")
    public final String getEmployeeName() {
    return employeeName;
    }
    /**
     * getter method for employeeEmail.
     * @return employeeEmail { @ link String}
     */
    @Column(name = "employeeEmail")
    public final String getEmployeeEmail() {
    return employeeEmail;
    }
    /**
     * getter method for employeeNumber.
     * @return employeeNumber { @ link long}
     */
    @Column(name = "employeeNumber")
    public final long getEmployeeNumber() {
    return employeeNumber;
    }
    /**
     * getter method for employeeDesignation.
     * @return employeeDesignation { @ link String}
     */
    @Column(name = "employeeDesignation")
    public final String getEmployeeDesignation() {
    return employeeDesignation;
    }
    /**
     * getter method for employeeTier.
     * @return employeeTier { @ link String}
     */
    @Column(name = "employeeTier")
    public final String getEmployeeTier() {
    return employeeTier;
    }
    /**
     * getter method for employeeCategory.
     * @return employeeCategory { @ link String}
     */
    @Column(name = "employeeCategory")
    public final String getEmployeeCategory() {
    return employeeCategory;
    }
    /**
     * getter method for password.
     * @return password { @ link String}
     */
    @Column(name = "password")
    public final String getPassword() {
    return password;
    }
    /**
     * getter method for projectManagerId.
     * @return projectManagerId { @ link long}
     */
    @Column(name = "projectMangId")
    public final long getProjectManagerId() {
    return projectManagerId;
    }
    /**
     * setter method for password.
     * @param pwd {@ link String}
     */
    public final void setPassword(final String pwd) {
    this.password = pwd;
    }
    /**
    * setter method for employeeCategory.
    * @param emplCategory {@link String}
    */
    public final void setEmployeeCategory(final String emplCategory) {
    this.employeeCategory = emplCategory;
    }
    /**
     * setter method for employeeEmail.
     * @param emplEmail {@link String}
     */
    public final void setEmployeeEmail(final String emplEmail) {
    this.employeeEmail = emplEmail;
    }
    /**
     * setter method for employeeNumber.
     * @param emplNumber {@link long}
     */
    public final void setEmployeeNumber(final long emplNumber) {
    this.employeeNumber = emplNumber;
    }
    /**
     * Setter method for employeeDesignation.
     * @param emplDesignation {@link String}
     */
    public final void setEmployeeDesignation(final String emplDesignation) {
    this.employeeDesignation = emplDesignation;
    }
    /**
     * setter method for EmployeeTier.
     * @param emplTier {@link String}
     */
    public final void setEmployeeTier(final String emplTier) {
    this.employeeTier = emplTier;
    }
    /**
     * setter method for projectManagerId.
     * @param projectMangId {@link long}
     */
    public final void setProjectManagerId(final long projectMangId) {
    this.projectManagerId = projectMangId;
    }
    /**
     * setter method for employeeName.
     * @param emplName {@link String}
     */
    public final void setEmployeeName(final String emplName) {
    this.employeeName = emplName;
    }
    /**
     * setter method for employeeId.
     * @param emplId {@link long}
     */
    public final void setEmployeeId(final long emplId) {
    this.employeeId = emplId;
    }
    /**
     * getter method for securityQuestion.
     * @return securityQuestion { @ link int}
     */
    @Column(name = "securityQuestion")
    public final int getSecurityQuestion() {
    return securityQuestion;
    }
    /**
     * setter method for securityQuestion.
     * @param question { @ link String}
     */
    public final void setSecurityQuestion(final int question) {
    this.securityQuestion = question;
    }
    /**
     * getter method for securityAnswer.
     * @return securityAnswer { @ link String}
     */
    @Column(name = "securityAnswer")
    public final String getSecurityAnswer() {
    return securityAnswer;
    }
    /**
     * setter method for securityAnswer.
     * @param answer { @ link String}
     */
    public final void setSecurityAnswer(final String answer) {
    this.securityAnswer = answer;
    }
}

	
	
