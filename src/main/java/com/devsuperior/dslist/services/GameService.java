package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameService {


	@Autowired //O proprio Spring irá resolver a dependencia
	private GameRepository gameRepository;
	
	//Uma função que retorna uma lista de GameMinDTO
	public List<GameMinDTO> findAll(){
		List<Game> result = gameRepository.findAll(); //Gerar uma consulta no bd para trazer o resultado, convertendo em uma lista
		return result.stream().map(x -> new GameMinDTO(x)).toList(); //tranforma uma lista de Games e uma lista de GameMinDTO
	}
}
