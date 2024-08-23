package org.au.careercove.api.data.config;

import org.au.careercove.api.data.model.Account;
import org.au.careercove.api.data.model.Application;
import org.au.careercove.api.data.model.Internship;
import org.au.careercove.api.data.model.Job;
import org.au.careercove.api.data.model.User;
import org.au.careercove.api.data.model.Workshop;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Account.class);
        config.exposeIdsFor(Job.class);
        config.exposeIdsFor(Internship.class);
        config.exposeIdsFor(Workshop.class);
        config.exposeIdsFor(Application.class);
    }
}
