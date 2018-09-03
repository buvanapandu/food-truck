package com.foodtruckclub.foodtruck.models.data;

import com.foodtruckclub.foodtruck.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LocationDao extends CrudRepository<Location, Integer> {
}
