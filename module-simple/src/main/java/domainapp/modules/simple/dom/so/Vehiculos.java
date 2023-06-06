package domainapp.modules.simple.dom.so;


import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.NatureOfService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.query.Query;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.persistence.jdo.applib.services.JdoSupportService;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Documento;
import domainapp.modules.simple.types.Email;
import domainapp.modules.simple.types.Modelo;
import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Nombre;
import domainapp.modules.simple.types.Patente;
import domainapp.modules.simple.types.Telefono;
import lombok.RequiredArgsConstructor;

@Named(SimpleModule.NAMESPACE + ".Vehiculos")
@DomainService(nature = NatureOfService.VIEW)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Vehiculos {

    final RepositoryService repositoryService;
    final JdoSupportService jdoSupportService;


    
    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_MODAL)
    public Vehiculo create(
            @Patente final String patente,
            @Modelo final String modelo
           
            
    		) {
        return repositoryService.persist(Vehiculo.withName(patente,modelo));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, promptStyle = PromptStyle.DIALOG_MODAL)
    public List<Vehiculo> findByName(
           @Patente final String patente
            ) {
        return repositoryService.allMatches(
                   
       		Query.named(Vehiculo.class, Vehiculo.NAMED_QUERY__FIND_BY_NAME_LIKE)
                        .withParameter("patente",patente));
    }


    public Vehiculo findByNameExact(final String patente) {
        return repositoryService.firstMatch(
                    Query.named(Vehiculo.class, Vehiculo.NAMED_QUERY__FIND_BY_NAME_EXACT)
                        .withParameter("patente", patente))
                .orElse(null);
   }
    
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Vehiculo> listAll() {
        return repositoryService.allInstances(Vehiculo.class);
    }


//
//    @Action(semantics = SemanticsOf.SAFE)
//    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
//    public List<Usuario> listAll() {
//        return repositoryService.allInstances(Usuario.class);
//    }
}
