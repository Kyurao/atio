package com.kyurao.atio.repository;

import com.kyurao.atio.domain.Human;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HumanRepository {

    private static Map<Long, Human> people = new HashMap<>();
    private static IdGenerator generator;

    static {
        people.put(1L, new Human(1L, "Yura", 24));
        people.put(2L, new Human(2L, "Taras", 31));
        people.put(3L, new Human(3L, "Lilya", 21));
        people.put(4L, new Human(4L, "Kitc'ka", 1));

        generator = new IdGenerator(people.size() + 1);
    }

    public void save(Human human) {
        if (human.getId() == null) {
            human.setId(generator.generateId());
        }
        people.put(human.getId(), human);
    }

    public void remove(Long id) {
        people.remove(id);
    }

    public Optional<Human> findById(Long id) {
        return Optional.ofNullable(people.get(id));
    }

    public List<Human> findAll() {
        return new ArrayList<>(people.values());
    }

    @AllArgsConstructor
    private static class IdGenerator {
        private long id;

        public long generateId() {
            return id++;
        }
    }
}
