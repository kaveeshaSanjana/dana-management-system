package org.myproject.repository;

import org.myproject.entity.HeadMonkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadMonkRepository extends JpaRepository<HeadMonkEntity, Long> {
    List<HeadMonkEntity> findByTempleId(Long templeId);
    void deleteByTempleId(Long templeId);
    HeadMonkEntity findByEmail(String email);
    HeadMonkEntity findByPhoneNumber(String phoneNumber);
}
