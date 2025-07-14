package com.gitlab.milestone.service;

import com.gitlab.milestone.dto.*;
import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final MilestoneRepository milestoneRepository;

    public MilestoneSearchResponse searchMilestones(String title, String state, String scope, Long scopeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dueDate").descending());
        // For simplicity, only a subset of filters is implemented here.
        Page<Milestone> milestones = milestoneRepository.findAll(pageable);

        List<MilestoneResponse> results = milestones.getContent().stream()
                .map(m -> MilestoneResponse.builder()
                        .id(m.getId())
                        .title(m.getTitle())
                        .description(m.getDescription())
                        .startDate(m.getStartDate())
                        .dueDate(m.getDueDate())
                        .state(m.getState())
                        .scope(m.getScope())
                        .scopeId(m.getScopeId())
                        .build())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .page(page)
                .size(size)
                .totalElements(milestones.getTotalElements())
                .totalPages(milestones.getTotalPages())
                .build();

        return MilestoneSearchResponse.builder()
                .results(results)
                .pagination(pagination)
                .build();
    }
}
