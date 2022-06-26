package io.jjong.idehttptest.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


  @PostMapping(value = "/body-test", produces = TEXT_PLAIN_VALUE)
  public String testPost(@RequestBody RequestBodyDto dto) {
    log.info("request body : {}", dto);
    return dto.toString();
  }

  @PostMapping(value = "/upload", produces = TEXT_PLAIN_VALUE)
  public String uploadFile(@RequestParam MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    String contentType = file.getContentType();

    log.info("file name : {}", fileName);
    log.info("file content type : {}", contentType);

    if (contentType.equals(TEXT_PLAIN_VALUE)) {
      log.info("\r\n file content : {}", readFromInputStream(file.getInputStream()));
    }
    return "ok";
  }

  private String readFromInputStream(InputStream inputStream)
      throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br
        = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }

}
