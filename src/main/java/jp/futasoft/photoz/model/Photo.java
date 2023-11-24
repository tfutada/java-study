package jp.futasoft.photoz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photo")
@Data
@NoArgsConstructor  // Generates the default no-arg constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Builder
public class Photo {
    @Id
    private Long id;
    @NotEmpty
    private String fileName;
}
