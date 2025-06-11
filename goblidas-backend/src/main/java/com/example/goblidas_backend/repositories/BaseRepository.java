package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {

}
