package domainapp.modules.simple.dom.so;

import static org.apache.causeway.applib.annotation.SemanticsOf.IDEMPOTENT;

import javax.inject.Inject;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.Publishing;
import org.apache.causeway.applib.services.repository.RepositoryService;

import domainapp.modules.simple.types.Modelo;
import lombok.RequiredArgsConstructor;

@Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)

@ActionLayout(associateWith = "Vehiculo")
@RequiredArgsConstructor
public class Usuario_addvehiculo {

	private final Usuario usuario;

	public Usuario act(@Modelo final String modelo

	) {
		repositoryService.persist(new Vehiculo(usuario, modelo));
		return usuario;
	}

	@Inject
	RepositoryService repositoryService;
}