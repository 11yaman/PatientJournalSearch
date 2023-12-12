package com.example.mapping;

import com.example.dto.UserDto;
import com.example.model.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserMapper implements StrategyMapper<User, UserDto> {

    @Override
    public UserDto map(User source) {
        return new UserDto(source.getId(), source.getUsername(),
                source.getFirstName(),source.getLastName(),
                source.getBirthDate(), source.getRole().name());
    }

    @Override
    public List<UserDto> mapAll(List<User> source) {
        List<UserDto> UserDtos = new ArrayList<>();
        for (User p:
                source) {
            UserDtos.add(map(p));
        }
        return UserDtos;
    }
}
