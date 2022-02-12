package velykyi.vladyslav.annotations.dto;

import org.junit.jupiter.api.Test;
import velykyi.vladyslav.annotations.annotation.validation.ContactNumberConstraint;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ValidatedPhoneTest {

    @Test
    void shouldBePresentCustomAnnotation() throws NoSuchFieldException {
        boolean annotationPresent = ValidatedPhone.class.getDeclaredField("phone")
                                                        .isAnnotationPresent(ContactNumberConstraint.class);

        assertThat(annotationPresent, is(true));
    }
}
