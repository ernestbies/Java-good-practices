package pl.edu.pwsztar.domain.chess;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RockTest {

    private RulesOfGame rook = new RulesOfGame.Rock();

    @Tag("Rock")
    @ParameterizedTest
    @CsvSource({
            "0, 8, 0, 6",
            "-3, 0, 9, 0",
            "-1, 4, -1, 2",
            " 2, 1, 2, -1",
    })
    void checkCorrectMoveForRock(int xStart, int yStart, int xStop, int yStop) {
        assertTrue(rook.isCorrectMove(xStart, yStart, xStop, yStop));
    }

    @ParameterizedTest
    @CsvSource({
            "6, -4, 10, -2",
            "7, -3, 20, 1",
            "5, 1, 2, 3",
            "4, 7, 9, 1"
    })
    void checkIncorrectMoveForRock(int xStart, int yStart, int xStop, int yStop) {
        assertFalse(rook.isCorrectMove(xStart, yStart, xStop, yStop));
    }
}
