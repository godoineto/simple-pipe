package fixture;

import com.godoineto.simplepipe.domain.User;

public class UserFixture {

    public static User get() {
        User user = new User();
        user.setId("5e37150298c04776383f83a5");
        user.setName("User Name");
        user.setEmail("user@email.com");
        return user;
    }
}
