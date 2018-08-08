package com.foodtruckclub.foodtruck.models.data;

import com.foodtruckclub.foodtruck.models.Home;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HomeDao extends CrudRepository<Home, Integer> {

}
