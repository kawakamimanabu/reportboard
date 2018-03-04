package com.foo.edu.reportboard.backing;

import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.foo.edu.reportboard.model.User;

/**
 * BackingBean の基底クラス
 * @author
 *
 */
public abstract class AbstractBacking {

	@Inject protected Logger logger;

	/**
	 * ログイン中の Staff 情報を取得します。
	 * @return
	 */
	protected User getLoggedInStaff() {
		HttpSession session = getSessionFromRequest();
		return (User)session.getAttribute("users");
	}

	/**
	 * Session を取得します。
	 * @return
	 */
	private HttpSession getSessionFromRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
        	session = request.getSession(true);
        }
        return session;
	}

}
