package com.sambaran.codecanvas.repository;

import com.sambaran.codecanvas.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Sambaran
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
            select p from Project p
            where p.deletedAt is NULL
            AND EXISTS(
                Select 1 from ProjectMember pm
                Where pm.id.userId = :userId
                AND pm.id.projectId = p.id            
            )
            ORDER BY p.updatedAt DESC
            """)
    List<Project> findAllAccessibleProjects(@Param("userId") Long userId);

    @Query("""
            SELECT p from Project p
            where p.id = :projectId 
            And p.deletedAt IS NULL
            AND EXISTS(
                Select 1 from ProjectMember pm
                Where pm.id.userId = :userId
                AND pm.id.projectId = p.id            
            )
            """)
    Optional<Project> findAccessibleProjectById(@Param("projectId") Long projectId,
                                                @Param("userId") Long  userId);

}
