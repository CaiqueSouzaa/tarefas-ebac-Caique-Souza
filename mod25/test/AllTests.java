import br.com.csouza.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GetTableNameTest.class, ProductDAOTest.class, SingletonDatabaseMockTest.class, ClientDAOTest.class, ProductServiceTest.class, ClientServiceTest.class })
public class AllTests {}
