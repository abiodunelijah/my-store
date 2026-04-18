package com.abiodunelijah.mappers;

import com.abiodunelijah.users.dtos.UserDto;
import com.abiodunelijah.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
