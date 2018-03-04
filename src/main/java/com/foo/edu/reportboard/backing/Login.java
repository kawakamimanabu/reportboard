package com.foo.edu.reportboard.backing;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.foo.edu.reportboard.model.User;
import com.foo.edu.reportboard.service.UserService;


/**
 * ログインクラス
 * @author
 *
 */
@Model
public class Login extends AbstractBacking {

	@EJB UserService userOperation;
	private String mailAddress;
	private String password;

	/**
	 * ログイン処理を行います。
	 * ロールによって遷移先画面が変わります。
	 * @return
	 */
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		User user = null;
		try {
			request.login(mailAddress, password);
			user = userOperation.getUserByMailAddress(mailAddress);
			if (user != null) {
				HttpSession session = request.getSession(false);
				if (session == null) {
					session = request.getSession(true);
				}
				session.setAttribute("user", user);
				if (user.getStaffFlag() > 0) {
					return "/staff/dashboard.xhtml?faces-redirect=true";
				}
				else if (user.getStaffFlag() == 0) {
					return "/newstaff/dashboard.xhtml?faces-redirect=true";
				}
				else {
					return "/access-denied.xhtml?faces-redirect=true";
				}
			}
			else {
				logger.severe("アカウントが見つかりません。 [" + mailAddress + "]");
				return "/access-denied.xhtml?faces-redirect=true";
			}
		} catch (ServletException ex) {
			if (ex.getMessage().contains("already logged in")) {
				logger.warning("forcedly logout. [" + mailAddress + "]");
				logout();
			}
			return "/access-denied.xhtml?faces-redirect=true";
		}
	}

	/**
	 * ログアウト処理を行います。
	 * @return
	 */
	public String logout() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.invalidateSession();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		try {
		    request.logout();
		} catch (ServletException ex) {
			return "/error.xhtml?faces-redirect=true";
		}
		return "/login.xhtml?faces-redirect=true";
	}

	// --- getter, setter ---
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
