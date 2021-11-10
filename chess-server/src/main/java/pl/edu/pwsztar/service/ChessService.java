package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dto.FigureMoveDto;

public interface ChessService {
    boolean isCorrectMove(FigureMoveDto figureMoveDto);
}
