package com.bookambulance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookambulance.Model.Booking;
import com.bookambulance.Services.BookingServices;
@Controller
public class BookingController {
@Autowired private BookingServices bookingServices;
@PostMapping(value = "/user/booking/delete/{id}")  public String deleteBookingById(@PathVariable Long id){
        try {
            Booking booking=bookingServices.findById(id);
            booking.setCanceled(true);
            bookingServices.saveOrUpdateData(booking);
        } catch (Exception e) {
            System.out.println(e);
        }
    return "redirect:/user";
}
 @PostMapping(value = "/user/booking")
      public String addOrUpdateBooking(@ModelAttribute Booking bookingInput){
      Booking booking=bookingServices.saveOrUpdateData(bookingInput);
      return "redirect:/user/"+booking.getUser().getId();
    }

}
