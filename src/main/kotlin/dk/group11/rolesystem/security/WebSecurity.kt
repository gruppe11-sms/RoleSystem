package dk.group11.rolesystem.security

import dk.group11.rolesystem.clients.AuditClient
import dk.group11.rolesystem.services.ISecretService
import dk.group11.rolesystem.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
class WebSecurity(private val userDetailsService: UserDetailsService,
                  private val bCryptPasswordEncoder: BCryptPasswordEncoder,
                  private val userService: UserService,
                  private val auditClient: AuditClient,
                  private val secretService: ISecretService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .cors()
                .and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/tokens").anonymous()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(AuthenticationFilter(authenticationManager(), userService, auditClient, secretService))
                .addFilter(AuthorizationFilter(authenticationManager(), secretService))
    }

    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val conf = CorsConfiguration()
        conf.addAllowedHeader("*")
        conf.addAllowedMethod("*")
        conf.addAllowedOrigin("*")
        source.registerCorsConfiguration("/**", conf)
        return source
    }
}