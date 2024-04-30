package com.epf.eventz.dao;

import com.epf.eventz.model.TypeMusique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeMusiqueDAO extends JpaRepository<TypeMusique, Long> {

}



