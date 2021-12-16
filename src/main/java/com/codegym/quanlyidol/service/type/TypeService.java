package com.codegym.quanlyidol.service.type;

import com.codegym.quanlyidol.model.Type;
import com.codegym.quanlyidol.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TypeService implements ITypeService{

    @Autowired
    private ITypeRepository typeRepository;
    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }
}
