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
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id).get();
		return new GameDTO(result);
	}

	public List<GameMinDTO> findAll(){
			//var result = gameRepository.findAll();
			List<Game> result = gameRepository.findAll();
			/*
			 * List<GameMinDTO> returnedList = new ArrayList<GameMinDTO>(); for (Game game :
			 * result) { returnedList.add(new GameMinDTO(game)); }
			 */
			List<GameMinDTO> returnedList = result.stream().map(g -> new GameMinDTO(g)).toList();
			return returnedList;
		}

	public List<GameMinDTO> findByList(Long listId){
			List<GameMinProjection> result = gameRepository.searchByList(listId);
			List<GameMinDTO> returnedList = result.stream().map(g -> new GameMinDTO(g)).toList();
			return returnedList;
		}
	}