package com.bookambulance.Model;
import java.util.List;

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
@ManyToOne()
private Location location;
@OneToMany(mappedBy = "hospital")
private List<Ambulance>ambulanceList;
@OneToMany(mappedBy = "hospital")
private List<Booking>bookingList;
}
