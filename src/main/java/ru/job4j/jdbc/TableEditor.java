package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        String driverClass = properties.getProperty("hibernate.connection.driver_class");
        Class.forName(driverClass);
        this.connection = DriverManager.getConnection(
                properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.username"),
                properties.getProperty("hibernate.connection.password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        move(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table %s;",
                tableName
        );
        move(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table %s add %s %s;",
                tableName,
                columnName,
                type
        );
        move(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "alter table %s drop column %s;",
                tableName,
                columnName
        );
        move(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        move(sql);
    }

    public void move(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("Car");
            System.out.println(getTableScheme(tableEditor.connection, "Car"));

            tableEditor.addColumn("Car", "id", "serial primary key");
            tableEditor.addColumn("Car", "name", "varchar(66)");
            System.out.println(getTableScheme(tableEditor.connection, "Car"));

            tableEditor.dropColumn("Car", "id");
            System.out.println(getTableScheme(tableEditor.connection, "Car"));

            tableEditor.renameColumn("Car", "name", "rename");
            System.out.println(getTableScheme(tableEditor.connection, "Car"));

            tableEditor.dropTable("Car");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}