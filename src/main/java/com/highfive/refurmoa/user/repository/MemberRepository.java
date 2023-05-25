package com.highfive.refurmoa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.user.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
	
	
	@Query(value="select member_id from member where member_id=:id",nativeQuery=true)
	String getId(@Param("id")String id);
	
	@Query(value="select password from member where member_id=:id",nativeQuery=true)
	String getPw(@Param("id") String id);
}
