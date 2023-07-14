package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;



@Service
public class GameListService {

	
	@Autowired
	private GameListRepository gameRepository;

	@Autowired //O proprio Spring irá resolver a dependencia
	private GameListRepository gameListRepository;
	
	
	//Uma função que retorna uma lista de GameMinDTO
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll(); //Gerar uma consulta no bd para trazer o resultado, convertendo em uma lista
		return result.stream().map(x -> new GameListDTO(x)).toList(); //tranforma uma lista de Games e uma lista de GameMinDTO
	}
	
	@Transactional
	//Alterar posições
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex,obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
