package com.practice.tms.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * .
 * @author jyothitamma
 *
 */
@Entity
@Table(name = "Enrollment")
public class Enrollment {
    /**
     * declaration of enrollment.
     */
    private long enrollmentId;
    /**
     * declaration of trainingId.
     */
    private  long trainingId;     //(FK)
    /**
     * declaration of employeeId.
     */
    private  long employeeId;     //(FK) who is  attending
    /**
     * declaration of statusId.
     */
    private int statusId;
    /**
     * declaration of employee.
     */
    private Employee employee;
    /**
     * declaration of training.
     */
    private Trainings trainings;
    /*
    1 Waiting  for Approval
    2 Approved
    3 Rejection
    4 Canceled
    5 Feedback Pending
    6 Feedback Submitted
    */
    /**
     *Getter method.
     * @return enrollmentId {@link long}
     */
    @Id
    @GeneratedValue
    @Column(name = "enrollmentId")
    public final long getEnrollmentId() {
    return enrollmentId;
    }
    /**
     *Getter method.
     * @return trainingId {@link long}
     */
    @Column(name = "trainingId")
    public final long getTrainingId() {
    return trainingId;
    }
    /**
     *Getter method.
     * @return employeeId {@link long}
     */
    @Column (name = "employeeId")
    public final long getEmployeeId() {
    return employeeId;
    }
    /**
     *Getter method.
     * @return statusId {@link int}
     */
    @Column (name = "statusId")
    public final int getStatusId() {
    return statusId;
    }
    /**
     *Getter method.
     * @return employee {@link Employee}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    public final Employee getEmployee() {
    return employee;
    }
    /**
     *Getter method.
     * @return trainings {@link Trainings}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    public final Trainings getTrainings() {
    return trainings;
    }
    /**
     *Setter method.
     * @param trainId {@link long}
     */
    public final void setTrainingId(final long trainId) {
    this.trainingId = trainId;
    }
    /**
     *Setter method.
     * @param emplId {@link long}
     */
    public final void setEmployeeId(final long emplId) {
    this.employeeId = emplId;
    }
    /**
     *Setter method.
     * @param statId {@link int}
     */
    public final void setStatusId(final int statId) {
    this.statusId = statId;
    }
    /**
     *Setter method.
     * @param empl {@link Employee}
     */
    public final void setEmployee(final Employee empl) {
    this.employee = empl;
    }
    /**
     *Setter method.
     * @param training {@link Trainings}
     */
    public final void setTrainings(final Trainings training) {
    this.trainings = training;
    }
    /**
     *Setter method.
     * @param enrollId {@link long}
     */
    public final void setEnrollmentId(final long enrollId) {
    this.enrollmentId = enrollId;
    }
    }