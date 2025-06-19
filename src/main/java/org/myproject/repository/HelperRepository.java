package org.myproject.repository;

import org.myproject.entity.HelperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelperRepository extends JpaRepository<HelperEntity, Long> {
    List<HelperEntity> findByTempleId(Long templeId);
    HelperEntity findByEmail(String email);
    HelperEntity findByPhoneNumber(String phoneNumber);
}
