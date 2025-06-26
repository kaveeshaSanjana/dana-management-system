package org.myproject.repository;

import org.myproject.entity.TempleDanaEntity;
import org.myproject.entity.serializable.TempleDanaEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TempleDanaRepository extends JpaRepository<TempleDanaEntity, TempleDanaEntityId> {
    List<TempleDanaEntity> findByTempleId( Long templeId);
    List<TempleDanaEntity> findByDanaId(Long danaId);
    void deleteByTempleId(Long templeId);
    void deleteByDanaId(Long danaId);
}
