package velykyi.vladyslav.annotations.dto;

import org.junit.jupiter.api.Test;
import velykyi.vladyslav.annotations.annotation.validation.ContactNumberConstraint;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ValidatedPhoneTest {

    @Test
    void shouldBePresentCustomAnnotation() throws NoSuchFieldException {
        String expected = ValidatedPhone.class.getDeclaredField("phone")
                                              .getAnnotation(ContactNumberConstraint.class)
                                              .toString();

        assertThat(expected, is("@velykyi.vladyslav.annotations.annotation.validation.ContactNumberConstraint(" +
                                "message=\"Invalid phone number\", groups={}, payload={})"));
    }
}
