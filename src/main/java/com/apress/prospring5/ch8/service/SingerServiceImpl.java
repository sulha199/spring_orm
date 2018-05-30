package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
    final static String ALL_SINGER_NATIVE_QUERY =
            "select id, first_name, last_name, birth_date, version from singer";
    private static Logger logger =
            LoggerFactory.getLogger(SingerServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAll() {
//        throw new NotImplementedException("findAll");
//        throw new NotImplementedException();
        return em.createNamedQuery(Singer.FIND_ALL, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAllWithAlbum() {
//        throw new NotImplementedException("findAllWithAlbum");
        throw new NotImplementedException();
    }

    @Transactional(readOnly=true)
    @Override
    public Singer findById(Long id) {
//        throw new NotImplementedException("findById");
        throw new NotImplementedException();
    }

    @Override
    public Singer save(Singer singer) {
        throw new NotImplementedException();
//        throw new NotImplementedException("save");
    }

    @Override
    public void delete(Singer singer) {
        throw new NotImplementedException();
//        throw new NotImplementedException("delete");
    }

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        throw new NotImplementedException();
//        throw new NotImplementedException("findAllByNativeQuery");
    }
}