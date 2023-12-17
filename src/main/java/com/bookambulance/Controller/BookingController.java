package com.bookambulance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookambulance.Model.Booking;
import com.bookambulance.Services.BookingServices;

@Controller
public class BookingController {
@Autowired private BookingServices bookingServices;
@MutationMapping() public String deleteBookingById(@Argument Long id){
    Booking booking=bookingServices.findById(id);
    if(booking==null)
    return "Booking not found";
    else{
        bookingServices.deleteDataById(id);
        return booking.getUser().getName()+" Booking at "+booking.getBookingDate() +" has removed successfully";
    }
}
@QueryMapping() public Booking findBookingById(@Argument Long id){
    return bookingServices.findById(id);
}
}
