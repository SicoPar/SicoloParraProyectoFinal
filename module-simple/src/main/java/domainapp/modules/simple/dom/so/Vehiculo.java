package domainapp.modules.simple.dom.so;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.annotations.Column;
import java.util.Comparator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;




import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;

import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.Publishing;
import org.apache.causeway.applib.annotation.TableDecorator;

import org.apache.causeway.applib.jaxb.PersistentEntityAdapter;

import org.apache.causeway.applib.services.message.MessageService;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.services.title.TitleService;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Modelo;

//@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "simple")
//@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="id")
//
//@javax.jdo.annotations.Unique(name = "SimpleObject__name__UNQ", members = { "name" })


@PersistenceCapable(
	    schema = "simple",
	    identityType=IdentityType.DATASTORE)
	@Unique(

	        name = "Vehiculo__Usuario_name__UNQ", members = {"id"})
	


@Version(strategy= VersionStrategy.DATE_TIME, column="version")
@Named(SimpleModule.NAMESPACE + ".Vehiculo")
@DomainObject(entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout(tableDecorator = TableDecorator.DatatablesNet.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Vehiculo implements Comparable<Vehiculo>{



    @Inject @NotPersistent RepositoryService repositoryService;
    @Inject @NotPersistent TitleService titleService;
    @Inject @NotPersistent MessageService messageService;



    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", allowsNull = "true")
	@Getter @Setter                                             
	@PropertyLayout(fieldSetId = "metadata", sequence = "1")    
	private Long id;





//	
//	   @Version
//	    @Column(name = "version", allowsNull = "false")
//	    @PropertyLayout(fieldSetId = "metadata", sequence = "999")
//	    @Getter @Setter
//	    private long version;

	
	   Vehiculo(Usuario Usuario, String Modelo) {
	        this.usuario = Usuario;
	        this.modelo = Modelo;
	    }
	   
	   
	   @ManyToOne(optional = false)
	    @JoinColumn(name = "usuario_id")
	    @PropertyLayout(fieldSetId = "name", sequence = "1")
	    @Getter @Setter
	    private Usuario usuario;
	   
	   @Modelo
	    @Column(name = "modelo", length = Modelo.MAX_LEN, allowsNull = "false")
	    @Getter @Setter
	    @PropertyLayout(fieldSetId = "name", sequence = "2")
	    private String modelo;



	   private final static  Comparator<Vehiculo> comparator =
	            Comparator.comparing(Vehiculo::getUsuario).thenComparing(Vehiculo::getModelo);
  

	    @Override
	    public int compareTo( Vehiculo other) {
	        return comparator.compare(this, other); 
	    }
	

}

    
    
    
    
    
    
    
  