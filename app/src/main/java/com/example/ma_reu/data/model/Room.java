package com.example.ma_reu.data.model;

import java.io.Serializable;

public class Room implements Serializable {

   private String name;

   public Room(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
