package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void addOne(Issue issue) {
        repository.save(issue);
    }

    public boolean addAll(Collection<? extends Issue> issues) {
        return repository.addAll(issues);
    }

    public List<Issue> findOpened() {
        List<Issue> items = new ArrayList<>();
        List<Issue> issues = repository.getAll();
        for (Issue issue : issues) {
            if (issue.isOpen()) {
                items.add(issue);
            }
        }
        return items;
    }

    public List<Issue> findClosed() {
        List<Issue> items = new ArrayList<>();
        List<Issue> issues = repository.getAll();
        for (Issue issue : issues) {
            if (!issue.isOpen()) {
                items.add(issue);
            }
        }
        return items;
    }

    public Collection<Issue> filterByAuthorName(String authorName) {
        Collection<Issue> issues = new ArrayList<>();
        repository.getAll()
                .stream().filter(issue -> issue.getAuthorName().equals(authorName))
                .forEach(issue -> issues.add(issue));
        return issues;
    }

    public Collection<Issue> filterByLabel(Set<String> label) {
        Collection<Issue> issues = new ArrayList<>();
        repository.getAll()
                .stream().filter(issue -> issue.getLabel().containsAll(label))
                .forEach(issue -> issues.add(issue));
        return issues;
    }

    public Collection<Issue> filterByAssignee(Set<String> assignee) {
        Collection<Issue> issues = new ArrayList<>();
        repository.getAll()
                .stream().filter(issue -> issue.getAssignee().containsAll(assignee))
                .forEach(issue -> issues.add(issue));
        return issues;
    }

    public List<Issue> findSortedNewest() {
        List<Issue> issues = repository.getAll();
        issues.sort(Comparator.naturalOrder());
        return issues;
    }

    public List<Issue> findSortedOldest() {
        List<Issue> issues = repository.getAll();
        issues.sort(Comparator.reverseOrder());
        return issues;
    }

    public void update(Issue issue, boolean open) {
        issue.setOpen(open);
    }
}
