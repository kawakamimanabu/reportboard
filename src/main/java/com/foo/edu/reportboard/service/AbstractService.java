package com.foo.edu.reportboard.service;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * サービスの基底クラス
 * @author manabu
 *
 */
public abstract class AbstractService {
	@Inject protected Logger logger;
	@PersistenceContext(unitName="primary")
	protected EntityManager em;
}
