package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Game;

import com.techelevator.model.JdbcGameDao;

@RestController
@CrossOrigin
@RequestMapping("/api/game")
public class GameController {
	
	@Autowired
	private JdbcGameDao jdbcGameDao;
	
	@GetMapping
	public List<Game> list(){
		return jdbcGameDao.listAllGames();
	
	}

}
