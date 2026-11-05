package com.sambaran.codecanvas.repository;

import com.sambaran.codecanvas.entity.ProjectMember;
import com.sambaran.codecanvas.entity.ProjectMemberId;
import com.sambaran.codecanvas.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sambaran
 */
@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    @Query("""
        Select pm.projectRole from ProjectMember pm
        where pm.id.projectId= :projectId and
        pm.id.userId = :userId
    """)
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("projectId") Long projectId,@Param("userId") Long userId);

    @Query("""
            Select Count(pm) from ProjectMember pm
            where pm.id.userId = :userId AND pm.role = 'OWNER'
            """)
    int countProjectOwnedByUser(@Param("userId") Long userId);

}
