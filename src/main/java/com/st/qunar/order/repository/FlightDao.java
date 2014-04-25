package com.st.qunar.order.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.st.qunar.order.entity.Flight;

public interface FlightDao extends PagingAndSortingRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

}