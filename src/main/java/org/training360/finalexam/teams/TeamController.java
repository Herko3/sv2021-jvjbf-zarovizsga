package org.training360.finalexam.teams;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService service;

    @GetMapping
    public List<TeamDTO> listTeams(){
        return service.listTeams();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command){
        return service.createTeam(command);
    }

    @PostMapping("/{id}/players")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO addNewPlayer(@PathVariable ("id") long id, @Valid @RequestBody CreatePlayerCommand command){
        return service.addNewPlayer(id,command);
    }

    @PutMapping("/{id}/players")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeamDTO addExistingPlayer(@PathVariable ("id") long id, @Valid @RequestBody UpdateWithExistingPlayerCommand command){
        return service.addExistingPlayer(id,command);
    }
}
