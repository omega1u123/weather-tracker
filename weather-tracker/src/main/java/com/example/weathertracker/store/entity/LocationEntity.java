package com.example.weathertracker.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "location")
public class LocationEntity {
 //   @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
   // private Long id;

    private String name;

}
