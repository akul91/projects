package com.practice.tms.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author jyothitamma
 *
 */
@Entity
@Table(name = "TrainingInformation")
public class TrainingInformation {
/**
 * Declaration for trainingInformationId.
 */
private long  trainingInformationId;         //(PK)
/**
 * Declaration for trainingProgram.
 */
private String trainingProgram;
/**
 * Declaration for technology.
 */
private String technology;
/**
 * declaration for trainingDescription.
 */
private String trainingDescription;
/**
 * Declaration for tier.
 */
private String tier;
/**
 * getter method for trainingInformationId.
 * @return trainingInformationId {@link long}
 */
@Id
@GeneratedValue
@Column(name = "trainingInformationId")
public final long getTrainingInformationId() {
return trainingInformationId;
}
/**
 * getter method for trainingProgram.
 * @return trainingProgram {@link String}
 */
@Column (name = "trainingProgram")
public final String getTrainingProgram() {
return trainingProgram;
}
/**
 * getter method for technology.
 * @return technology {@link String}
 */
@Column (name = "technology")
public final String getTechnology() {
return technology;
}
/**
 * getter method for trainingDescription.
 * @return trainingDescription {@link String}
 */
@Column (name = "trainingDescription")
public final String getTrainingDescription() {
return trainingDescription;
}
/**
 * getter method for tier.
 * @return tier {@link String}
 */
@Column (name = "tier")
public final String getTier() {
return tier;
}
/**
 * setter method for trainingInformationId.
 * @param trainingInfoId {@link long}
 */
public final void setTrainingInformationId(final long trainingInfoId) {
this.trainingInformationId = trainingInfoId;
}
/**
 * setter method for trainingProgram.
 * @param trainingProg {@link String}
 */
public final void setTrainingProgram(final String trainingProg) {
this.trainingProgram = trainingProg;
}
/**
 * setter method for technology.
 * @param techno {@link String}
 */
public final void setTechnology(final String techno) {
this.technology = techno;
}
/**
 * setter method for trainingDescription.
 * @param trainingDesc {@link String}
 */
public final void setTrainingDescription(final String trainingDesc) {
this.trainingDescription = trainingDesc;
}
/**
 * setter method for tier.
 * @param eTier {@link String}
 */
public final void setTier(final String eTier) {
this.tier = eTier;
}
}