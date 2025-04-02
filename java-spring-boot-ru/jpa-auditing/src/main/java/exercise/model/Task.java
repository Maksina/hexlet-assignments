package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;

// BEGIN
@Entity
@Table(name = "Tasks")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    private String description;

    @LastModifiedDate
    private LocalDate updatedAt;

    @CreatedDate
    private LocalDate createdAt;


}
// END
