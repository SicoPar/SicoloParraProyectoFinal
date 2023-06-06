package domainapp.modules.simple.dom.so;


import java.util.List;

import javax.inject.Inject;

import org.apache.causeway.applib.annotation.Collection;
import org.apache.causeway.applib.annotation.CollectionLayout;

import lombok.RequiredArgsConstructor;

@Collection                                             
@CollectionLayout(defaultView = "Vehiculos")
@RequiredArgsConstructor                                
public class Usuario_vehiculo {                            

    private final Usuario usuario;                    

    public List<Vehiculo> coll() {
        return vehiculorepository.findByName(usuario);  
    }

    @Inject VehiculoRepository vehiculorepository;                
}