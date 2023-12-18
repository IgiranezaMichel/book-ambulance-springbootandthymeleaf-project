package com.bookambulance.Model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Location {
@GeneratedValue(strategy = GenerationType.AUTO)
@Id
private long id;
private String name;
private String type;
@JsonIgnore
@ManyToOne
private Location location;
@JsonIgnore
@OneToMany(mappedBy = "location")
private List<Hospital>hospitalLocation;
@JsonIgnore
@OneToMany(mappedBy = "location")
private List<Location>locationList;
public Location(String name2, String type2,Location location) {
    this.name=name2;
    this.type=type2;
    this.location=location;
}
}
