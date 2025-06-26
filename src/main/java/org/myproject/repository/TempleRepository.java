package org.myproject.repository;

import org.myproject.entity.TempleEntity;
import org.myproject.enums.District;
import org.myproject.enums.Province;
import org.myproject.enums.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempleRepository extends JpaRepository<TempleEntity, Long> {
    List<TempleEntity> findByTempleVillagesVillageId(Long villageId);
    List<TempleEntity> findByHeadMonksId(Long headMonkId);
    List<TempleEntity> findByHelpersId(Long helperId);
    void deleteByTempleVillagesVillageId(Long villageId);

    @Query("SELECT t FROM TempleEntity t WHERE " +
           "(:province is null OR t.province = :province) AND " +
           "(:district is null OR t.district = :district) AND " +
           "(:town is null OR t.town = :town)")
    List<TempleEntity> findByFilters(@Param("province") Province province,
                                    @Param("district") District district,
                                    @Param("town") Town town);
}
