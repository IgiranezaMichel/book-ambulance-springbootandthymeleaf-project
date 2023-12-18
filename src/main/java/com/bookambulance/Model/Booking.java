package com.bookambulance.Model;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Booking {
    @Id  @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Hospital hospital;
    private boolean isCanceled;
    private boolean isDone;
    private LocalDateTime bookingDate=LocalDateTime.now();
}
