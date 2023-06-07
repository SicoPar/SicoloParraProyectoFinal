package domainapp.modules.simple.dom.so;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.JDOQLTypedQuery;

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

import lombok.RequiredArgsConstructor;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Documento;
import domainapp.modules.simple.types.Email;
import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Nombre;
import domainapp.modules.simple.types.Telefono;

@Named(SimpleModule.NAMESPACE + ".SimpleObjects")
@DomainService(nature = NatureOfService.VIEW)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Usuarios {

    final RepositoryService repositoryService;
    final JdoSupportService jdoSupportService;


    
    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_MODAL)
    public Usuario create(
            @Name final String name,
            @Nombre final String nombre,
            @Documento final String documento,
            @Email final String email,
            @Telefono final String telefono
    		) {
        return repositoryService.persist(Usuario.withName(name,nombre,documento,telefono,email));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, promptStyle = PromptStyle.DIALOG_MODAL)
    public List<Usuario> findByName(
            @Name final String name
            ) {
        return repositoryService.allMatches(
                    Query.named(Usuario.class, Usuario.NAMED_QUERY__FIND_BY_NAME_LIKE)
                        .withParameter("name", name));
    }


    public Usuario findByNameExact(final String name) {
        return repositoryService.firstMatch(
                    Query.named(Usuario.class, Usuario.NAMED_QUERY__FIND_BY_NAME_EXACT)
                        .withParameter("name", name))
                .orElse(null);
    }




    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Usuario> listAll() {
        return repositoryService.allInstances(Usuario.class);
    }



//    public void ping() {
//        JDOQLTypedQuery<SimpleObject> q = jdoSupportService.newTypesafeQuery(SimpleObject.class);
//        final QSimpleObject candidate = QSimpleObject.candidate();
//        q.range(0,2);
//        q.orderBy(candidate.name.asc());
//        q.executeList();
//    }

}
