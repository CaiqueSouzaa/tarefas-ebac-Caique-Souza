package br.com.csouza;

import br.com.csouza.annotations.TableName;
import br.com.csouza.exceptions.EntityWithoutTableNameException;
import br.com.csouza.utils.GetTableName;
import org.junit.Test;
import org.junit.Assert;

public class GetTableNameTest {
    @Test
    public void getNameTest() throws EntityWithoutTableNameException {
        @TableName("ClassTest")
        class ClassTest {}

        ClassTest classTest =  new ClassTest();

        Assert.assertEquals("ClassTest", GetTableName.getTableName(classTest));
    }

    @Test(expected = EntityWithoutTableNameException.class)
    public void getNameExceptionTest() throws EntityWithoutTableNameException {

        class ClassTest {}

        ClassTest classTest =  new ClassTest();

        GetTableName.getTableName(classTest);
    }
}
