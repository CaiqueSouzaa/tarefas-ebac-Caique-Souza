package br.com.csouza.annotations.use;

import br.com.csouza.annotations.TableName;
import br.com.csouza.annotations.TableColumn;
import br.com.csouza.entities.DatabaseEntity;
import br.com.csouza.exceptions.WithoutFieldColumnNameException;
import br.com.csouza.exceptions.WithoutTableNameException;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TableUse {
    public static String getTableName(Object o) throws WithoutTableNameException {
        final Class<?> oClass = o.getClass();

        return getName(oClass);
    }

    public static String getTableNameClass(Class<?> oClass) throws WithoutTableNameException {
        return getName(oClass);
    }

    private static String getName(Class<?> oClass) throws WithoutTableNameException {
        TableName hasAnnotation = oClass.getAnnotation(TableName.class);

        if (hasAnnotation == null) {
            throw new WithoutTableNameException("Nome de tabela não configurada na entidade " + oClass.getSimpleName());
        }

        return hasAnnotation.value();
    }

    /**
     * Método para filtrar pelos atributos da classe que possuem a anotação "TableColumn" na classe "DatabaseEntity".
     * @return Stream filtrada contendo somente os atributos que possuem a anotação "TableColumn".
     */
    public static Stream<Field> getDatabaseFieldsTableColumn() {
        return getFilteredFieldsTableColumn(DatabaseEntity.class);
    }

    /**
     * Método para filtrar pelos atributos da classe que possuem a anotação "TableColumn".
     * @param oClass Classe do objeto a ser filtrado.
     * @return Stream filtrada contendo somente os atributos que possuem a anotação "TableColumn".
     */
    public static Stream<Field> getFilteredFieldsTableColumn(Class<?> oClass) {
        final Stream<Field> fields = Arrays.stream(oClass.getDeclaredFields());
        return fields.filter((Field f) -> {
            final TableColumn hasAnnotation = f.getAnnotation(TableColumn.class);
            return hasAnnotation != null;
        });
    }

    public static Stream<Field> getFilteredFieldsDatabaseTableColumn(Class<?> oClass) {
        final Stream<Field> databaseFields = getDatabaseFieldsTableColumn();
        final Stream<Field> classFields = getFilteredFieldsTableColumn(oClass);

        final List<Field> fields = new ArrayList<>();

        fields.addAll(databaseFields.toList());
        fields.addAll(classFields.toList());

        return fields.stream();
    }

    /**
     * Método para obter o nome das colunas registradas no bando de dados.
     * @param f Atributo a ter o nome da coluna buscada.
     * @return Nome da coluna.
     * @throws WithoutFieldColumnNameException Exception lançada caso o atributo não possua a anotação "TableColumn".
     */
    public static String getTableColumnName(Field f) throws WithoutFieldColumnNameException {
        final TableColumn hasAnnotation = f.getAnnotation(TableColumn.class);

        if (hasAnnotation == null) {
            throw new WithoutFieldColumnNameException("Nome de coluna não configurada na propriedade [" + f.getName() + "]");
        }

        return hasAnnotation.dbName();
    }

    /**
     * Método para obter o nome do método "set" responsável por definir o valor no atributo.
     * @param f Atributo a ter o nome do método buscado.
     * @return Nome do método.
     * @throws WithoutFieldColumnNameException Exception lançada caso o atributo não possua a anotação "TableColumn".
     */
    public static String getTableColumnSetValue(Field f) throws WithoutFieldColumnNameException {
        final TableColumn hasAnnotation = f.getAnnotation(TableColumn.class);

        if (hasAnnotation == null) {
            throw new WithoutFieldColumnNameException("Nome de coluna não configurada na propriedade [" + f.getName() + "]");
        }

        return hasAnnotation.javaName();
    }

    public static String getTableColumnType(Field f) throws WithoutFieldColumnNameException {
        final TableColumn hasAnnotation = f.getAnnotation(TableColumn.class);

        if (hasAnnotation == null) {
            throw new WithoutFieldColumnNameException("Nome de coluna não configurada na propriedade [" + f.getName() + "]");
        }

        return f.getType().getSimpleName();
    }

    public static Class<?> getTableColumnTypeClass(Field f) throws WithoutFieldColumnNameException {
        final TableColumn hasAnnotation = f.getAnnotation(TableColumn.class);

        if (hasAnnotation == null) {
            throw new WithoutFieldColumnNameException("Nome de coluna não configurada na propriedade [" + f.getName() + "]");
        }

        return f.getType();
    }
}
