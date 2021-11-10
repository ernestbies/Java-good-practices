package pl.edu.pwsztar.domain.chess;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public interface RulesOfGame {

    /**
     * Metoda zwraca true, tylko gdy przejscie z polozenia
     * (xStart, yStart) na (xEnd, yEnd) w jednym ruchu jest zgodne
     * z zasadami gry w szachy
     */
    boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd);

    @Component
    @Qualifier("Bishop")
    class Bishop implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if(xStart == xEnd && yStart == yEnd) {
                return false;
            }

            return Math.abs(xEnd - xStart) == Math.abs(yEnd - yStart);
        }
    }

    @Component
    @Qualifier("Knight")
    class Knight implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if(xStart == xEnd && yStart == yEnd) {
                return false;
            }

            int column = Math.abs(yEnd - yStart);
            int row = Math.abs(xEnd - xStart);
            return (row == 2 && column == 1) || (row == 1 && column == 2);
        }
    }

    @Component
    @Qualifier("King")
    class King implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if(xStart == xEnd && yStart == yEnd) {
                return false;
            }

            return (Math.abs(xEnd - xStart) < 2) && (Math.abs(yEnd - yStart) < 2);
        }
    }

    @Component
    @Qualifier("Queen")
    class Queen implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if(xStart == xEnd && yStart == yEnd) {
                return false;
            }

            return ((xEnd == xStart) ^ (yEnd == yStart)) || (Math.abs(xEnd - xStart) == Math.abs(yEnd - yStart));
        }
    }

    @Component
    @Qualifier("Rock")
    class Rock implements RulesOfGame {

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if(xStart == xEnd && yStart == yEnd) {
                return false;
            }

            return (xEnd == xStart) ^ (yEnd == yStart);
        }
    }

    @Component
    @Qualifier("Pawn")
    class Pawn implements RulesOfGame {

        private boolean isFirstMove = true;

        @Override
        public boolean isCorrectMove(int xStart, int yStart, int xEnd, int yEnd) {
            if(xStart == xEnd && yStart == yEnd) {
                return false;
            }

            if(isFirstMove) {
                isFirstMove = false;
                return ((yEnd == yStart+1) && (xEnd == xStart)) ^ ((yEnd == yStart+2) && (xEnd == xStart));
            } else {
                return ((yEnd == yStart+1) && (xEnd == xStart));
            }
        }
    }
}
