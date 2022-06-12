package io.jjong.idehttptest.api;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 2022/06/12. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */

@RestController
@Slf4j
@RequestMapping("/")
public class TestApi {
  private static Set<String> tokens = new HashSet<>();

  @GetMapping("/welcome")
  public String welcome() {
    return "welcome my homepage";
  }

  @GetMapping("/token")
  public String token() {
    String token = UUID.randomUUID().toString();
    tokens.add(token);
    return token;
  }

  @GetMapping("/auth")
  public String auth(@RequestHeader("http-token") String token) {
    if (!tokens.contains(token)) {
      return deny();
    }
    return "you has been authorized.";
  }


  @GetMapping("/deny")
  public String deny() {
    return "you does not have authority to call.";
  }

}
