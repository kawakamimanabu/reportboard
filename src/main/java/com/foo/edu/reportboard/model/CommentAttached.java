package com.foo.edu.reportboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the comment_attached database table.
 * 
 */
@Entity
@Table(name="comment_attached")
@NamedQueries ({
	@NamedQuery(name="CommentAttached.findAll" ,query="SELECT ca FROM CommentAttached ca ORDER BY ca.commentAttachedId ASC"),
	@NamedQuery(name="CommentAttached.findById",query="SELECT ca FROM CommentAttached ca WHERE ca.commentAttachedId = :commentAttachedId")
})
public class CommentAttached implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_attached_id")
	private Integer commentAttachedId;

	@Column(name="attached_name")
	private String attachedName;

	private byte[] content;

	//bi-directional many-to-one association to Comment
	@ManyToOne
	@JoinColumn(name="comment_id")
	private Comment comment;

	public CommentAttached() {
	}

	public Integer getCommentAttachedId() {
		return this.commentAttachedId;
	}

	public void setCommentAttachedId(Integer commentAttachedId) {
		this.commentAttachedId = commentAttachedId;
	}

	public String getAttachedName() {
		return this.attachedName;
	}

	public void setAttachedName(String attachedName) {
		this.attachedName = attachedName;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}