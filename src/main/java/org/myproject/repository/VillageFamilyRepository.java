package org.myproject.repository;

import org.myproject.entity.VillageFamilyEntity;
import org.myproject.entity.serializable.VillageFamilyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageFamilyRepository extends JpaRepository<VillageFamilyEntity, VillageFamilyId> {
    List<VillageFamilyEntity> findByVillageId(Long villageId);
    List<VillageFamilyEntity> findByFamilyId(Long familyId);
}
