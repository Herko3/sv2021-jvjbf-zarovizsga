package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@Service
public class TeamService {

    private TeamRepository repository;
    private PlayerRepository playerRepository;

    private ModelMapper mapper;

    public List<TeamDTO> listTeams() {
        return repository.findAll().stream()
                .map(t -> mapper.map(t, TeamDTO.class))
                .toList();
    }

    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        repository.save(team);
        return mapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO addNewPlayer(long id, CreatePlayerCommand command) {
        Team team = getTeamById(id);
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        team.addPlayer(player);
        return mapper.map(team, TeamDTO.class);
    }

    private Team getTeamById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(URI.create("teams/not-found"), "No team with id: " + id));
    }

    @Transactional
    public TeamDTO addExistingPlayer(long id, UpdateWithExistingPlayerCommand command) {
        Team team = getTeamById(id);
        Player player = getPlayerById(command.getPlayerId());
        if (canBeSigned(team, player)) {
            team.addPlayer(player);
        }
        return mapper.map(team, TeamDTO.class);
    }

    private Player getPlayerById(long id) {
        return playerRepository.findById(id).orElseThrow(() -> new NotFoundException(URI.create("players/not-found"),"No player with id: " + id));
    }

    private boolean canBeSigned(Team team, Player player) {
        long playersOnPost = team.getPlayers().stream()
                .filter(p -> p.getPosition() == player.getPosition())
                .count();

        return player.getTeam() == null && playersOnPost < 2;
    }
}
