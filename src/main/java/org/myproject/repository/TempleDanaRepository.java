package org.myproject.repository;

import org.myproject.entity.TempleDanaEntity;
import org.myproject.entity.serializable.TempleDanaEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempleDanaRepository extends JpaRepository<TempleDanaEntity, TempleDanaEntityId> {
    List<TempleDanaEntity> findByTempleId(Long templeId);
    List<TempleDanaEntity> findByDanaId(Long danaId);
    void deleteByTempleId(Long templeId);
    void deleteByDanaId(Long danaId);
}
