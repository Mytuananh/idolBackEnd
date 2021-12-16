package com.codegym.quanlyidol.repository;

import com.codegym.quanlyidol.model.Idol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIdolRepository extends JpaRepository<Idol, Long> {
}
