package jp.futasoft.photoz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor  // Generates the default no-arg constructor
@AllArgsConstructor // Generates a constructor with all arguments
public class Photo {
    private String id;
    private String fileName;
}
