package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;



@Service
public class GameService {


	@Autowired //O proprio Spring irá resolver a dependencia
	private GameRepository gameRepository;
	
	
	//Metodo para buscar por ID
	@Transactional(readOnly = true)//Garantir que a opreação do BD vai acontecer
	public GameDTO finById(Long id) {
		Game result = gameRepository.findById(id).get();
		return new GameDTO(result);	
	}
	
	//Uma função que retorna uma lista de GameMinDTO
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game> result = gameRepository.findAll(); //Gerar uma consulta no bd para trazer o resultado, convertendo em uma lista
		return result.stream().map(x -> new GameMinDTO(x)).toList(); //tranforma uma lista de Games e uma lista de GameMinDTO
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId){
		List<GameMinProjection> result = gameRepository.searchByList(listId); //Gerar uma consulta no bd para trazer o resultado, convertendo em uma lista
		return result.stream().map(x -> new GameMinDTO(x)).toList(); //tranforma uma lista de Games e uma lista de GameMinDTO
	}
}
