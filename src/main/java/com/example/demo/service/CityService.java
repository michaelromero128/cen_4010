package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.model.Comment;
import com.example.demo.model.CommentDTO;

@Service
public class CityService {
	
	private CityRepository cityRepository;
	
	@Autowired
	public CityService(CityRepository cityRepo) {
		this.cityRepository = cityRepo;
	}
	
	public City getCity(String cityName) {
		List<City> cities =cityRepository.findByCityName(cityName);
		City city = null;
		if(cities.size() == 0) {
			city = new City();
			city.setCityName(cityName);
			city.setComments(new ArrayList<Comment>());
			return cityRepository.save(city);
			
		}
		return cities.get(0);
	}
	
	public List<CommentDTO> addComment(String cityName, Comment comment){
		City city = getCity(cityName);
		comment.setCity(city);
		city.getComments().add(comment);
		cityRepository.save(city);
		List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
		Iterator<Comment> iterator = city.getComments().iterator();
		while(iterator.hasNext()) {
			Comment zeComment = iterator.next();
		}
		return getDTO(city.getComments());
	}
	
	public List<CommentDTO> getCommentsDTOFromCity(String cityName){
		City city = getCity(cityName);
		return getDTO(city.getComments());
	}
	
	private List<CommentDTO> getDTO(List<Comment> comments){
		List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
		Iterator<Comment> iterator = comments.iterator();
		while(iterator.hasNext()) {
			Comment zeComment = iterator.next();
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.cityId = zeComment.getCity().getCityId();
			commentDTO.cityName = zeComment.getCity().getCityName();
			commentDTO.commentId = zeComment.getCommentId();
			commentDTO.text = zeComment.getText();
			commentDTO.userId = zeComment.getUser().getId();
			commentDTO.userName = zeComment.getUser().getUserName();
			commentDTOList.add(commentDTO);
		}
		return commentDTOList;
		
	}

}
