package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Player")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	@Column(name = "name")
   private String name;
	@Column(name = "sports")
   private String sports;
	@Column(name = "rank")
   private int rank;
	@Column(name = "country")
   private String country;
	
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getSports() {
      return sports;
   }
   public void setSports(String sports) {
      this.sports = sports;
   }
   public int getRank() {
      return rank;
   }
   public void setRank(int rank) {
      this.rank = rank;
   }
   
   public String getCountry() {
      return country;
   }
   public void setCountry(String country) {
      this.country = country;
   }
   @Override
   public String toString() {
      return "Player [name=" + name + ", sports=" + sports + ", rank=" + rank
              + ", country=" + country + "]";
   }
   
   
   




}

