package com.anapiashko.site.jdbc;

import java.sql.*;

public class DeleteRowDB
{
    String position = new String();
    String count = new String();
    String id;

    public DeleteRowDB(Integer id) throws SQLException, ClassNotFoundException {
        this.position = position;
        this.count = count.toString();
        this.id = id.toString();
        testDatabase();
    }

    private void testDatabase() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
           String url = "jdbc:postgresql://localhost:5432/roles";
            String login = "anastasiya";
            String password = "123";
            Connection con = DriverManager.getConnection(url, login, password);
            select(con);
    }

    private void select(Connection con) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM position WHERE id = '"+id+"' ")) {

            boolean test = stmt.execute();

        }
    }
}