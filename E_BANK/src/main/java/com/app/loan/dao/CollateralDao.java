package com.app.loan.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.loan.entities.Collateral;
import com.app.loan.entities.Request;
@Repository
public interface CollateralDao extends JpaRepository<Collateral, String>{
	@Query("SELECT c FROM Collateral c WHERE c.request = :request")
    Collateral findByRequest(@Param("request") Request request);
}
