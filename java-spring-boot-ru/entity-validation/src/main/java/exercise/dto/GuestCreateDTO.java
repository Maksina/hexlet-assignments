package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+\\d{11,13}$")
    private String phoneNumber;

    @Size(min = 4)
    @Size(max = 4)
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;

}
// END
