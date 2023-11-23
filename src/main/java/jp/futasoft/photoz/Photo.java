package jp.futasoft.photoz;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor  // Generates the default no-arg constructor
@AllArgsConstructor // Generates a constructor with all arguments
public class Photo {
    @NotEmpty
    private String id;
    @NotEmpty
    private String fileName;
}
