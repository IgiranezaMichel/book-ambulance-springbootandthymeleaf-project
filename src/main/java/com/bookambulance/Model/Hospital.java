package com.bookambulance.Model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Hospital {
@Id  @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String name;
@JsonIgnore
@ManyToOne()
private Location location;
@OneToMany(mappedBy = "hospital")
private List<Ambulance>ambulanceList;
@OneToMany(mappedBy = "hospital")
private List<Booking>bookingList;
}
