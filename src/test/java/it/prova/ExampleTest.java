package it.prova;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.arc.Arc;
import io.quarkus.arc.ClientProxy;
import io.quarkus.builder.Version;
import io.quarkus.maven.dependency.Dependency;
import io.quarkus.test.QuarkusUnitTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExampleTest {

	@RegisterExtension
	static final QuarkusUnitTest config = new QuarkusUnitTest()
			.withConfigurationResource("application.properties")
			.setForcedDependencies( List.of( Dependency.of( "io.quarkus", "quarkus-jdbc-h2", Version.getVersion())) );


	@Test
	public void entityManagerFactory() {
		DataSource dataSource = Arc.container().instance( DataSource.class ).get();
		DataSource unwrapped = ClientProxy.unwrap(dataSource);
		assertNotNull(unwrapped);
	}


}
