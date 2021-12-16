package com.codegym.quanlyidol.repository;

import com.codegym.quanlyidol.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {
}
