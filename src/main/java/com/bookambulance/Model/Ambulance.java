package com.bookambulance.Model;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Ambulance {
@Id  @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String plateNumber;
private String model;
@ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
private Hospital hospital;
@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER,mappedBy = "ambulance")
private List<User>listOfDriver;
}
