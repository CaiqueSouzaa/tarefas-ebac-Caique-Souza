import br.com.csouza.entities.Product;
import br.com.csouza.exceptions.EntityWithoutTableNameException;
import br.com.csouza.utils.GetTableName;

public class Main {
    public static void main(String[] args) throws EntityWithoutTableNameException {
        final Product p1 = new Product();
        System.out.println(GetTableName.getTableName(p1));
    }
}