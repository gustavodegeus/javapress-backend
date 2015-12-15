package br.com.javapress.infrastructure.webappinitializers;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import br.com.javapress.infrastructure.config.AppConfig;
import br.com.javapress.infrastructure.config.MvcConfig;
import br.com.javapress.infrastructure.config.SecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class, AppConfig.class, MvcConfig.class);
    }

}
