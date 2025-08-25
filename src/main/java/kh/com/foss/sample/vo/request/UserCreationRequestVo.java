package kh.com.foss.sample.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kh.com.foss.sample.constant.GenderType;
import lombok.Data;

@Data
public class UserCreationRequestVo {
    @NotBlank
    @Size(min = 9, max = 10)
    @Pattern(regexp = "^[0-9]*$", message = "Must be a valid number")
    private String phone;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private GenderType gender;
}
