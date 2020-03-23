package org.vlcervera.composition.infrastructure.adapter.repository;

import com.google.common.collect.Maps;
import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Repository;
import org.vlcervera.composition.domain.User;
import org.vlcervera.composition.domain.port.repository.UserRepository;

import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<UUID, User> usersMap = Maps.newHashMap();

    public UserRepositoryImpl() {
        EasyRandom easyRandom = new EasyRandom();
        User       user       = easyRandom.nextObject(User.class);
        usersMap.put(user.getId(), user);
    }

    @Override
    public User findById(UUID userId) {
        return usersMap.get(userId);
    }
}
