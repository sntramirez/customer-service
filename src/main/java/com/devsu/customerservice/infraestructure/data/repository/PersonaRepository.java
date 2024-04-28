package com.devsu.customerservice.infraestructure.data.repository;

import com.devsu.customerservice.infraestructure.data.entities.Persona;
import com.devsu.customerservice.infraestructure.data.entities.QCliente;
import com.devsu.customerservice.infraestructure.data.entities.QPersona;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "personas", collectionResourceRel = "personas")
public interface PersonaRepository extends JpaRepository<Persona, Long>,
        QuerydslPredicateExecutor<Persona>, QuerydslBinderCustomizer<QPersona> {

    @Override
    default void customize(
            QuerydslBindings bindings, QPersona root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    Persona findByIdentificacion(String identificacion);
}
