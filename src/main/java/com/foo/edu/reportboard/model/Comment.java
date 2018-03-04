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
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQueries ({
	@NamedQuery(name="Comment.findAll" ,query="SELECT c FROM Comment c ORDER BY c.commentId ASC"),
	@NamedQuery(name="Comment.findById",query="SELECT c FROM Comment c WHERE c.commentId = :commentId")
})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private Integer commentId;

	private String comment;

	@Column(name="created_when")
	private Timestamp createdWhen;

	//bi-directional many-to-one association to Report
	@ManyToOne
	@JoinColumn(name="report_id")
	private Report report;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="created_user_id")
	private User user;

	//bi-directional many-to-one association to CommentAttached
	@OneToMany(mappedBy="comment")
	private List<CommentAttached> commentAttacheds;

	public Comment() {
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreatedWhen() {
		return this.createdWhen;
	}

	public void setCreatedWhen(Timestamp createdWhen) {
		this.createdWhen = createdWhen;
	}

	public Report getReport() {
		return this.report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentAttached> getCommentAttacheds() {
		return this.commentAttacheds;
	}

	public void setCommentAttacheds(List<CommentAttached> commentAttacheds) {
		this.commentAttacheds = commentAttacheds;
	}

	public CommentAttached addCommentAttached(CommentAttached commentAttached) {
		getCommentAttacheds().add(commentAttached);
		commentAttached.setComment(this);

		return commentAttached;
	}

	public CommentAttached removeCommentAttached(CommentAttached commentAttached) {
		getCommentAttacheds().remove(commentAttached);
		commentAttached.setComment(null);

		return commentAttached;
	}

}