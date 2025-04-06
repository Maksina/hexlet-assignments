package exercise.mapper;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.model.Author;
import exercise.model.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingTarget;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AuthorMapper {

    // BEGIN
    public abstract Author map(AuthorCreateDTO dto);
    public abstract AuthorDTO map(Author model);
    // END

    public abstract void update(AuthorUpdateDTO dto, @MappingTarget Author model);
}
