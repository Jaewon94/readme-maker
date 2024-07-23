package com.ai.readmemaker.repository;

import com.ai.readmemaker.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
