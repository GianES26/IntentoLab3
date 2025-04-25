package com.example.intentolab3.repository;

import com.example.intentolab3.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE locations SET postal_code = ?1, city = ?2  WHERE location_id = ?3", nativeQuery = true)
    void actualizarLocationEmpleado(String postalCode, String city, int locationId);


}
