package org.au.careercove.api.data.repository.processor;

import org.au.careercove.api.data.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor implements RepresentationModelProcessor<EntityModel<User>> {

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        
        model.add(
            Link.of(model.getLink("self").get().getHref().split("api/")[0] + "api/postings/{id}")
                .withRel(LinkRelation.of("posting"))
                .expand(model.getContent().getId()));
        
        return model;
    }
}
