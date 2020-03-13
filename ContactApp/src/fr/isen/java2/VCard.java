package fr.isen.java2;

import fr.isen.java2.model.Person;
import javafx.collections.ObservableList;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class VCard {
	
    /**
     * Method to create directory with contacts details inside in vCard format.
     * 
     * @param personData, file
     */
	public void export(ObservableList<Person> personData, File file) {
		
		// Create the directory
		file.mkdir();
		
		for(int i = 0 ; i < personData.size() ; i++) {
			
			// Create the name of each contact file
			Path directory = file.toPath();
			Path path = directory.resolve(personData.get(i).getFirstName() + " " + personData.get(i).getLastName());
			
			DataOutputStream dos;
			
			try {
				dos = new DataOutputStream(
				          new BufferedOutputStream(
				            new FileOutputStream(
				              path.toString())));
				
				    
				dos.writeBytes("BEGIN:VCARD\n");
				dos.writeBytes("VERSION:4.0\n");
				dos.writeBytes("N:" + personData.get(i).getLastName() + ";" + personData.get(i).getFirstName() + ";\n");
				dos.writeBytes("FN:" + personData.get(i).getFirstName() + " " + personData.get(i).getLastName() + "\n");
				dos.writeBytes("NICKNAME:" + personData.get(i).getNickName() + "\n");
				dos.writeBytes("TEL:" + personData.get(i).getPhoneNumber() + "\n");
				dos.writeBytes("ADR:" + personData.get(i).getAddress() + ";\n");
				dos.writeBytes("EMAIL:" + personData.get(i).getEmailAddress() + "\n");
				dos.writeBytes("BDAY:" + personData.get(i).getBirthDate());
				dos.close();
			      	
			      	
			  } catch (FileNotFoundException e) {
			    e.printStackTrace();
			  } catch (IOException e) {
			    e.printStackTrace();
			  }
			
		}
	
	}
	
	
    /**
     * Method to import from a directory all contacts details inside in vCard format.
     * 
     * @param personData, file
     */
	public void open(ObservableList<Person> personData, File file) {
		
		// Remove the existing contacts
		personData.clear();
		
		File[] files = file.listFiles();
		
		for(int i = 0 ; i < files.length ; i++) {
			
			DataInputStream dis;
			
			try {
				dis = new DataInputStream(
				          new BufferedInputStream(
				            new FileInputStream(
				              files[i])));
				
				Person person = new Person();

				dis.readLine();
				dis.readLine();
				String[] temp = dis.readLine().split(":")[1].split(";");
				person.setFirstName(temp[0]);
				person.setLastName(temp[1]);
				dis.readLine();
				person.setNickName(dis.readLine().split(":")[1]);
				person.setPhoneNumber(dis.readLine().split(":")[1]);
				person.setAddress(dis.readLine().split(":")[1]);
				person.setEmailAddress(dis.readLine().split(":")[1]);
				person.setBirthDate(dis.readLine().split(":")[1]);
				
				personData.add(person);
				
				dis.close();
			      	
			      	
			  } catch (FileNotFoundException e) {
			    e.printStackTrace();
			  } catch (IOException e) {
			    e.printStackTrace();
			  }
			
			}
			
		// Remove old contacts and adding the new ones
		DataBase dataBase = new DataBase();
		dataBase.removeAll();
		for(int i = 0 ; i < personData.size() ; i++) {
			dataBase.add(personData.get(i));
		}
	
	}
	
}