package de.htwberlin.mae.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import de.htwberlin.mae.controller.NutzerBulkController;
import de.htwberlin.mae.model.Nutzer;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@SuppressWarnings("rawtypes")
@Component
public class NutzerResourceAssembler extends ResourceAssemblerSupport<Nutzer, Resource> {

    public NutzerResourceAssembler() {
        super(NutzerBulkController.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends Nutzer> nutzerList) {
        List<Resource> resources = new ArrayList<Resource>();
        for(Nutzer nutzer : nutzerList) {
            resources.add(new Resource<Nutzer>(nutzer, linkTo(NutzerBulkController.class).slash(nutzer.getNutzerId()).withSelfRel()));
        }
        return resources;
    }

    @Override
    public Resource toResource(Nutzer nutzer) {
        return new Resource<Nutzer>(nutzer, linkTo(NutzerBulkController.class).slash(nutzer.getNutzerId()).withSelfRel());
    }
}
