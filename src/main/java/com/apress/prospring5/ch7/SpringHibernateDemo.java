package com.apress.prospring5.ch7;

import com.apress.prospring5.ch7.config.AppConfig;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import java.util.List;

public class SpringHibernateDemo {
    private static Logger logger =
            LoggerFactory.getLogger(SpringHibernateDemo.class);
    public static void main(String... args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        //singerDao.delete(singer);

        //System.out.println("singers:");
//        listSingers(singerDao.findAllWithAlbum());

        System.out.println("singers with album:");
//        listSingersWithAlbum(singerDao.findAll()); // lazy loading
        listSingersWithAlbum(singerDao.findAllWithAlbum()); // eager loading

        Long sid = 2l;
        System.out.println("singers by id " + sid +" with album:");
        Singer singer = singerDao.findById(sid);
        System.out.println(singer.toString());
        ctx.close();
    }
    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            System.out.println(singer.toString());
        }
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            System.out.println(singer.toString());
            if (singer.getAlbums() != null) {
                for (Album album :
                        singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                    System.out.println(album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId());
                    System.out.println(instrument.toString());
                }
            }
        }
    }
}

