package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class Issue implements Comparable<Issue> {
    private int id;
    private int daysAgo;
    private String authorName;
    private Set<String> label;
    private Set<String> assignee;
    private boolean isOpen;

    @Override
    public int compareTo(Issue o) {
        return this.daysAgo - o.daysAgo;
    }
}
