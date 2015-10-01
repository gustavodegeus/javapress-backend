package br.com.javapress.application.infrastructure.config.webappinitializers;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import br.com.javapress.application.infrastructure.config.AppConfig;
import br.com.javapress.application.infrastructure.config.MvcConfig;
import br.com.javapress.application.infrastructure.config.SecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class, AppConfig.class, MvcConfig.class);
    }

}
