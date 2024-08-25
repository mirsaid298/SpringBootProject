package org.example.bootproject.repository;

import org.example.bootproject.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Teams, Long> {
    Optional<Teams> findByTeamName(String teamName);

    List<Teams> findTeamsByCategoryId(Long categoryId);
}
