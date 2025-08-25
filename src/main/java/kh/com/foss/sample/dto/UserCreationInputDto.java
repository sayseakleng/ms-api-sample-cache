package kh.com.foss.sample.dto;

import kh.com.foss.sample.constant.GenderType;
import lombok.Data;

@Data
public class UserCreationInputDto {
    private String phone;
    private String firstName;
    private String lastName;
    private GenderType gender;
}
