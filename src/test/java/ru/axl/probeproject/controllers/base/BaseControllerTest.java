package ru.axl.probeproject.controllers.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.axl.probeproject.config.TestConfig;
import ru.axl.probeproject.exceptions.GlobalExceptionHandler;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {GlobalExceptionHandler.class, TestConfig.class})
public abstract class BaseControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

}
