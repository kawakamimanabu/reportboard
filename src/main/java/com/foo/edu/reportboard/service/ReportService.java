package com.foo.edu.reportboard.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.foo.edu.reportboard.model.Report;

@Stateless
public class ReportService extends AbstractService {

	@SuppressWarnings("unchecked")
	public List<Report> getReportsByUser(int userId) {
		try {
			Query q = em.createNamedQuery("Reports.findByCreated");
			q.setParameter("createdUserId", userId);
			return (List<Report>)q.getResultList();
		}
		catch(NoResultException ex) {
			logger.severe("Reports レコードが見つかりません。[" + ex.getMessage() +"]");
		}
		return null;
	}
}
