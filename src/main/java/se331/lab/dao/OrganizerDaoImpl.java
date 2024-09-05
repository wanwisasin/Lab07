package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Organizer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder()
                .id(123L)
                .address("Meow Town")
                .organizer("Kat Laydee")
                .build());

        organizerList.add(Organizer.builder()
                .id(456L)
                .address("Flora City")
                .organizer("Fern Pollin")
                .build());

        organizerList.add(Organizer.builder()
                .id(789L)
                .address("Playa Del Carmen")
                .organizer("Carey Wales")
                .build());

        organizerList.add(Organizer.builder()
                .id(1001L)
                .address("Woof Town")
                .organizer("Dawg Dahd")
                .build());

        organizerList.add(Organizer.builder()
                .id(1002L)
                .address("Tin City")
                .organizer("Kahn Opiner")
                .build());

        organizerList.add(Organizer.builder()
                .id(1003L)
                .address("Highway 50")
                .organizer("Brody Kill")
                .build());
    }

    @Override
    public Integer getOrganizerSize() {
        return 0;
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        int firstIndex = (page - 1) * pageSize;
        return organizerList.subList(firstIndex, firstIndex + pageSize);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream().filter(event ->
                event.getId().equals(id)).findFirst().orElse(null);
    }
}
