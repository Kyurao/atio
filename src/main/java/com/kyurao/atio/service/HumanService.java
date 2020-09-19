package com.kyurao.atio.service;

import com.kyurao.atio.domain.Human;
import com.kyurao.atio.domain.Lot;
import com.kyurao.atio.repository.HumanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HumanService {

    private final HumanRepository humanRepository;

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public void createHuman(String name, int age) {
        Human human = new Human();
        human.setName(name);
        human.setAge(age);
        humanRepository.save(human);
    }

    public void createHuman(Human human) {
        humanRepository.save(human);
    }

    public String getHumanById(Long humanId) {
        return humanRepository.findById(humanId)
                .map(Human::toString)
                .orElseThrow(() -> new RuntimeException("Human with id=" + humanId + " is absent!"));
    }

    public List<Human> getAllPeople() {
        return humanRepository.findAll();
    }

    public String updateHumanById(Long humanId, String name, Integer age) {
        final Human human = humanRepository.findById(humanId).get();
        if (name != null) {
            human.setName(name);
        }
        if (age != null) {
            human.setAge(age);
        }

        humanRepository.save(human);

        return human.toString();

    }

    public void removeHumanById(Long humanId) {
        humanRepository.remove(humanId);
    }
}