package com.highfive.refurmoa.pay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Delivery;

import jakarta.transaction.Transactional;

public interface DeliveryRepository extends JpaRepository <Delivery, Integer> {
    Delivery findByProductProductCode(int productCode);
    
    // 배송중
    @Query("SELECT COUNT(d) FROM Delivery d " +
            "WHERE d.deliDate IS NULL " +
            "AND d.deliNum IS NOT NULL"
    )
    Long getAdminCountShipping();

    // 배송완료
    @Query("SELECT COUNT(d) FROM Delivery d LEFT JOIN product p " +
            "WHERE d.deliDate IS NOT NULL " +
            "AND p.prodState != 5"
    )
    Long getAdminCountCompleted();

    // 상품준비중
    @Query("SELECT COUNT(d) FROM Delivery d LEFT JOIN payment p " +
            "WHERE d.deliNum IS NULL " +
            "AND p.payCancel = FALSE"
    )
    Long getPrepareCount();
    
    @Query("SELECT d FROM Delivery d LEFT JOIN payment p  " +
            "WHERE d.receiptName like %:search% " +
            "or d.receiptPhone like %:search% "+
            "or p.payNum like %:search% "+
            " Order by  d.deliNum ASC, p.payNum DESC" 
    )
    Page<Delivery> findOrder(@Param("search")String search,Pageable pageable);
    
    @Transactional
    @Modifying
    @Query("UPDATE Delivery d SET d.deliNum =:deli  WHERE d.num = :num")
    void updatDeliNum(@Param("num") int num,@Param("deli") String deli); // 판매 글 삭제
}
