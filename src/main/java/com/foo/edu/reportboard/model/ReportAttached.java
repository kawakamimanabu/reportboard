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
 * The persistent class for the report_attached database table.
 * 
 */
@Entity
@Table(name="report_attached")
@NamedQueries ({
	@NamedQuery(name="ReportAttached.findAll" ,query="SELECT ra FROM ReportAttached ra ORDER BY ra.reportAttachedId ASC"),
	@NamedQuery(name="ReportAttached.findById",query="SELECT ra FROM ReportAttached ra WHERE ra.reportAttachedId = :reportAttachedId")
})
public class ReportAttached implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="report_attached_id")
	private Integer reportAttachedId;

	@Column(name="attached_name")
	private String attachedName;

	private byte[] content;

	//bi-directional many-to-one association to Report
	@ManyToOne
	@JoinColumn(name="report_id")
	private Report report;

	public ReportAttached() {
	}

	public Integer getReportAttachedId() {
		return this.reportAttachedId;
	}

	public void setReportAttachedId(Integer reportAttachedId) {
		this.reportAttachedId = reportAttachedId;
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

	public Report getReport() {
		return this.report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}