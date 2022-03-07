package uz.pdp.coding_bat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.coding_bat.entity.Category;
import uz.pdp.coding_bat.entity.Users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProblemDTO {



    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "solution is required")
    private String solution;

    @NotBlank(message = "category is required")
    private Integer categoryId;

    @NotBlank(message = "user is required")
    private Integer userId;
}
