package org.myproject.repository;

import org.myproject.entity.VillageFamilyEntity;
import org.myproject.entity.serializable.VillageFamilyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VillageFamilyRepository extends JpaRepository<VillageFamilyEntity, VillageFamilyId> {
    List<VillageFamilyEntity> findByVillageId(Long villageId);
    List<VillageFamilyEntity> findByFamilyId(Long familyId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO village_family (temple_id, village_id, family_id) VALUES (:templeId, :villageId, :familyId)", nativeQuery = true)
    void assignFamilyToVillageManually(@Param("templeId") Long templeId,
                                     @Param("villageId") Long villageId,
                                     @Param("familyId") Long familyId);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM village_family WHERE temple_id = :templeId AND village_id = :villageId AND family_id = :familyId)", nativeQuery = true)
    boolean checkFamilyExistsInVillage(@Param("templeId") Long templeId,
                                     @Param("villageId") Long villageId,
                                     @Param("familyId") Long familyId);
}
