package org.myproject.repository;

import org.myproject.entity.TempleVillageEntity;
import org.myproject.entity.serializable.TempleVillageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TempleVillageRepository extends JpaRepository<TempleVillageEntity, TempleVillageId> {
    List<TempleVillageEntity> findByTempleId(Long templeId);
    List<TempleVillageEntity> findByVillageId(Long villageId);
    Optional<TempleVillageEntity> findByTemple_IdAndVillage_Id(Long templeId, Long villageId);
}
