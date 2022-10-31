package com.parkingslot.main.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.parkingslot.main.Entity.Booking;
import com.parkingslot.main.Entity.Building;
import com.parkingslot.main.Entity.Floor;
import com.parkingslot.main.Entity.Slot;
import com.parkingslot.main.Exception.ResourceNotFoundException;
import com.parkingslot.main.JwtSecurity.User;
import com.parkingslot.main.repository.AvailabilityRepository;
import com.parkingslot.main.repository.BookingRepository;
import com.parkingslot.main.repository.BuildingRepository;
import com.parkingslot.main.repository.FloorRepository;
import com.parkingslot.main.repository.SlotRepository;
import com.parkingslot.main.repository.UserRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookRepo;
	@Autowired
	private BuildingRepository buildRepo;
	@Autowired
	private SlotRepository slotRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private FloorRepository floorRepo;
	@Autowired
	private AvailabilityRepository availRepo;

	public Long getId() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		User user = userRepo.findByUserName(name).orElse(null);
		return user.getId();
	}

	public String addBooking(Long bid, Long fid, Long sid, Date date) {
		
		Building build = buildRepo.getReferenceById(bid);
		if (build == null)
			return "NO SUCH BUILDING IS AVAILABLE";
		List<Floor> flist = build.getFloor();
		if (flist.isEmpty())
			return "NO SUCH FLOOR IS AVAILABLE";
		Floor f = null;
		Boolean bb = flist.stream().anyMatch(f1 -> f1.getId() == fid);
		if (bb) {
			f = floorRepo.getReferenceById(fid);
		}
		else
			return "NO FLOOR WITH THIS ID EXISTS";
		List<Slot> slist = f.getSlot();
		if (slist == null)
			return "NO SUCH SLOT IS AVAILABLE TO BOOK";
		for (Slot s : slist) {
			if(!((s.getId() == sid)))
					continue;
			if (s.getId() == sid) {
				System.out.println("#########################################"+s.getId()+"====="+sid);
				if ((date.equals(s.getAvail().getFromDate()) || date.after(s.getAvail().getFromDate())
						&& (date.equals(s.getAvail().getToDate()) || date.before(s.getAvail().getToDate())))) {
					if (bookRepo.getBookingDetails(date, sid,"Booked") == null) {
						User u = userRepo.getReferenceById(getId());
						Booking b = new Booking();
						b.setDate(date);
						b.setUser(u);
						b.setSlot(s);
						b.setStatus("Booked");
						bookRepo.save(b);
						return "YOUR SLOT HAVE BEEN SUCCESSFULLY BOOKED";
					} else
						return "SLOT IS ALREADY BOOKED";

				} else
					return "DATE YOU HAVE GIVEN IS NOT APPROPRIATE ACCORDING TO SLOT AVAILABLITY";
			} else
				return "INVALID SLOT ID";
		}

		return " LITE MASLA";
	}

	/*
	 * //List<Booking> booked = new ArrayList<>(); // Availability a=s.getAvail();
	 * //Boolean book = false; // if(a==null) // return
	 * "NO AVAILABILITY FOR THE SLOT"; // if
	 * ((date.after(a.getFromDate())||date.equals(a.getFromDate())) &&
	 * (date.before(a.getToDate()) || date.equals(a.getToDate())) ) // { // //
	 * Boolean flag=a.getBook().stream().anyMatch(a1->a1.getDate().equals(date)); //
	 * if(flag) // return "THIS SLOT IS ALREADY BOOKED"; // else // book=true; // //
	 * if(a.getBook().isEmpty()||book) // { // User u =
	 * userRepo.getReferenceById(getId()); // Booking b = new Booking(); //
	 * b.setDate(date); // b.setUser(u); // b.setSlot(s); // booked.add(b); //
	 * a.setBook(booked); // //availRepo.save(a); // // bookRepo.save(b); // return
	 * "YOUR SLOT HAVE BEEN SUCCESSFULLY BOOKED"; // } // } // else // return
	 * "DATE YOU HAVE GIVEN IS NOT APPROPRIATE ACCORDING TO SLOT AVAILABLITY"; // }
	 * // else // return "YOUR SLOT ID INVALID"; // } // return " LITE MASLA"; // //
	 * }
	 */
	public String bookingCancel(Long id) {
		Optional<Booking> obook = bookRepo.findById(id);
		if (obook == null || obook.isEmpty())
			return "NO BOOKING IS MADE WITH THIS " + id + " ID IN OUR DATABASE";
		Booking book = bookRepo.getReferenceById(id);
	
		Long uid = getId();
		if (uid == book.getUser().getId()) {
			Date currentDate = new Date();
			Date bookDate = book.getDate();
			if (currentDate.before(bookDate) ) {
				book.setStatus("Cancelled");
				//bookRepo.deleteById(id);
				bookRepo.save(book);
				return "DELETED SUCCESSFULLY";
			} else
				return "YOU ARE TOO LATE TO CANCEL BOOKING";
		} else
			throw new ResourceNotFoundException("YOU HAVE NOT BOOKED THIS SLOT SO YOU CANT DELETE THIS BOOKING");

	}

}
