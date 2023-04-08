package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(loadProperties());
        String table = "table_demo";
        tableEditor.createTable(table);
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.addColumn(table, "column1", "text");
        tableEditor.addColumn(table, "column2", "text");
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.renameColumn(table, "column1", "column111");
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.dropColumn(table, "column2");
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.dropTable(table);
        System.out.println(tableEditor.getTableScheme(table));
        tableEditor.close();
    }

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        this.connection = initConnection();
    }

    public static Properties loadProperties() {
        Properties config = new Properties();
        ClassLoader loader = TableEditor.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("statement.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    private Connection initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        );
        connection.createStatement().execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE %s;",
                tableName
        );
        connection.createStatement().execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        );
        connection.createStatement().execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        connection.createStatement().execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        connection.createStatement().execute(sql);
    }

    public String getTableScheme(String tableName) {
        String rsl;
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
            rsl = buffer.toString();
        } catch (SQLException e) {
            rsl = e.getMessage();
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
