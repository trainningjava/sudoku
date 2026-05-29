package br.com.dio.service;

import br.com.dio.model.Board;
import br.com.dio.model.GameStatusEnum;
import br.com.dio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final static int BOARD_LIMIT = 9;

    private final Board board;

    public BoardService(final Map<String, String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces(){
        return board.getSpaces();
    }

    public void reset(){
        board.reset();
    }

    public boolean hasErrors(){
        return board.hasErrors();
    }

    public GameStatusEnum getStatus(){
        return board.getStatus();
    }

    public boolean gameIsFinished(){
        return board.gameIsFinished();
    }

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = gameConfig.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        return spaces;
    }

    public boolean isBoardValid() {

        var spaces = board.getSpaces();

        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {

                Integer value = spaces.get(col).get(row).getActual();

                if (value == null) {
                    continue;
                }

                if (!isValidPosition(col, row, value)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidPosition(int col, int row, int value) {
        return validateRow(col, row, value)
                && validateColumn(col, row, value)
                && validateSector(col, row, value);
    }

    private boolean validateRow(int col, int row, int value) {

        for (int r = 0; r < 9; r++) {

            if (r == row) {
                continue;
            }

            Integer actual =
                    board.getSpaces().get(col).get(r).getActual();

            if (actual != null && actual.equals(value)) {
                return false;
            }
        }

        return true;
    }

    private boolean validateColumn(int col, int row, int value) {

        for (int c = 0; c < 9; c++) {

            if (c == col) {
                continue;
            }

            Integer actual =
                    board.getSpaces().get(c).get(row).getActual();

            if (actual != null && actual.equals(value)) {
                return false;
            }
        }

        return true;
    }

    private boolean validateSector(int col, int row, int value) {

        int startCol = (col / 3) * 3;
        int startRow = (row / 3) * 3;

        for (int c = startCol; c < startCol + 3; c++) {
            for (int r = startRow; r < startRow + 3; r++) {

                if (c == col && r == row) {
                    continue;
                }

                Integer actual =
                        board.getSpaces().get(c).get(r).getActual();

                if (actual != null && actual.equals(value)) {
                    return false;
                }
            }
        }

        return true;
    }
}
