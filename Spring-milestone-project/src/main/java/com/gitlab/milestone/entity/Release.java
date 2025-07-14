package com.gitlab.milestone.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "release", uniqueConstraints = @UniqueConstraint(columnNames = {"tag", "milestone_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tag;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;
}