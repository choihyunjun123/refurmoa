package com.highfive.refurmoa.admin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.ProdPartner;

import jakarta.transaction.Transactional;

public interface ProdPartnerRepository extends JpaRepository<ProdPartner, Integer>{
	 
     @Query(value="select * from prod_partner where com_name like CONCAT('%',:search,'%') and com_status=1 ",nativeQuery=true)
	 List<ProdPartner> findByComNameContaining(@Param("search")String search);
     
     @Query(value="select * from prod_partner where com_name like CONCAT('%',:search,'%')",nativeQuery=true)
	 List<ProdPartner> findComAdmin(@Param("search")String search);
     @Query(value="select * from prod_partner where com_num =:search",nativeQuery=true)
     ProdPartner findByComNum(@Param("search")int search);
     
     @Transactional
     @Modifying
     @Query(value="UPDATE prod_partner SET com_status = com_status + 1 WHERE com_num = :com_num",nativeQuery=true)
     void changState(@Param("com_num")int com_num);
}