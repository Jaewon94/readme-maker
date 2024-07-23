package com.ai.readmemaker.repository;

import com.ai.readmemaker.domain.Readme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadmeRepository extends JpaRepository<Readme, Long> {
}
