package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PlayerService {

    private PlayerRepository repository;

    private ModelMapper mapper;

    public List<PlayerDTO> listPlayers() {
        return repository.findAll().stream()
                .map(p->mapper.map(p,PlayerDTO.class))
                .toList();
    }

    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName(),command.getBirthDate(),command.getPosition());
        repository.save(player);
        return mapper.map(player,PlayerDTO.class);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
