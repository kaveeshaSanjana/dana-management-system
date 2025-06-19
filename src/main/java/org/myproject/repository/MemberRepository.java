package org.myproject.repository;

import org.myproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    List<MemberEntity> findByFamily_Id(Long familyId);
    MemberEntity findByEmail(String email);
    MemberEntity findByPhoneNumber(String phoneNumber);
}
