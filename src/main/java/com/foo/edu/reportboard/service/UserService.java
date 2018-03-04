package com.foo.edu.reportboard.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.security.auth.spi.Users;

import com.foo.edu.reportboard.model.User;

/**
 * Staff 情報を操作する EJB クラス
 * @author
 *
 */
@Stateless
public class UserService extends AbstractService {

	/**
	 * アカウント名から Users レコードを取得します。
	 * @param name
	 * @return
	 */
	public User getUserByName(String name) {
		try {
			Query q = em.createNamedQuery("Users.findByName");
			q.setParameter("name", name);
			return (User) q.getSingleResult();
		}
		catch(NoResultException ex) {
			logger.severe("User レコードが見つかりません。[" + ex.getMessage() +"]");
		}
		return null;
	}

	/**
	 * ID から Users レコードを取得します。
	 * @param userId
	 * @return
	 */
	public Users getUserById(int userId) {
		try {
			Query q = em.createNamedQuery("Users.findById");
			q.setParameter("userId", userId);
			return (Users) q.getSingleResult();
		}
		catch(NoResultException ex) {
			logger.severe("Users レコードが見つかりません。UserID:[" + userId + "], [" + ex.getMessage() +"]");
		}
		return null;
	}

	/**
	 * メールアドレスから Users レコードを取得します。
	 * @param mailAddress
	 * @return
	 */
	public User getUserByMailAddress(String mailAddress) {
		try {
			Query q = em.createNamedQuery("Users.findByMailAddress");
			q.setParameter("mailAddress", mailAddress);
			return (User) q.getSingleResult();
		}
		catch(NoResultException ex) {
			logger.severe("User レコードが見つかりません。mailAddress:[" + mailAddress + "], [" + ex.getMessage() +"]");
		}
		return null;
	}
	
	/**
	 * 内定者全員の一覧を取得します。
	 * ID の昇順、入社年の降順
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllCandidateList(){
		Query q = em.createNamedQuery("Staff.findAllCandidates");
		return q.getResultList();
	}

	/**
	 * 教育係一覧を取得します。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getEducationStaffList() {
		Query q = em.createNamedQuery("Staff.findEducationStaff");
		return q.getResultList();
	}

	/**
	 * User 情報を更新します。
	 * @param target
	 */
	public User updateUser(User target) {
		User staff = em.merge(target);
		em.flush();
		return staff;
	}

	/**
	 * Staff 情報を保存します。
	 * @param target
	 * @return
	 */
	public User saveStaff(User target) {
		if (target.getUserId() > 0) {
			updateUser(target);
		}
		else {
			em.persist(target);
			User user = getUserByMailAddress(target.getMailAddress());
			target.setUserId(user.getUserId());
		}
		return target;
	}

	/**
	 * パスワードを変更します。
	 * @param target
	 * @return
	 */
	public int updatePassword(User target) {
		String jpql = "UPDATE Users s SET s.password = :password WHERE s.userId = :userId";
		Query q = em.createQuery(jpql);
		q.setParameter("password", target.getPassword());
		q.setParameter("userId", target.getUserId());
		return q.executeUpdate();
	}
}
