package br.com.picpay.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("PicPay Challange")
        .description("PicPay Challange")
        .version("v1")
        .contact(new Contact().name("Willian").email("willian.java@gmail.com"))
      )
      .tags(Arrays.asList(
        new Tag().name("Users").description("Manager users"),
        new Tag().name("Transactions").description("Realize transaction")
      ));
  }
}
