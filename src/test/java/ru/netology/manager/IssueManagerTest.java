package ru.netology.manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.IssueByTimeAscComparator;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private Comparator<Issue> comparator = new IssueByTimeAscComparator();
    private Issue issue1 = new Issue(1, 1, "splatch", Set.of("Jupiter", "extensions", "documentation"), Set.of("dsaff", "kcooney"), false);
    private Issue issue2 = new Issue(2, 3, "splatch", Set.of("Jupiter", "extensions", "documentation"), Set.of("dsaff", "kcooney"), false);
    private Issue issue3 = new Issue(3, 6, "splatch", Set.of("Kotlin", "Gradle", "Pioneer"), Set.of("dsaff", "kcooney"), false);
    private Issue issue4 = new Issue(4, 8, "leonard84", Set.of("Kotlin", "Gradle", "Pioneer"), Set.of("sbrannen"), false);
    private Issue issue5 = new Issue(5, 11, "nipafx", Set.of("Kotlin", "Gradle", "Pioneer"), Set.of("sbrannen"), false);

    @Nested
    public class Empty {
        @Test
        void shouldFindOpened() {
            manager.update(issue1, true);

            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findOpened();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosed() {
            manager.update(issue2, false);

            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindSortedNewest() {
            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findSortedNewest(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindSortedOldest() {
            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findSortedOldest(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthorName() {
            Collection<Issue> actual = manager.filterByAuthorName("nipafx");

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistAuthorName() {
            Collection<Issue> actual = manager.filterByAuthorName("splas");

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            Collection<Issue> actual = manager.filterByLabel(Set.of("Jupiter", "extensions", "documentation"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistLabel() {
            Collection<Issue> actual = manager.filterByLabel(Set.of("Jupit", "extensio", "documentat"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
            Collection<Issue> actual = manager.filterByAssignee(Set.of("dsaff", "kcooney"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistAssignee() {
            Collection<Issue> actual = manager.filterByAssignee(Set.of("dsaf", "kcoon"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {
        @Test
        void shouldFindOpened() {
            manager.addOne(issue1);
            manager.update(issue1, true);

            Collection<Issue> expected = new ArrayList<>();
            expected.add(issue1);

            List<Issue> actual = manager.findOpened();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpenedIfNotExist() {
            manager.addOne(issue1);
            manager.update(issue1, false);

            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findOpened();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosed() {
            manager.addOne(issue2);
            manager.update(issue2, false);

            Collection<Issue> expected = new ArrayList<>();
            expected.add(issue2);

            List<Issue> actual = manager.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfNotExist() {
            manager.addOne(issue2);
            manager.update(issue2, true);

            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindSortedNewest() {
            manager.addOne(issue3);

            Collection<Issue> expected = new ArrayList<>();
            expected.add(issue3);

            List<Issue> actual = manager.findSortedNewest(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindSortedOldest() {
            manager.addOne(issue4);

            Collection<Issue> expected = new ArrayList<>();
            expected.add(issue4);

            List<Issue> actual = manager.findSortedOldest(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthorName() {
            manager.addOne(issue5);

            Collection<Issue> actual = manager.filterByAuthorName("nipafx");

            Collection<Issue> expected = new ArrayList<>(List.of(issue5));

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistAuthorName() {
            manager.addOne(issue1);

            Collection<Issue> actual = manager.filterByAuthorName("splas");

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            manager.addAll(List.of(issue1));

            Collection<Issue> actual = manager.filterByLabel(Set.of("Jupiter", "extensions", "documentation"));

            Collection<Issue> expected = new ArrayList<>(List.of(issue1));

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistLabel() {
            manager.addAll(List.of(issue1));

            Collection<Issue> actual = manager.filterByLabel(Set.of("Jupit", "extensio", "documentat"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
            manager.addAll(List.of(issue1));

            Collection<Issue> actual = manager.filterByAssignee(Set.of("dsaff", "kcooney"));

            Collection<Issue> expected = new ArrayList<>(List.of(issue1));

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistAssignee() {
            manager.addAll(List.of(issue1));

            Collection<Issue> actual = manager.filterByAssignee(Set.of("dsaf", "kcoon"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItems {
        @Test
        void shouldFindOpened() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            manager.update(issue1, true);
            manager.update(issue2, true);
            manager.update(issue3, true);

            Collection<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);

            List<Issue> actual = manager.findOpened();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpenedIfNotExist() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findOpened();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosed() {
            manager.addAll(List.of(issue1, issue2, issue3));
            manager.update(issue1, false);
            manager.update(issue2, false);
            manager.update(issue3, false);

            Collection<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1, issue2, issue3));

            List<Issue> actual = manager.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfNotExist() {
            manager.addAll(List.of(issue1, issue2, issue3));
            manager.update(issue1, true);
            manager.update(issue2, true);
            manager.update(issue3, true);

            Collection<Issue> expected = new ArrayList<>();

            List<Issue> actual = manager.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindSortedNewest() {
            manager.addAll(List.of(issue4, issue2, issue3, issue1, issue5));

            Collection<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            List<Issue> actual = manager.findSortedNewest(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindSortedOldest() {
            manager.addAll(List.of(issue4, issue2, issue3, issue1, issue5));

            Collection<Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue5, issue4, issue3, issue2, issue1));

            List<Issue> actual = manager.findSortedOldest(comparator);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthorName() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            Collection<Issue> actual = manager.filterByAuthorName("splatch");

            Collection<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue3));

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistAuthorName() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            Collection<Issue> actual = manager.filterByAuthorName("splat");

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            Collection<Issue> actual = manager.filterByLabel(Set.of("Jupiter", "extensions", "documentation"));

            Collection<Issue> expected = new ArrayList<>(List.of(issue1, issue2));

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistLabel() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            Collection<Issue> actual = manager.filterByLabel(Set.of("Jupit", "extensio", "documentat"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            Collection<Issue> actual = manager.filterByAssignee(Set.of("dsaff", "kcooney"));

            Collection<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue3));

            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByNotExistAssignee() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4, issue5));

            Collection<Issue> actual = manager.filterByAssignee(Set.of("dsaf", "kcoon"));

            Collection<Issue> expected = new ArrayList<>();

            assertEquals(expected, actual);
        }
    }
}