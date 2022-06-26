package io.jjong.idehttptest.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * create on 2022/06/26. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RequestBodyDto {
  private String key;
  private String value;

}
