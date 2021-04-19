package com.example.demo;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;

@RunWith(MockitoJUnitRunner.class)
class Cen4010ApplicationTests {

	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createsCity() {
		CityRepository cityRepository = Mockito.mock(CityRepository.class);
		CityService cityService = new CityService(cityRepository);
		Mockito.when(cityRepository.save( Mockito.any(City.class) ) ).thenAnswer(new Answer<City>() {
			@Override
			public City answer(InvocationOnMock invocation) {
				City city = invocation.getArgument(0, City.class);
				if(city.getCityName().equals("thing")) {
					return city;
				}
				return null;
			}
		});
		City city = cityService.getCity("thing");
		assertNotNull("city was null", city);
	}
	void retrieveCity() {
		CityRepository cityRepository = Mockito.mock(CityRepository.class);
		CityService cityService = new CityService(cityRepository);
		final City finalCity = new City();
		finalCity.setCityName("wozers");
		Mockito.when(cityRepository.findByCityName(Mockito.anyString() ) ).thenAnswer(new Answer<List<City>>() {
			@Override
			public List<City> answer(InvocationOnMock invocation) {
				String cityName = invocation.getArgument(0, String.class);
				if(cityName.equals("wozers")){
					ArrayList<City> list = new ArrayList<>();
					list.add(finalCity);
				}
				return new ArrayList();
			}
		});
		City city = cityService.getCity("wozers");
		assertNotNull("city was null", city);
	}
	

}
