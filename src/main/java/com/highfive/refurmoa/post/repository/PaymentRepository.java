package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // 결제 내역 조회
    Page<Payment> findByMemberMemberId(String memberId, Pageable pageable);

    // 결제 내역 기간 조회
    @Query("SELECT p FROM Payment p " +
            "WHERE p.member.memberId = :memberId " +
            "AND :startDate < p.payDate " +
            "AND p.payDate < :endDate"
    )
    Page<Payment> findByMemberIdAndPeriod(String memberId, Date startDate, Date endDate, Pageable pageable);

    // 결제 내역 검색
    @Query("SELECT p FROM Payment p " +
            "WHERE p.member.memberId = :memberId " +
            "AND p.product.prodName like %:search%"
    )
    Page<Payment> findByMemberIdAndSearch(String memberId, String search, Pageable pageable);

    // 상품 코드로 결제 내역 조회
    Payment findByProductProductCode(int productCode);

    // 하루 경매 매출액
    @Query("SELECT SUM(p.payPrice) / 1000 FROM Payment p LEFT JOIN board b " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND b.sellType != 2 " +
            "AND p.prodPrice != b.directPrice"
    )
    Long sumDailyAuction(Date startDate, Date endDate);

    // 하루 즉시구매 매출액
    @Query("SELECT SUM(p.payPrice) / 1000 FROM Payment p LEFT JOIN board b " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND b.sellType != 1 " +
            "AND p.prodPrice = b.directPrice"
    )
    Long sumDailyDirect(Date startDate, Date endDate);

    // 하루 취소금액
    @Query("SELECT SUM(p.payPrice) / 1000 FROM Payment p " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND p.payCancel"
    )
    Long sumCancel(Date startDate, Date endDate);

    // 하루 판매건수
    @Query("SELECT COUNT(p) FROM Payment p " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND NOT p.payCancel"
    )
    Long sumCount(Date startDate, Date endDate);
}
