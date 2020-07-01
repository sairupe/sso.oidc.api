import com.syriana.sso.oidc.api.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author syriana.zh
 * @date 2020/07/01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BcryTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        System.out.println(passwordEncoder.encode("client-a-secret"));
    }
}
