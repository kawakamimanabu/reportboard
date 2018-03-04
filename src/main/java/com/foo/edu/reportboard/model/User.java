package com.foo.edu.reportboard.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@NamedQueries ({
	@NamedQuery(name="User.findAll",              query="SELECT u FROM User u ORDER BY u.userId ASC"),
	@NamedQuery(name="User.findById",             query="SELECT u FROM User u WHERE u.userId = :userId"),
	@NamedQuery(name="User.findByName",           query="SELECT u FROM User u WHERE u.name = :name"),
	@NamedQuery(name="User.findByMailAddress",    query="SELECT u FROM User u WHERE u.mailAddress = :mailAddress"),
	@NamedQuery(name="User.findEducationStaff",   query="SELECT u FROM User u WHERE u.staffFlag = 1 ORDER BY u.userId ASC"),
	@NamedQuery(name="User.findAllCandidates",    query="SELECT u FROM User u WHERE u.staffFlag = 0 ORDER BY u.userId ASC")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;

	@Column(name="delete_flag")
	private Integer deleteFlag;

	@Column(name="mail_address")
	private String mailAddress;

	private String name;

	private String password;

	@Column(name="staff_flag")
	private Integer staffFlag;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="createdUserId")
	private List<Report> reports1;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="updatedUserId")
	private List<Report> reports2;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStaffFlag() {
		return this.staffFlag;
	}

	public void setStaffFlag(Integer staffFlag) {
		this.staffFlag = staffFlag;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public List<Report> getReports1() {
		return this.reports1;
	}

	public void setReports1(List<Report> reports1) {
		this.reports1 = reports1;
	}

	public Report addReports1(Report reports1) {
		getReports1().add(reports1);
		reports1.setUser1(this);

		return reports1;
	}

	public Report removeReports1(Report reports1) {
		getReports1().remove(reports1);
		reports1.setUser1(null);

		return reports1;
	}

	public List<Report> getReports2() {
		return this.reports2;
	}

	public void setReports2(List<Report> reports2) {
		this.reports2 = reports2;
	}

	public Report addReports2(Report reports2) {
		getReports2().add(reports2);
		reports2.setUser2(this);

		return reports2;
	}

	public Report removeReports2(Report reports2) {
		getReports2().remove(reports2);
		reports2.setUser2(null);

		return reports2;
	}

}