package com.intenthq.battleship.controller

import com.intenthq.battleship.GameBuilder
import com.intenthq.battleship.GameDataParser
import com.intenthq.battleship.InvalidGameDataException
import com.intenthq.battleship.domain.InvalidGameException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BattleShipController {

    @Autowired private GameDataParser gameDataParser
    @Autowired private GameBuilder gameBuilder

    @RequestMapping('/battleship')
    String battleship() {
        'battleship'
    }

    @RequestMapping('/battleship/exercise')
    String exercise(@RequestParam(value = 'input', required = false) String input, ModelMap model) {
        if (!StringUtils.isEmpty(input)) {
            try {
                model.addAttribute('output', playGame(input))
            }
            catch (InvalidGameDataException | InvalidGameException ex) {
                model.addAttribute('error', ex.message)
            }
        }
        'exercise'
    }

    private playGame(input) {
        def gameData = gameDataParser.process(input)
        def game = gameBuilder.create(gameData)
        game.validate()
        game.play()
        game.format()
    }
}