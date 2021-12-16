package com.codegym.quanlyidol.service.idol;

import com.codegym.quanlyidol.model.Idol;
import com.codegym.quanlyidol.repository.IIdolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IdolService implements IIdolService{

    @Autowired
    private IIdolRepository idolRepository;
    @Override
    public Idol save(Idol idol) {
        return idolRepository.save(idol);
    }

    @Override
    public Iterable<Idol> findAll() {
        return idolRepository.findAll();
    }

    @Override
    public Optional<Idol> findById(Long id) {
        return idolRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        idolRepository.deleteById(id);
    }
}
