package org.myproject.repository;

import org.myproject.entity.TempleDanaAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempleDanaAssignmentRepository extends JpaRepository<TempleDanaAssignmentEntity, Long> {
    List<TempleDanaAssignmentEntity> findByTempleDanaTempleIdAndTempleDanaDanaId(Long templeId, Long danaId);
    List<TempleDanaAssignmentEntity> findByFamilyId(Long familyId);
    void deleteByTempleDanaTempleId(Long templeId);
    void deleteByFamilyId(Long familyId);
}
