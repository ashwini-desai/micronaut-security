package io.micronaut.security.token.multitenancy.principal

import io.micronaut.context.annotation.Requires
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.StartupEvent

import javax.inject.Inject
import javax.inject.Singleton

@Requires(property = 'spec.name', value = 'multitenancy.principal.gorm')
@Singleton
class Bootstrap implements ApplicationEventListener<StartupEvent> {

    @Inject
    BookService bookService

    @Override
    void onApplicationEvent(StartupEvent event) {

        bookService.save('sherlock', 'Sherlock diary')
        bookService.save('watson', 'Watson diary')
    }
}
