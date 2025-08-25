package kh.com.foss.sample.vo.response;

import kh.com.foss.sample.constant.GenderType;
import lombok.Data;

@Data
public class UserCreationResponseVo {
    private Long userId;
    private String phone;
    private String firstName;
    private String lastName;
    private GenderType gender;
}
