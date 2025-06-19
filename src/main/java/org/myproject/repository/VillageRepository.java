package org.myproject.repository;

import org.myproject.entity.VillageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<VillageEntity, Long> {
    List<VillageEntity> findByTempleVillagesTempleId(Long templeId);
    List<VillageEntity> findByVillageFamiliesFamilyId(Long familyId);
    void deleteByTempleVillagesTempleId(Long templeId);
}
