package com.apress.prospring5.ch7.dao;

import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import java.util.List;

public interface InstrumentDao {
    List<Instrument> findAll();
    Instrument findById(Long id);
    Instrument save(Instrument instrument);
    void delete(Instrument instrument);
}
