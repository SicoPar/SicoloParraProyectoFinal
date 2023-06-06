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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.val;
import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Modelo;
import domainapp.modules.simple.types.Patente;

//@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "simple")
//@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="id")
//
//@javax.jdo.annotations.Unique(name = "SimpleObject__name__UNQ", members = { "name" })



//@Entity
//@Table(
//   schema="Vehiculos",
//   uniqueConstraints = {
//        @UniqueConstraint(name = "Pet__owner_name__UNQ", columnNames = {"owner_id, name"})
//    }
//)
//


@PersistenceCapable(
	    schema = "simple",
	    identityType=IdentityType.DATASTORE)
	@Unique(name = "Vehiculo__Usuario_name__UNQ", members = {"patente"})
	


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


    public static Vehiculo withName(String patente) {
		return withName(patente, null
				);
	}
    
	public static Vehiculo withName(String patente, String modelo) {
		val simpleObject = new Vehiculo();
		simpleObject.setPatente(patente);
		simpleObject.setModelo(modelo);
		
		return simpleObject;
	}
    
    
    
    
	@Patente
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patente", allowsNull = "true")
	@Getter @Setter                                             
	@PropertyLayout(fieldSetId = "metadata", sequence = "1")    
	private String patente;
	

	

	  



//	
//	   @Version
//	    @Column(name = "version", allowsNull = "false")
//	    @PropertyLayout(fieldSetId = "metadata", sequence = "999")
//	    @Getter @Setter
//	    private long version;

	
	   Vehiculo(Usuario Usuario, String Modelo,String Patente) {
		   this.patente=Patente;
	        this.usuario = Usuario;
	        this.modelo = Modelo;
	    }
	   
	   
	   @ManyToOne(optional = false)
	    @JoinColumn(name = "id")
	    @PropertyLayout(fieldSetId = "name", sequence = "2")
	    @Getter @Setter
	    private Usuario usuario;
	   
	   
	   
	   @Modelo
	    @Column(name = "modelo", length = Modelo.MAX_LEN, allowsNull = "false")
	    @Getter @Setter
	    @PropertyLayout(fieldSetId = "name", sequence = "3")
	    private String modelo;



	   private final static  Comparator<Vehiculo> comparator =
	            Comparator.comparing(Vehiculo::getUsuario).thenComparing(Vehiculo::getModelo);
	
	   static final String NAMED_QUERY__FIND_BY_NAME_LIKE = "SimpleObject.findByNameLike";
	
	   static final String NAMED_QUERY__FIND_BY_NAME_EXACT = "SimpleObject.findByNameExact";
  

	    @Override
	    public int compareTo( Vehiculo other) {
	        return comparator.compare(this, other); 
	    }
	

}

    
    
    
    
    
    
    
  