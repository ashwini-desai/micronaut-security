package io.micronaut.security.config

import io.micronaut.context.ApplicationContext
import io.micronaut.security.handlers.AuthenticationMode
import spock.lang.Specification
import spock.lang.Unroll

class SecurityConfigurationAuthenticationSpec extends Specification {

    void "by default no login handler mode is set"() {
        when:
        ApplicationContext applicationContext = ApplicationContext.run([:])

        then:
        applicationContext.containsBean(SecurityConfiguration)
        !applicationContext.getBean(SecurityConfiguration).getAuthentication()

        cleanup:
        applicationContext.close()
    }

    @Unroll
    void "if property micronaut.security.authentication = #mode io.micronaut.security.config.SecurityConfiguration#getAuthentication = #mode"(AuthenticationMode mode) {
        when:
        ApplicationContext applicationContext = ApplicationContext.run(['micronaut.security.authentication': mode.toString()])

        then:
        applicationContext.containsBean(SecurityConfiguration)
        applicationContext.getBean(SecurityConfiguration).getAuthentication() == mode

        cleanup:
        applicationContext.close()

        where:
        mode << [AuthenticationMode.SESSION, AuthenticationMode.COOKIE, AuthenticationMode.BEARER]

    }
}
