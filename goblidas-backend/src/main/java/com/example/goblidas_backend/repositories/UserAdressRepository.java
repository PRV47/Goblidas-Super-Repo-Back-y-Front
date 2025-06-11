package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.UserAdress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAdressRepository extends BaseRepository<UserAdress, Long> {


    @Query("SELECT ua FROM UserAdress ua WHERE ua.userId.id = :userId")
    List<UserAdress> findByUserId(@Param("userId") Long userId);
}
