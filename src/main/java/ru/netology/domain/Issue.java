package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Set;

@AllArgsConstructor
@Data
public class Issue {
    int id;
    int daysAgo;
    String authorName;
    Set<String> label;
    Set<String> assignee;
    boolean isOpen;
}
