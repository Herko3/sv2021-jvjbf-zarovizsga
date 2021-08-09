package org.training360.finalexam.players;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePlayerCommand {

    @NotBlank(message = "name must not be empty")
    private String name;

    @Past
    @JsonProperty
    private LocalDate birthDate;

    @JsonProperty
    private PositionType position;
}
