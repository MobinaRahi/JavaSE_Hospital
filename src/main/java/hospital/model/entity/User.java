package hospital.model.entity;

import com.google.gson.Gson;
import hospital.model.entity.enums.Role;
import hospital.model.entity.enums.UserName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter


public class User {
    private int id;
    private String name;
    private String family;
    private LocalDate birthDate;
    private Role role;
    private Boolean status;
    private UserName username;
    private String password;
    private String nickname;
    private Boolean locked;
    private LocalDate registerDate;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
