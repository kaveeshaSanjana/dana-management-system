package org.myproject.repository;

import org.myproject.entity.TempleDanaAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Query("SELECT tda FROM TempleDanaAssignmentEntity tda " +
           "LEFT JOIN FETCH tda.templeDana td " +
           "LEFT JOIN FETCH td.temple " +
           "LEFT JOIN FETCH td.dana " +
           "WHERE td.temple.id = :templeId AND tda.date = :date")
    Collection<TempleDanaAssignmentEntity> findByDateAndTemple(Long templeId, LocalDate date);

    @Query("SELECT tda FROM TempleDanaAssignmentEntity tda " +
           "LEFT JOIN FETCH tda.templeDana td " +
           "LEFT JOIN FETCH td.temple " +
           "LEFT JOIN FETCH td.dana " +
           "LEFT JOIN tda.family f " +
           "LEFT JOIN f.member m " +
           "WHERE td.temple.id = :templeId AND m.phoneNumber = :phoneNumber")
    List<TempleDanaAssignmentEntity> findByPhoneNumber(@Param("templeId") Long templeId,
                                                      @Param("phoneNumber") String phoneNumber);

    // Add method to update confirmation status
    @Query("UPDATE TempleDanaAssignmentEntity tda " +
           "SET tda.isConfirmed = :status " +
           "WHERE tda.id = :id")
    void updateConfirmationStatus(@Param("id") Long id, @Param("status") boolean status);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO temple_dana_assignments (dana_id, family_id, date, is_confirmed, temple_id) " +
           "VALUES (:danaId, :familyId, :date, 0, :templeId)", nativeQuery = true)
    void assignDana(@Param("templeId") Long templeId,
                   @Param("danaId") Long danaId,
                   @Param("familyId") Long familyId,
                   @Param("date") LocalDate date);
}
