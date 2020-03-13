package fr.isen.java2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import fr.isen.java2.model.Person;
import javafx.collections.ObservableList;



public class DataBase {

	
	private String url = "jdbc:postgresql://localhost:5432/contactApp";
    private String user = "postgres";
    private String passwd = "postgres";

    /**
     * Show the full database. For debug purpose.
     */
	public void showAll() {

	    try {
	        Class.forName("org.postgresql.Driver");

	        Connection conn = DriverManager.getConnection(url, user, passwd);

	        //Création d'un objet Statement
	        Statement state = conn.createStatement();
	        //L'objet ResultSet contient le résultat de la requête SQL
	        ResultSet result = state.executeQuery("SELECT * FROM person");
	        //On récupère les MetaData
	        ResultSetMetaData resultMeta = result.getMetaData();

	        System.out.println("\n**********************************");
	        //On affiche le nom des colonnes
	        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	          System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

	        System.out.println("\n**********************************");

	        while(result.next()){

	        	for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
	        		System.out.print("\t" + result.getObject(i).toString() + "\t |");
	        	}

	        	System.out.println("\n---------------------------------");

	        }

	        result.close();
	        state.close();

	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}


    /**
     * Sync the variable application personData with the database.
     * 
     * @param personData
     */
	public void setPersonData(ObservableList<Person> personData) {

	    try {
	        Class.forName("org.postgresql.Driver");

	        Connection conn = DriverManager.getConnection(url, user, passwd);

	        Statement state = conn.createStatement();
	        ResultSet result = state.executeQuery("SELECT * FROM person");
	        ResultSetMetaData resultMeta = result.getMetaData();

	        personData.clear();

	        while(result.next()){

	        	String[] tab = new String[8];

	        	for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
	        		tab[i-1] = result.getObject(i).toString();
	        	}

	        	personData.add(new Person(result.getInt(1), tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7]));

	        }

	        result.close();
	        state.close();

	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}


    /**
     * Add a person to the database.
     * 
     * @param person
     */
	public void add(Person person) {

	    try {
	        Class.forName("org.postgresql.Driver");

	        Connection conn = DriverManager.getConnection(url, user, passwd);

	        Statement state = conn.createStatement();
	        state.executeUpdate("INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) " +
	      		  "VALUES('" + person.getLastName() + "', '" + person.getFirstName() + "', '" + person.getNickName() + "', '" + person.getPhoneNumber() + "', '" + person.getAddress() + "', '" + person.getEmailAddress() + "', '" + person.getBirthDate() + "')");

	        ResultSet result = state.executeQuery("SELECT LASTVAL()");

	        result.next();
    		person.setIdPerson(result.getInt(1));

	        state.close();

	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}


    /**
     * Remove a person from the database.
     * 
     * @param person
     */
	public void remove(Person person) {

	    try {
	        Class.forName("org.postgresql.Driver");

	        Connection conn = DriverManager.getConnection(url, user, passwd);

	        Statement state = conn.createStatement();
	        state.executeUpdate("DELETE from person where idperson = " + person.getIdPerson());

	        state.close();

	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}
	
	
    /**
     * Remove all person from the database.
     */
	public void removeAll() {

	    try {
	        Class.forName("org.postgresql.Driver");

	        Connection conn = DriverManager.getConnection(url, user, passwd);

	        Statement state = conn.createStatement();
	        state.executeUpdate("DELETE from person");

	        state.close();

	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}


    /**
     * Edit a person to the database.
     * 
     * @param person
     */
	public void edit(Person person) {

	    try {
	        Class.forName("org.postgresql.Driver");

	        Connection conn = DriverManager.getConnection(url, user, passwd);

	        Statement state = conn.createStatement();
	        state.executeUpdate("UPDATE person SET lastname = '" + person.getLastName() + "', firstname = '" + person.getFirstName() + "', nickname = '" + person.getNickName() + "', phone_number = '" + person.getPhoneNumber() + "', address = '" + person.getAddress() + "', email_address = '" + person.getEmailAddress() + "', birth_date = '" + person.getBirthDate() + "' WHERE idPerson = " + person.getIdPerson());

	        state.close();

	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}


}
