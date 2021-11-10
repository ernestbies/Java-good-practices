package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.chess.RulesOfGame;
import pl.edu.pwsztar.domain.dto.FigureMoveDto;
import pl.edu.pwsztar.domain.enums.FigureType;
import pl.edu.pwsztar.service.ChessService;

import java.util.Arrays;
import java.util.List;

@Service
public class ChessServiceImpl implements ChessService {

    private RulesOfGame bishop;
    private RulesOfGame knight;
    private RulesOfGame king;
    private RulesOfGame rock;
    private RulesOfGame queen;
    private RulesOfGame pawn;


    @Autowired
    public ChessServiceImpl(@Qualifier("Bishop") RulesOfGame bishop,
                            @Qualifier("Knight") RulesOfGame knight,
                            @Qualifier("King") RulesOfGame king,
                            @Qualifier("Rock") RulesOfGame rock,
                            @Qualifier("Queen") RulesOfGame queen,
                            @Qualifier("Pawn") RulesOfGame pawn) {
        this.bishop = bishop;
        this.knight = knight;
        this.king = king;
        this.rock = rock;
        this.queen = queen;
        this.pawn = pawn;
    }

    @Override
    public boolean isCorrectMove(FigureMoveDto figureMoveDto) {
        int xStart;
        int yStart;
        int xEnd;
        int yEnd;

        List<String> startPosition = Arrays.asList(figureMoveDto.getStart().split("_"));
        List<String> destinationPosition = Arrays.asList(figureMoveDto.getDestination().split("_"));

        xStart = columnNameToInteger(startPosition.get(0));
        xEnd = columnNameToInteger(destinationPosition.get(0));

        yStart = Integer.parseInt(startPosition.get(1));
        yEnd = Integer.parseInt(destinationPosition.get(1));

        FigureType figureType = figureMoveDto.getType();
        switch(figureType) {
            case KING:
                return king.isCorrectMove(xStart, yStart, xEnd, yEnd);
            case PAWN:
                return pawn.isCorrectMove(xStart, yStart, xEnd, yEnd);
            case ROCK:
                return rock.isCorrectMove(xStart, yStart, xEnd, yEnd);
            case QUEEN:
                return queen.isCorrectMove(xStart, yStart, xEnd, yEnd);
            case BISHOP:
                return bishop.isCorrectMove(xStart, yStart, xEnd, yEnd);
            case KNIGHT:
                return knight.isCorrectMove(xStart, yStart, xEnd, yEnd);
            default:
                return false;
        }
    }

    private int columnNameToInteger(String columnName){
        return columnName.charAt(0) - 96;
    }
}
