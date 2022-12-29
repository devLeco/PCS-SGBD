package repositorio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
 

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public class RepositorioBase  { //talvez implements interface
	
	@PersistenceContext
    private EntityManager entityManagerFactory;
	
	public EntityManager getEntityManager() {
	        return entityManagerFactory;
	    }
	
	/* emrpms.blogspot */
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> klass) {
		return entityManagerFactory.createQuery("Select t from " + klass.getSimpleName() + " t")
				.getResultList();
	}

	 @Transactional
	public <T> T save(T t) {
		T newRecord = null;
		newRecord = entityManagerFactory.merge(t);
		return newRecord;
	}

	 @Transactional
	public <T> void delete(T t) {
		entityManagerFactory.remove(entityManagerFactory.merge(t));
		entityManagerFactory.flush();
	}

	
	
	/* */
    @SuppressWarnings("unchecked")
//    @Transactional(readOnly = true)
    public <T> List<T> list(Class<T> clz) {
        CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<T> cri = cb.createQuery(clz);
        cri.from(clz);
        Query query = entityManagerFactory.createQuery(cri);
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <T> List<T> list(Class<T> clz, String columnOrder) {
        CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<T> cri = cb.createQuery(clz);
        
        if (StringUtils.isNotEmpty(columnOrder)){
            Root<T> classe = cri.from(clz);
            cri.select(classe);
            cri.orderBy(cb.asc(classe.get(columnOrder)));
        }else{
            cri.from(clz);
        }
        Query query = entityManagerFactory.createQuery(cri);
        return query.getResultList();
    }

    public <T> List<T> list(Class<T> clz, int index, int pageSize) {
        CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<T> cri = cb.createQuery(clz);
        cri.from(clz);

        TypedQuery<T> query = entityManagerFactory.createQuery(cri);
        query.setFirstResult(index);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

//    @Transactional(readOnly = true)
    public <T> int sizeOf(Class<T> clz) {
        CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Long> cri = cb.createQuery(Long.class);
        cri.select(cb.count(cri.from(clz)));

        TypedQuery<Long> query = entityManagerFactory.createQuery(cri);
        return query.getSingleResult().intValue();
    }

    @Transactional
    public <T> T update(T item) {
        item = entityManagerFactory.merge(item);
        return item;
    }

    @Transactional
    public <T> T merge(T formEntity) {
        return entityManagerFactory.merge(formEntity);
    }

    public <T> T refresh(T item) {
        entityManagerFactory.refresh(item);
        return item;
    }
    
    public <T> T reload(Class<T> clz, Serializable key) {
        if(key == null || clz == null)
            return null;
        return entityManagerFactory.find(clz, key);
    }

}