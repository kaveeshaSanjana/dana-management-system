package org.myproject.repository;

import org.myproject.entity.TempleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempleRepository extends JpaRepository<TempleEntity, Long> {
    List<TempleEntity> findByTempleVillagesVillageId(Long villageId);
    List<TempleEntity> findByHeadMonksId(Long headMonkId);
    List<TempleEntity> findByHelpersId(Long helperId);
    void deleteByTempleVillagesVillageId(Long villageId);
}
