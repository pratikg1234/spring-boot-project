package com.gitlab.milestone.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "milestone",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "scope", "scope_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String state; // "active", "closed"

    @Column(nullable = false)
    private String scope; // "project" or "group"

    @Column(name = "scope_id", nullable = false)
    private Long scopeId;

    @OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Release> releases;
}
