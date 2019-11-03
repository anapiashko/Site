package com.anapiashko.site.config;

import com.anapiashko.site.domain.Author;
import com.anapiashko.site.repos.AuthorRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor( AuthorRepo authorRepo) {
        return map -> {
            String id = (String) map.get("sub");

            Author author = authorRepo.findById(id).orElseGet(() -> {
                Author newAuthor = new Author();

                newAuthor.setId(id);
                newAuthor.setName((String) map.get("name"));
                newAuthor.setEmail((String) map.get("email"));

                return newAuthor;
            });

            return authorRepo.save(author);

        };
    }
}