package codeworld.tutorial.configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import codeworld.tutorial.service.FilmService;

@Configuration
@ComponentScan(basePackages = "codeworld.tutorial.*")
public class ClientConfiguration {

    private <C> C createClient(Class<C> service, String url) {
        ClientConfig cc = new ClientConfig().register(ObjectMapper.class);
        Client resource = ClientBuilder.newClient(cc);
        return WebResourceFactory.newResource(service, resource.target(url));
    }

    @Bean
    public FilmService filmService() {
        return createClient(FilmService.class, "http://localhost:8077/");
    }

}
