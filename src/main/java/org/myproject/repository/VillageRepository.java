package org.myproject.repository;

import org.myproject.entity.VillageEntity;
import org.myproject.enums.Province;
import org.myproject.enums.District;
import org.myproject.enums.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<VillageEntity, Long> {
    List<VillageEntity> findByTempleVillagesTempleId(Long templeId);
    List<VillageEntity> findByVillageFamiliesFamilyId(Long familyId);
    void deleteByTempleVillagesTempleId(Long templeId);

    @Query("SELECT v FROM VillageEntity v WHERE " +
           "(:province is null OR v.province = :province) AND " +
           "(:district is null OR v.district = :district) AND " +
           "(:town is null OR v.town = :town)")
    List<VillageEntity> findByFilters(@Param("province") Province province,
                                     @Param("district") District district,
                                     @Param("town") Town town);
}
