package com.pokemon.studi.security.configuration;

import com.pokemon.studi.security.filter.JwtRequestFilter;
import com.pokemon.studi.security.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * Objet configuré de manière automatique par Spring, permet de filtrer les requetes HTTP avec le JWT Token.
     */
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * Necessaire par Spring afin de configurer la classe courante.
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     *
     * @param auth
     * @throws Exception
     * méthode surchargé de WebSecurityConfigurerAdapter , essentiel afin de pouvoir initiliaser l'authentification.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //Identification de l'utilisateur
        auth.userDetailsService(userDetailsServiceImpl);
    }

    /*
    <p>Permet de filtrer les requetes HTTP, donne accès uniquement au path /authenticate sans token.
     * Pour tous les autres path, il faut etre authentifier.</p>
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests().antMatchers("/api/authenticate")
                .permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /*
     * Autorise les accès aux Path énuméré sans authentification.
     */
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-ui.html",
                "/swagger-resources/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
