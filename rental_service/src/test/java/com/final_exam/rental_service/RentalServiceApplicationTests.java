package com.final_exam.rental_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RentalServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	private RentalRepo rentalRepo;

	@InjectMocks
	private RentalController rentalController;

	private JacksonTester<Rental> jsonRental;

	private JacksonTester<List<Rental>> jsonRentals;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper().registerModule(new JavaTimeModule()));
		mvc = MockMvcBuilders.standaloneSetup(rentalController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getAll() throws Exception {
		Rental rental1 = new Rental(1L,1L, "civic", "malir", "7493", "7394", LocalDateTime.now(), LocalDateTime.now(), true,
				430, 890);
		Rental rental2 = new Rental(1L,1L, "civic", "malir", "7493", "7394", LocalDateTime.now(), LocalDateTime.now(), true,
				430, 890);

		List<Rental> expectedRental = new ArrayList<>();

		expectedRental.add(rental1);
		expectedRental.add(rental2);

		when(rentalRepo.findAll()).thenReturn(expectedRental);

		// mvc.perform(MockMvcRequestBuilders.get("/cars/getAll"))
		// .andExpect(status().isOk())
		// .andExpect(content().json(jsonCars.write(expectedCars).getJson()));
		mvc.perform(MockMvcRequestBuilders
				.get("/rental/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonRentals.write(expectedRental).getJson()));

	}

	@Test
	public void getCarbyId() throws Exception {
		Rental rental1 = new Rental(1L, 1L,"civic", "malir", "7493", "7394", LocalDateTime.now(), LocalDateTime.now(), true,
				430, 890);
		// Rental rental2= new Rental(1L,"civic","malir", 7493,7394,LocalDateTime.now(),
		// LocalDateTime.now(),true, 430,890);

		// List<Rental> expectedRental = new ArrayList<>();

		// expectedRental.add(rental1);
		// expectedRental.add(rental2);

		when(rentalRepo.findById(1L)).thenReturn(Optional.of(rental1));

		// mvc.perform(MockMvcRequestBuilders.get("/cars/getAll"))
		// .andExpect(status().isOk())
		// .andExpect(content().json(jsonCars.write(expectedCars).getJson()));
		mvc.perform(MockMvcRequestBuilders
				.get("/rental/get/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonRental.write(rental1).getJson()));

	}

	@Test
	public void postRental() throws Exception{
		Rental rental1= new Rental(1L,1L,"civic","malir", "7493","7394",LocalDateTime.now(), LocalDateTime.now(),true, 430,890);
		// Rental rental2= new Rental(1L,"civic","malir", 7493,7394,LocalDateTime.now(), LocalDateTime.now(),true, 430,890);

		// List<Rental> expectedRental = new ArrayList<>();

		// expectedRental.add(rental1);
		// expectedRental.add(rental2);


		when(rentalRepo.save(rental1)).thenReturn(rental1);

		// mvc.perform(MockMvcRequestBuilders.get("/cars/getAll"))
		// .andExpect(status().isOk())
		// .andExpect(content().json(jsonCars.write(expectedCars).getJson()));
		mvc.perform(MockMvcRequestBuilders
				.post("/rental/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRental.write(rental1).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
				

	}

}

