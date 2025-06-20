package org.myproject.repository;

import org.myproject.entity.TempleDanaAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TempleDanaAssignmentRepository extends JpaRepository<TempleDanaAssignmentEntity, Long> {

    List<TempleDanaAssignmentEntity> findByFamilyId(Long familyId);

    @Query("SELECT tda FROM TempleDanaAssignmentEntity tda " +
           "LEFT JOIN FETCH tda.templeDana td " +
           "LEFT JOIN FETCH td.temple " +
           "LEFT JOIN FETCH td.dana " +
           "WHERE tda.family.id IN :familyIds")
    List<TempleDanaAssignmentEntity> findByFamilyIds(@Param("familyIds") List<Long> familyIds);

    Collection<TempleDanaAssignmentEntity> findByTempleDanaTempleIdAndTempleDanaDanaId(Long templeId, Long danaId);
}
