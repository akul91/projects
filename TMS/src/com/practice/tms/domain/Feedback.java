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
@Table(name = "Feedback")
public class Feedback {
/**
 * declaration of enrollmentId.
 */
private long enrollmentId;
/**
 * declaration of feedbackId.
 */
private long feedbackId;
/**
 * declaration of optionOne.
 */
private int optionOne;
/**
 * declaration of optionTwo.
 */
private int optionTwo;
/**
 * declaration of optionThree.
 */
private int optionThree;
/**
 * declaration of optionFour.
 */
private int optionFour;
/**
 * declaration of comments.
 */
private String comments;
/**
 * getter method for feedbackId.
 * @return feedbackId {@link long}
 */
@Id
@GeneratedValue
@Column(name = "feedbackId")
public final long getFeedbackId() {
return feedbackId;
}
/**
 * getter method for optionOne.
 * @return optionOne {@link int}
 */
@Column (name = "OptionOne")
public final int getOptionOne() {
return optionOne;
}
/**
 * getter method for optionTwo.
 * @return optionTwo {@link int}
 */
@Column (name = "OptionTwo")
public final int getOptionTwo() {
return optionTwo;
}
/**
 * getter method for optionThree.
 * @return optionThree {@link int}
 */
@Column (name = "OptionThree")
public final int getOptionThree() {
return optionThree;
}
/**
 * getter method for optionFour.
 * @return optionFour {@link int}
 */
@Column (name = "OptionFour")
public final int getOptionFour() {
return optionFour;
}
/**
 * getter method for comments.
 * @return comments {@link String}
 */
@Column (name = "Comments")
public final String getComments() {
return comments;
}
/**
 *getter method for enrollmentId.
 * @return enrollmentId {@link long}
 */
@Column (name = "enrollmentId")
public final long getEnrollmentId() {
return enrollmentId;
}
/**
 *seter method for feedback.
 * @param feedbkId {@link long}
 */
public final void setFeedbackId(final long feedbkId) {
this.feedbackId = feedbkId;
}
/**
 * setter method for optionOne.
 * @param optOne {@link int}
 */
public final void setOptionOne(final int optOne) {
this.optionOne = optOne;
}
/**
 * setter method for optionTwo.
 * @param optTwo {@link int}
 */
public final void setOptionTwo(final int optTwo) {
this.optionTwo = optTwo;
}
/**
 * setter method for optionThree.
 * @param optThree {@link int}
 */
public final void setOptionThree(final int optThree) {
this.optionThree = optThree;
}
/**
 * setter method for optionFour.
 * @param optFour {@link int}
 */
public final void setOptionFour(final int optFour) {
this.optionFour = optFour;
}
/**
 * setter method for comments.
 * @param comnts {@link int}
 */
public final void setComments(final String comnts) {
this.comments = comnts;
}
/**
 * setter method for enrollmentId.
 * @param enrollId {@link int}
 */
public final void setEnrollmentId(final long enrollId) {
this.enrollmentId = enrollId;
}
}