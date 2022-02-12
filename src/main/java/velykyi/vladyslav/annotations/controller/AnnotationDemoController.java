package velykyi.vladyslav.annotations.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velykyi.vladyslav.annotations.annotation.Traceable;
import velykyi.vladyslav.annotations.dto.ValidatedPhone;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("annotations")
public class AnnotationDemoController {

    @Traceable
    @PostMapping("/traceRequest")
    public String processAnnotationAspect(@RequestBody Map<String, String> input, HttpServletRequest request){
        return "success";
    }

    @PostMapping("/validatePhoneNumber")
    public String processPhoneNumberValidation(@Valid @RequestBody ValidatedPhone validatedPhone, BindingResult result) {
        if(result.hasErrors()) {
            return "invalid phone number";
        }

        return "valid phone number";
    }
}
