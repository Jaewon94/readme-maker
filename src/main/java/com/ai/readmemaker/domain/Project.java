package com.ai.readmemaker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 프로젝트 이름

    @Column(nullable = false)
    private String title; // 프로젝트 제목

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String mainFeatures;

    @Column(nullable = false)
    private String installationGuide;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}