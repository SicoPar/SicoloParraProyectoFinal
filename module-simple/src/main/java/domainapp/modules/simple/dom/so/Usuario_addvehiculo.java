package domainapp.modules.simple.dom.so;

import static org.apache.causeway.applib.annotation.SemanticsOf.IDEMPOTENT;

import javax.inject.Inject;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.Publishing;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;

import domainapp.modules.simple.types.Modelo;
import domainapp.modules.simple.types.Patente;
import lombok.RequiredArgsConstructor;

@Action(
		semantics = SemanticsOf.IDEMPOTENT , commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)

@ActionLayout(associateWith = "Vehiculos")
@RequiredArgsConstructor
public class Usuario_addvehiculo {

	private final Usuario usuario;

	public Usuario act(
			@Patente final String patente,
			@Modelo final String modelo) {
		repositoryService.persist(new Vehiculo(usuario, modelo,patente));
		return usuario;
	}

	@Inject
	RepositoryService repositoryService;
}