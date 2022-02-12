package velykyi.vladyslav.annotations.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import velykyi.vladyslav.annotations.annotation.validation.ContactNumberConstraint;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidatedPhone{

    @ContactNumberConstraint
    private String phone;

    /**
     * Required constructor for construction of instance.
     */
    public ValidatedPhone() {
    }

    public ValidatedPhone(String phone) {
        this.phone = phone;
    }
}
