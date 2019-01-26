package tech.lapsa.eurasia.dao.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import tech.lapsa.eurasia.dao.CompanyPointOfSaleDAO;
import tech.lapsa.eurasia.dao.CompanyPointOfSaleDAO.CompanyPointOfSaleDAOLocal;
import tech.lapsa.eurasia.dao.CompanyPointOfSaleDAO.CompanyPointOfSaleDAORemote;
import tech.lapsa.eurasia.domain.CompanyPointOfSale;
import tech.lapsa.eurasia.domain.CompanyPointOfSale_;

@Stateless(name = CompanyPointOfSaleDAO.BEAN_NAME)
@Interceptors(LoggingInterceptor.class)
public class CompanyPointOfSaleDAOBean
	extends ABaseDAO<CompanyPointOfSale, Integer>
	implements CompanyPointOfSaleDAOLocal, CompanyPointOfSaleDAORemote {

    public CompanyPointOfSaleDAOBean() {
	super(CompanyPointOfSale.class);
    }

    @Override
    public List<CompanyPointOfSale> findAll() {
	final CriteriaBuilder cb = em.getCriteriaBuilder();
	final CriteriaQuery<CompanyPointOfSale> cq = cb.createQuery(CompanyPointOfSale.class);
	final Root<CompanyPointOfSale> root = cq.from(CompanyPointOfSale.class);
	cq.select(root);
	final TypedQuery<CompanyPointOfSale> q = em.createQuery(cq);
	return q.getResultList();
    }

    @Override
    public List<CompanyPointOfSale> findAllByAvailable(boolean available) {
	final CriteriaBuilder cb = em.getCriteriaBuilder();
	final CriteriaQuery<CompanyPointOfSale> cq = cb.createQuery(entityClass);
	final Root<CompanyPointOfSale> root = cq.from(entityClass);
	cq.select(root)
		.where(cb.equal(root.get(CompanyPointOfSale_.available), Boolean.valueOf(available)));
	final TypedQuery<CompanyPointOfSale> q = em.createQuery(cq);
	return q.getResultList();
    }

}
