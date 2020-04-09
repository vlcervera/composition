package org.vlcervera.composition.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vlcervera.composition.domain.model.User;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private String company;
    private String phone;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                           .company(user.getCompany().getValue())
                           .name(user.getName().getValue())
                           .email(user.getEmail().getValue())
                           .phone(user.getPhone().getValue())
                           .build();
    }
}
