package velykyi.vladyslav.annotations.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import velykyi.vladyslav.annotations.annotation.Traceable;
import velykyi.vladyslav.annotations.dto.ValidatedPhone;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static velykyi.vladyslav.annotations.Utils.mapObjectToJsonString;

@SpringBootTest
@AutoConfigureMockMvc
class AnnotationDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldProcessAnnotationAspect() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("1", "test");

        mockMvc.perform(post("/annotations/traceRequest")
                        .contentType(APPLICATION_JSON)
                        .content(mapObjectToJsonString(map)))
               .andExpect(status().isOk())
               .andExpect(content().string("success" + ": message from the annotation aspect"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789", "1234567891234"})
    void shouldAcceptPhoneNumberWithValidPhoneNumber(String phoneNumber) throws Exception {
        ValidatedPhone validatedPhone = new ValidatedPhone(phoneNumber);

        mockMvc.perform(post("/annotations/validatePhoneNumber")
                        .contentType(APPLICATION_JSON)
                        .content(mapObjectToJsonString(validatedPhone)))
               .andExpect(status().isOk())
               .andExpect(content().string("valid phone number"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"without numbers", "1234567", "123456789123456"})
    void shouldRejectPhoneNumberWithInvalidPhoneNumber(String phoneNumber) throws Exception {
        ValidatedPhone validatedPhone = new ValidatedPhone(phoneNumber);

        mockMvc.perform(post("/annotations/validatePhoneNumber")
                        .contentType(APPLICATION_JSON)
                        .content(mapObjectToJsonString(validatedPhone)))
               .andExpect(status().isOk())
               .andExpect(content().string("invalid phone number"));
    }

    @Test
    void shouldBePresentCustomAnnotation() throws NoSuchMethodException {
        boolean annotationPresent = AnnotationDemoController.class.getMethod("processAnnotationAspect",
                                                                             Map.class,
                                                                             HttpServletRequest.class)
                                                                   .isAnnotationPresent(Traceable.class);

        assertThat(annotationPresent, is(true));
    }
}
