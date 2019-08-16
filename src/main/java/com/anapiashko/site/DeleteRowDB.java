package com.anapiashko.site;

import java.sql.*;

public class DeleteRowDB
{
    String position = new String();
    String count = new String();

    public DeleteRowDB(String position, Integer count) throws SQLException, ClassNotFoundException {
        this.position = position;
        this.count = count.toString();
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
                "DELETE FROM position WHERE position = '"+position+"' AND count = '"+count+"'")) {

            boolean test = stmt.execute();

        }
    }
}