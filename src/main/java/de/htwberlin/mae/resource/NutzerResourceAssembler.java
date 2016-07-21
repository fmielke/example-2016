package de.htwberlin.mae.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import de.htwberlin.mae.controller.NutzerBulkController;
import de.htwberlin.mae.model.Customer;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@SuppressWarnings("rawtypes")
@Component
public class NutzerResourceAssembler extends ResourceAssemblerSupport<Customer, Resource> {

    public NutzerResourceAssembler() {
        super(NutzerBulkController.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends Customer> customerList) {
        List<Resource> resources = new ArrayList<Resource>();
        for(Customer customer : customerList) {
            resources.add(new Resource<Customer>(customer, linkTo(NutzerBulkController.class).slash(customer.getCustomerId()).withSelfRel()));
        }
        return resources;
    }

    @Override
    public Resource toResource(Customer customer) {
        return new Resource<Customer>(customer, linkTo(NutzerBulkController.class).slash(customer.getCustomerId()).withSelfRel());
    }
}
