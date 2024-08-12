package com.dev.customerservice.infraestructure.data.repository;

import com.dev.customerservice.infraestructure.data.entities.Cliente;
import com.dev.customerservice.infraestructure.data.entities.QCliente;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "clientes-api", collectionResourceRel = "clientes")
public interface ClienteRepository extends JpaRepository<Cliente, Long>,
        QuerydslPredicateExecutor<Cliente>, QuerydslBinderCustomizer<QCliente>{

    @Override
    default void customize(
            QuerydslBindings bindings, QCliente root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    @Query(value = "SELECT nextval('cliente_cliente_id_seq')", nativeQuery = true)
    Long getNextClienteIdSequenceValue();

   // List<Cliente> findByPersonaId(Long personaId);

    //Cliente findByNumeroCuenta(String numeroCuenta);



}
