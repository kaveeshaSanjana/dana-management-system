package org.myproject.repository;

import org.myproject.entity.DanaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanaRepository extends JpaRepository<DanaEntity, Long> {
    List<DanaEntity> findByTempleDanasTempleId(Long templeId);
}
