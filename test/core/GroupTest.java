package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class GroupTest {

    Group first;
    Group second;

    @Test
    void equalsInCells() {
        first = new Group(List.of(new GameCell(0,0), new GameCell(4,4)), 2);
        second = new Group(List.of(new GameCell(0,0), new GameCell(4,4)), 2);
        assertTrue(first.equalsInCells(second));
        assertTrue(second.equalsInCells(first));

        first.setBombs(1);
        assertFalse(first.equalsInCells(second));
        assertFalse(second.equalsInCells(first));

        List<GameCell> cellsOfFirst = new ArrayList<>();
        cellsOfFirst.add(new GameCell(0,0));
        cellsOfFirst.add(new GameCell(1, 2));
        first.setCellsOfGroup(cellsOfFirst);
        assertFalse(first.equalsInCells(second));
        assertFalse(second.equalsInCells(first));
    }

    @Test
    void remove() {
        first = new Group(new ArrayList<>(), 2);
        List<GameCell> toAddFirst = List.of(new GameCell(0,0), new GameCell(4,4));
        first.getCells().addAll(toAddFirst);
        second = new Group(new ArrayList<>(), 1);
        List<GameCell> toAddSecond = List.of(new GameCell(4,4));
        second.getCells().addAll(toAddSecond);
        assertTrue(first.remove(second));
        assertEquals(new Group(List.of(new GameCell(0,0)), 1),first);
    }
}