package com.bank_example.user_service.infraestructure.in.http;

import com.bank_example.user_service.domain.usecases.CreateClientUseCase;
import com.bank_example.user_service.domain.usecases.GetClientByIdUseCase;
import com.bank_example.user_service.infraestructure.in.http.api.ClientsApiDelegate;
import com.bank_example.user_service.infraestructure.in.http.mappers.ClientApiMapper;
import com.bank_example.user_service.infraestructure.in.http.model.ClientResponse;
import com.bank_example.user_service.infraestructure.in.http.model.CreateCompanyRequest;
import com.bank_example.user_service.infraestructure.in.http.model.CreatePersonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ClientApiAdapter implements ClientsApiDelegate {

    private final ClientApiMapper clientApiMapper;
    private final GetClientByIdUseCase getClientByIdUseCase;
    private final CreateClientUseCase createClientUseCase;


    @Override
    public Mono<ResponseEntity<ClientResponse>> createCompany(Mono<CreateCompanyRequest> createCompanyRequest, ServerWebExchange exchange) {
        ZoneId zoneId = this.getTimezone(exchange);

        return createCompanyRequest
                .map(this.clientApiMapper::toCompany)
                .flatMap(this.createClientUseCase::createCompany)
                .map(user -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(this.clientApiMapper.toClientResponse(user, zoneId))
                );
    }

    @Override
    public Mono<ResponseEntity<ClientResponse>> createPerson(Mono<CreatePersonRequest> createPersonRequest, ServerWebExchange exchange) {
        ZoneId zoneId = this.getTimezone(exchange);

        return createPersonRequest
                .map(this.clientApiMapper::toPerson)
                .flatMap(this.createClientUseCase::createPerson)
                .map(user -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(this.clientApiMapper.toClientResponse(user, zoneId))
                );
    }

    @Override
    public Mono<ResponseEntity<ClientResponse>> getClientById(String id, ServerWebExchange exchange) {

        ZoneId zoneId = this.getTimezone(exchange);

        return this.getClientByIdUseCase.getClientById(id)
                .map(user -> this.clientApiMapper.toClientResponse(user, zoneId))
                .map(ResponseEntity::ok);
    }


    private ZoneId getTimezone(ServerWebExchange exchange) {

        String timezoneHeader = exchange.getRequest().getHeaders().getFirst("X-Timezone");
        if (timezoneHeader != null) {
            return ZoneId.of(timezoneHeader);
        } else {
            return ZoneId.systemDefault();
        }
    }
}
