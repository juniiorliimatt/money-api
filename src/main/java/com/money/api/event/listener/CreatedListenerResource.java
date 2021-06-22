package com.money.api.event.listener;

import com.money.api.event.CreatedEventResource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@Component
public class CreatedListenerResource implements ApplicationListener<CreatedEventResource> {

  @Override
  public void onApplicationEvent(CreatedEventResource createdEventResource) {
    HttpServletResponse response = createdEventResource.getResponse();
    Long code = createdEventResource.getCode();

    addHeaderLocation(response, code);
  }

  private void addHeaderLocation(HttpServletResponse response, Long code){
    var uri =
      ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(code).toUri();
    response.setHeader("Location", uri.toASCIIString());
  }
}
