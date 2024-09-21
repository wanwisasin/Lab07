package se331.rest.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Organizer;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder()
                .id(1L)
                .organizationName("Kat Laydee")
                .address("123 Feline Street, Meow Town")
                .build());
        organizerList.add(Organizer.builder()
                .id(2L)
                .organizationName("Fern Pollin")
                .address("456 Flora Avenue, Flora City")
                .build());
        organizerList.add(Organizer.builder()
                .id(3L)
                .organizationName("Carey Wales")
                .address("789 Ocean Drive, Playa Del Carmen")
                .build());
        organizerList.add(Organizer.builder()
                .id(4L)
                .organizationName("Dawg Dahd")
                .address("321 Woof Lane, Woof Town")
                .build());
        organizerList.add(Organizer.builder()
                .id(5L)
                .organizationName("Kahn Opiner")
                .address("654 Tin Street, Tin City")
                .build());
        organizerList.add(Organizer.builder()
                .id(6L)
                .organizationName("Brody Kill")
                .address("987 Highway 50, Rural Area")
                .build());
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public Page<Organizer> getOrganizers(Integer pageSize, Integer page) {
        int firstIndex = (page - 1) * pageSize;
        return new PageImpl<Organizer>(organizerList.subList(firstIndex, firstIndex + pageSize), PageRequest.of(page,pageSize), organizerList.size());
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream().filter(organizer -> organizer.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organizer save(Organizer organizer) {
        organizer.setId(organizerList.get(organizerList.size() - 1).getId()+1);
        organizerList.add(organizer);
        return organizer;
    }
}
