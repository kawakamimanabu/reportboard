package com.foo.edu.reportboard.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r")
@NamedQueries ({
	@NamedQuery(name="Report.findAll",              query="SELECT r FROM Report r ORDER BY r.reportId ASC"),
	@NamedQuery(name="Report.findById",             query="SELECT r FROM Report r WHERE r.reportId = :reportId"),
	@NamedQuery(name="Report.findByCreated",        query="SELECT r FROM Report r WHERE r.createdUserId.userId = :createdUserId ORDER BY r.createdWhen DESC"),
	@NamedQuery(name="Report.findByUpdated",        query="SELECT r FROM Report r WHERE r.updatedUserId.userId = :updatedUserId")
})
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="report_id")
	private Integer reportId;

	@Column(name="created_when")
	private Timestamp createdWhen;

	private String report;

	@Column(name="updated_when")
	private Timestamp updatedWhen;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="report")
	private List<Comment> comments;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="created_user_id")
	private User createdUserId;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="updated_user_id")
	private User updatedUserId;

	//bi-directional many-to-one association to ReportAttached
	@OneToMany(mappedBy="report")
	private List<ReportAttached> reportAttacheds;

	public Report() {
	}

	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Timestamp getCreatedWhen() {
		return this.createdWhen;
	}

	public void setCreatedWhen(Timestamp createdWhen) {
		this.createdWhen = createdWhen;
	}

	public String getReport() {
		return this.report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public Timestamp getUpdatedWhen() {
		return this.updatedWhen;
	}

	public void setUpdatedWhen(Timestamp updatedWhen) {
		this.updatedWhen = updatedWhen;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setReport(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setReport(null);

		return comment;
	}

	public User getUser1() {
		return this.createdUserId;
	}

	public void setUser1(User user1) {
		this.createdUserId = user1;
	}

	public User getUser2() {
		return this.updatedUserId;
	}

	public void setUser2(User user2) {
		this.updatedUserId = user2;
	}

	public List<ReportAttached> getReportAttacheds() {
		return this.reportAttacheds;
	}

	public void setReportAttacheds(List<ReportAttached> reportAttacheds) {
		this.reportAttacheds = reportAttacheds;
	}

	public ReportAttached addReportAttached(ReportAttached reportAttached) {
		getReportAttacheds().add(reportAttached);
		reportAttached.setReport(this);

		return reportAttached;
	}

	public ReportAttached removeReportAttached(ReportAttached reportAttached) {
		getReportAttacheds().remove(reportAttached);
		reportAttached.setReport(null);

		return reportAttached;
	}

}