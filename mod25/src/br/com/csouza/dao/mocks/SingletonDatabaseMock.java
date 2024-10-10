package br.com.csouza.dao.mocks;

import br.com.csouza.entities.DatabaseObject;
import br.com.csouza.exceptions.WithoutDatabaseMethodsException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

/**
 * Este objeto Singleton é a simulação de um banco de dados em memória.
 *
 * @author Caique Souza
 * @version 1.0
 */
public class SingletonDatabaseMock {
    private static Map<String, List<Object>> tables;

    private SingletonDatabaseMock() {}

    /**
     * Método para inicializar o banco de daddos.
     * Atenção! Por segurança, recomendamos que o banco de dados seja sempre inicializado.
     */
    public static void init() {
        if (tables == null) {
            tables = new HashMap<>();
        }
    }

    /**
     * Método para obter todas as tabelas do banco de dados.
     * @return Collect - Coleção contendo todas as tabelas.
     */
    public static Map<String, List<Object>> getTables() {
        return tables;
    }

    /**
     * Método para obter uma única tabela do banco de dados.
     * @param table Nome da tabela a ser buscada.
     * @return Tabela solicitada.
     * @throws Exception Exeção lançada caso o nome de tabela não exista no banco de dados.
     */
    public static Collection<Object> getTable(String table) throws Exception {
        if (!hasTable(table)) {
            notHasTableException(table);
        }

        return tables.get(table);
    }

    /**
     * Método para obter um único registro de uma tabela.
     * @param table Nome da tabela.
     * @param id ID do registro a ser buscado.
     * @return Optional - Item buscado.
     * @throws Exception Exeção lançada caso o nome de tabela não exista no banco de dados.
     */
    public static Optional<Object> getItem(String table, long id) throws Exception {
        if (!hasTable(table)) {
            notHasTableException(table);
        }

        Optional<Object> item = tables.get(table).stream()
                .filter((Object o) -> {
                    final Class<?> itemClass = o.getClass();
                    Optional<Method> getId = Arrays.stream(itemClass.getMethods())
                            .filter((Method m) -> m.getName().equals("getId"))
                            .findFirst();

                    return getId.isPresent();
                })
                .filter((Object o) -> {
                    boolean status = false;
                    final Class<?> itemClass = o.getClass();
                    Optional<Method> getId = Arrays.stream(itemClass.getMethods())
                            .filter((Method m) -> m.getName().equals("getId"))
                            .findFirst();

                    if (getId.isPresent()) {
                        try {
                            status = (long) getId.get().invoke(o) == id;
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    return status;
                })
                .findFirst();

        return item;
    }

    /**
     * Método para criar uma nova tabela.
     * @param table Nome da tabela a ser criada.
     * @return Status de criação. true: Tabela criada, false: Tabela não criada.
     */
    public static boolean newTable(String table) {
        tables.put(table, new ArrayList<>());

        return tables.containsKey(table);
    }

    /**
     * Método para realizar um novo registro em uma tabela.
     * @param table Nome da tabela que receberá o registro.
     * @param entity Entidade/objeto a ser registrado.
     * @throws Exception Exeção lançada caso o nome de tabela não exista no banco de dados.
     * @throws WithoutDatabaseMethodsException Exeção lançada caso a entidade não possua os métodos necessários do banco de dados.
     */
    public static boolean add(String table, Object entity) throws Exception, WithoutDatabaseMethodsException {
        if (!hasTable(table)) {
            notHasTableException(table);
        }

        Class<?> itemClass = entity.getClass();
        Stream<Method> itemMethods = Arrays.stream(itemClass.getMethods());

        Optional<Method> setIdMethod = itemMethods.filter((Method m) -> m.getName().equals("setId")).findFirst();

        if (setIdMethod.isEmpty()) {
            notHasDatabaseMethods();
        }

        setIdMethod.get().invoke(entity, Long.parseLong(String.valueOf(getTable(table).size() + 1)));

        tables.get(table).add(entity);
        return tables.get(table).contains(entity);
    }

    /**
     * Método para verificar se uma tabela existe.
     * @param table Nome da tabela a ser verificada.
     * @return Status de existencia. true: Existe, false: Não existe.
     */
    public static boolean hasTable(String table) {
        return tables.containsKey(table);
    }

    /**
     * Método privado para lançar erro caso a tabela não exista no banco de dados.
     * @param table Nome da tabela.
     * @throws Exception Exeção lançada caso o nome de tabela não exista no banco de dados.
     */
    private static void notHasTableException(String table) throws Exception {
        throw new Exception("A tabela " + table + " não existe");
    }

    private static void notHasDatabaseMethods() throws WithoutDatabaseMethodsException {
        throw new WithoutDatabaseMethodsException("A entidade não possui os métodos necessários para manipulação no banco de dados");
    }

    /**
     * Método para limpar uma tabela.
     * @param table Nome da tabela.
     * @return Status de limpeza. true: Tabela limpa, false: Tabela não limpa.
     * @throws Exception Exeção lançada caso o nome de tabela não exista no banco de dados.
     */
    public static boolean clear(String table) throws Exception {
        if (!hasTable(table)) {
            notHasTableException(table);
        }

        tables.get(table).clear();
        return tables.get(table).isEmpty();
    }

    /**
     * Método para atualizar um registro.
     * @param table Nome da tabela.
     * @param id ID do registro a ser atualizado.
     * @param newData Novas informações para atualizar o registro.
     * @return Status de atualização.
     * @throws Exception Exeção lançada caso o nome de tabela não exista no banco de dados.
     */
    public static boolean update(String table, long id, Object newData) throws Exception {
        if (!hasTable(table)) {
            notHasTableException(table);
        }

        Optional<Object> item = getItem(table, id);
        if (item.isEmpty()) {
            throw new Exception("Registro de item não existente");
        }

        for (int i = 0; i < tables.get(table).size(); i++) {
            if (getId(tables.get(table).get(i)) == id) {
                setId(newData, getId(tables.get(table).get(i)));
                tables.get(table).remove(i);
                tables.get(table).add(newData);
            }
        }

        return true;
    }

    /**
     * Método para obter o id da entidade.
     * @param entity Entidade a ser buscada pelo id.
     * @return Method - Método para buscar o ID.
     * @throws WithoutDatabaseMethodsException Exeção lançada caso a entidade não possua os métodos necessários do banco de dados.
     */
    private static long getId(Object entity) throws WithoutDatabaseMethodsException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> itemClass = entity.getClass();

        Optional<Method> getIdMethod = Arrays.stream(itemClass.getMethods())
                .filter((Method m) -> m.getName().equals("getId"))
                .findFirst();

        if (getIdMethod.isEmpty()) {
            notHasDatabaseMethods();
        }

        return (long) getIdMethod.get().invoke(entity);
    }

    /**
     * Método para definir o id de um item.
     * @param entity Entidade a ter o id definido.
     * @param id ID da entidade.
     * @throws WithoutDatabaseMethodsException Exeção lançada caso a entidade não possua os métodos necessários do banco de dados.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void setId(Object entity, long id) throws WithoutDatabaseMethodsException, InvocationTargetException, IllegalAccessException {
        Class<?> itemClass = entity.getClass();

        Optional<Method> setIdMethod = Arrays.stream(itemClass.getMethods())
                .filter((Method m) -> m.getName().equals("setId"))
                .findFirst();

        if (setIdMethod.isEmpty()) {
            notHasDatabaseMethods();
        }

        setIdMethod.get().invoke(entity, id);
    }

    public static boolean delete(String table, long id) throws Exception {
        if (!hasTable(table)) {
            notHasTableException(table);
        }

        if (getItem(table, id).isEmpty()) {
            throw new Exception("Registro de item não existente");
        }

        for (int i = 0; i < tables.get(table).size(); i++) {
            if (getId(tables.get(table).get(i)) == id) {
                tables.get(table).remove(i);
            }
        }

        return true;
    }
}
