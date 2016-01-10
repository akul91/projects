package com.practice.tms.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * @author jyothitamma.
 *
 */
    @Entity
    @Table(name = "Trainings")
    public class Trainings {
    /**
     * declaration of trainingId.
     */
    private long trainingId;
    /**
     * declaration of trainerId.
     */
    private long trainerId;   //(FK) who is giving training
    /**
     * declaration of status.
     */
    private String status;      //(Canceled/In Progress/Completed)
    /**
     * declaration of trainingInformation.
     */
    private TrainingInformation trainingInformation;
    /**
     * declaration of schedule.
     */
    private Schedule schedule;
    /**
     * getter method for trainingId.
     * @return trainingId {@link long}
     */
    @Id
    @GeneratedValue
    @Column(name = "trainingId")
    public final long getTrainingId() {
    return trainingId;
    }
    /**
     * getter method for trainerId.
     * @return trainerId {@link long}
     */
    @Column (name = "trainerId")
    public final long getTrainerId() {
    return trainerId;
    }
    /**
     * getter method for status.
     * @return status {@link String}
     */
    @Column (name = "Status")
    public final String getStatus() {
    return status;
    }
    /**
     * getter method for trainingInformation.
     * @return trainingInformation {@link TrainingInformation}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    public final TrainingInformation getTrainingInformation() {
    return trainingInformation;
    }
    /**
     * getter method for schedule.
     * @return schedule {@link Schedule}
     */
    @OneToOne(cascade = CascadeType.ALL)
    public final Schedule getSchedule() {
    return schedule;
    }
    /**
     * setter method for trainngId.
     * @param triningId {@link long}
     */
    public final void setTrainingId(final long triningId) {
    this.trainingId = triningId;
    }
    /**
     * setter method for traininerId.
     * @param trinerId {@link long}
     */
    public final void setTrainerId(final long trinerId) {
    this.trainerId = trinerId;
    }
    /**
     * setter method for status.
     * @param stat {@link String}
     */
    public final void setStatus(final String stat) {
    this.status = stat;
    }
    /**
     * setter method for trainingInformation.
     * @param trainingInfo {@link TrainingInformation}
     */
    public final void setTrainingInformation(
    final TrainingInformation trainingInfo) {
    this.trainingInformation = trainingInfo;
    }
    /**
     * setter method for schedule.
     * @param sched {@link Schedule}
     */
    public final void setSchedule(final Schedule sched) {
    this.schedule = sched;
    }
}