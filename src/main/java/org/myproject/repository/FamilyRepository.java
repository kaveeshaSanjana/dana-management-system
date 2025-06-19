package org.myproject.repository;

import org.myproject.entity.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity, Long> {
    List<FamilyEntity> findByVillageFamilies_Id_TempleVillageId_VillageId(Long villageId);
    List<FamilyEntity> findByTempleDanaAssignments_TempleDana_Temple_Id(Long templeId);
}
