package com.asynclife.jooq;

import static org.jooq.util.maven.example.tables.Author.AUTHOR;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.maven.example.tables.Author;

public class Insert {
	public static void main(String[] args) {

        String userName = "root";
        String password = "root123";
        String url = "jdbc:mysql://localhost:3306/library";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
           
            create.insertInto(AUTHOR, 
            		AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
            		.values(101, "zhang", "san")
            		.values(102, "Lis", "si")
            		.execute();
        } 

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    
	}
}
