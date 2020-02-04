package fixture;

import com.godoineto.simplepipe.api.dto.UserDTO;

public class UserDTOFixture {

    public static UserDTO get() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("5e37150298c04776383f83a5");
        userDTO.setName("User Name");
        userDTO.setEmail("userDTO@email.com");
        return userDTO;
    }
}
