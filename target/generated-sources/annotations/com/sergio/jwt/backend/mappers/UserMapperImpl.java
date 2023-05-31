package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.SignUpDto;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T04:04:21+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.surName( user.getSurName() );
        userDto.lastName( user.getLastName() );
        userDto.phone( user.getPhone() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( signUpDto.firstName() );
        user.surName( signUpDto.surName() );
        user.lastName( signUpDto.lastName() );
        user.phone( signUpDto.phone() );

        return user.build();
    }
}
