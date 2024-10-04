import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClientDAOTest.class, ClientServiceTest.class, ContractDAOTest.class, ContractServicesTest.class })
public class AllTests {
}
