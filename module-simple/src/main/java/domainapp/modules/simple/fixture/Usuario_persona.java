package domainapp.modules.simple.fixture;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.springframework.core.io.ClassPathResource;

import org.apache.causeway.applib.services.clock.ClockService;
import org.apache.causeway.applib.services.registry.ServiceRegistry;
import org.apache.causeway.applib.value.Blob;
import org.apache.causeway.testing.fakedata.applib.services.FakeDataService;
import org.apache.causeway.testing.fixtures.applib.personas.BuilderScriptWithResult;
import org.apache.causeway.testing.fixtures.applib.personas.Persona;
import org.apache.causeway.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.val;
import lombok.experimental.Accessors;

import domainapp.modules.simple.dom.so.Usuario;
import domainapp.modules.simple.dom.so.Usuarios;

@RequiredArgsConstructor
public enum Usuario_persona
implements Persona<Usuario, Usuario_persona.Builder> {

    FOO("Luciano","Sicolo","42402145","11111111", "luciano.sicolo@hotmail.com"),
    BAR("juan","Gomez","42402145","123123", "juan.Gomez@hotmail.com"),
    BAZ("bruno","perez","42232345","11111111", "luciano.sicolo@hotmail.com"),
    FRODO("Frodo","gimenez","42412235","11111111", "Frodo.gimenez@hotmail.com");
  

    private final String name;
    private final String nombre;
    private final String documento;
    
        private final String telefono;
    private final String email;
//    private final String contentFileName;

    @Override
    public Builder builder() {
        return new Builder().setPersona(this);
    }

    @Override
    public Usuario findUsing(final ServiceRegistry serviceRegistry) {
        return serviceRegistry.lookupService(Usuarios.class).map(x -> x.findByNameExact(name)).orElseThrow();
    }

    @Accessors(chain = true)
    public static class Builder extends BuilderScriptWithResult<Usuario> {

        @Getter @Setter private Usuario_persona persona;

        @Override
        protected Usuario buildResult(final ExecutionContext ec) {

            val simpleObject = wrap(usuarios).create(persona.name,persona.nombre,persona.documento,persona.telefono,persona.email);

//            if (persona.contentFileName != null) {
//                val bytes = toBytes(persona.contentFileName);
//                val attachment = new Blob(persona.contentFileName, "application/pdf", bytes);
//                simpleObject.updateAttachment(attachment);
//            }

            simpleObject.setLastCheckedIn(clockService.getClock().nowAsLocalDate().plusDays(fakeDataService.ints().between(-10, +10)));

            return simpleObject;
        }

        @SneakyThrows
        private byte[] toBytes(String fileName){
            InputStream inputStream = new ClassPathResource(fileName, getClass()).getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            return buffer.toByteArray();
        }

        // -- DEPENDENCIES

        @Inject Usuarios usuarios;
        @Inject ClockService clockService;
        @Inject FakeDataService fakeDataService;
    }

    public static class PersistAll
            extends PersonaEnumPersistAll<Usuario, Usuario_persona, Builder> {
        public PersistAll() {
            super(Usuario_persona.class);
        }
    }


}
