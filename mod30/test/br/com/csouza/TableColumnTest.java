package br.com.csouza;

import br.com.csouza.annotations.TableColumn;
import br.com.csouza.annotations.TableName;
import br.com.csouza.annotations.use.TableUse;
import br.com.csouza.entities.Client;
import br.com.csouza.exceptions.WithoutFieldColumnNameException;
import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

public class TableColumnTest {
    @Test
    public void test1() throws WithoutFieldColumnNameException {
        final Client c1 = new Client();

        Class<Client> clientClass = Client.class;

//        System.out.println(TableUse.getDatabaseFieldsTableColumn().toList());

        Stream<Field> filtered = TableUse.getFilteredFieldsDatabaseTableColumn(Client.class);

        for (Field f : filtered.toList()) {
//            System.out.println(TableUse.getTableColumnName(f));
//            System.out.println(TableUse.getTableColumnSetValue(f));
//            System.out.println(TableUse.getTableColumnType(f));
//            System.out.println(TableUse.getTableColumnTypeClass(f));
        }
    }
}
