package demo.web;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import demo.web.dto.GameDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static demo.web.dto.GameDTO.Type.FPS;
import static demo.web.dto.GameDTO.Type.RPG;

/**
 * Created by Benoit on 19/03/2015.
 */
@RestController
@RequestMapping("/game")
public class GameController {

    private List<GameDTO> games = Lists.newArrayList();
    @PostConstruct
    public void init() {
        GameDTO gameDTO = null;
        for (int i= 1; i<21; i++){
            gameDTO = new GameDTO();
            gameDTO.setId(i);
            gameDTO.setName("Game"+i);
            gameDTO.setType((i%2==0) ? FPS : RPG);
            games.add(gameDTO);
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<GameDTO> getGames(){
                return games;
        }

    @RequestMapping(value = "/{gameId}",method = RequestMethod.GET)
    public GameDTO getGame(@PathVariable Integer gameId){
       return games.get(gameId -1);
    }

    @RequestMapping(value = "/{gameId}",method = RequestMethod.DELETE)
    public GameDTO deleteGame(@PathVariable final int gameId){
        GameDTO gameDelete = games.get(gameId - 1);
        games.remove(gameId -1);
        FluentIterable.from(games).allMatch(new Predicate<GameDTO>() {
            @Override
            public boolean apply(GameDTO input) {
                return input.getId() == gameId;
            }
        });
        return gameDelete;


    }



    @RequestMapping(method = RequestMethod.POST)
    public GameDTO createGame(@RequestBody GameDTO gameDTOReceived){
        String name = gameDTOReceived.getName();
        GameDTO.Type type =gameDTOReceived.getType();

        GameDTO gameDTO = new GameDTO();
        int id = games.size() + 1;
        gameDTO.setId(id);
        gameDTO.setName(name);
        gameDTO.setType(type);
        games.add(gameDTO);
        return gameDTO;
    }


}
