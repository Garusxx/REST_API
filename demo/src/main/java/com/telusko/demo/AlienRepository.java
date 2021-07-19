package com.telusko.demo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class AlienRepository {
	
	List<Alien> aliens;
	   
	Connection sqlConn = null;
	PreparedStatement pst = null;
	
	public AlienRepository(){
		
		String username="root";
		String password="Swinni12";
		String dataConn = "jdbc:mysql://127.0.0.1:3306/Alien?" + "autoReconnect=true&useSSL=false";
		
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		sqlConn = (Connection) DriverManager.getConnection(dataConn,username,password);
		}catch(Exception e){
			System.out.print("Error");
		}
	}
	
	public List<Alien> getAliens(){
		
		List<Alien> aliens = new ArrayList<>();
		
		
		String sql = "SELECT * FROM alien_table";
		try{
			Statement st = sqlConn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoint(rs.getInt(3));
				
				aliens.add(a);
			}
		}catch(Exception e){
			System.out.print(e);
		}
		
		return aliens;
	}
	
	public Alien getAlien(int id){
		
		String sql = "select * from alien wher id=" + id;
		Alien a = new Alien();
		try{
			Statement st = sqlConn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoint(rs.getInt(3));
			
			}
		}catch(Exception e){
			System.out.print(e);
		}
		
		return a;
	}

	public void create(Alien a1) {
		String sql = "INSERT INTO alien.alien_table (id,name,points) VALUES(?,?,?);";
		
		try{
			PreparedStatement st = sqlConn.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoint());
			st.executeUpdate();	
		
		}catch(Exception e){
			System.out.print(e);
		}
	}
	
	public void upData(Alien a1) {
		String sql = "update alien.alien_table set name=?, points=? where id=?;";
		
		try{
			PreparedStatement st = sqlConn.prepareStatement(sql);
			
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoint());
			st.setInt(3, a1.getId());
			st.executeUpdate();	
		
		}catch(Exception e){
			System.out.print(e);
		}
	}
	
	public void delete(int id) {
		
		String sql = "delete from alien.alien_table where id=?;";
		
		try{
			PreparedStatement st = sqlConn.prepareStatement(sql);
			
			st.setInt(1, id);
			st.executeUpdate();	
		
		}catch(Exception e){
			System.out.print(e);
		}
	}
}
