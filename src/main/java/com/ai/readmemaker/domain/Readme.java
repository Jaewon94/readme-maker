package com.ai.readmemaker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Readme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Lob
    @Column(nullable = false)
    private String content;

//    @Column(nullable = false)
//    private String introduction; // 소개
//    @Column(nullable = false)
//    private String installation; // 설치
//    @Column(name = "usage_info",nullable = false)
//    private String usage; // 사용 방법
//    @Column(nullable = false)
//    private String contributing; // 기여 방법
//    @Column(nullable = false)
//    private String license; // 라이센스
//    @Column(nullable = false)
//    private String contact; // 문의

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}