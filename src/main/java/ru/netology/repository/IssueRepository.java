package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> getAll() {
        return issues;
    }

    public boolean addAll(Collection<? extends Issue> issues) {
        return this.issues.addAll(issues);
    }
}

