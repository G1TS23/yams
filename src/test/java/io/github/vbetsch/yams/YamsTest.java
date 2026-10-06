package io.github.vbetsch.yams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YamsTest {
    @Test
    void chance_return15Points_whenGivenLargeStraight() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(1, 2, 3, 4, 5), CategoryEnum.CHANCE);

        // Assert
        assertEquals(15, result);
    }

    @Test
    void chance_return16Points_whenGivenSmallStraight() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(1, 2, 3, 4, 6), CategoryEnum.CHANCE);

        // Assert
        assertEquals(16, result);
    }

    @Test
    void yams_return50Points_whenGivenFiveDicesSix() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(6, 6, 6, 6, 6), CategoryEnum.YAMS);

        // Assert
        assertEquals(50, result);
    }

    @Test
    void yams_return50Points_whenGivenFiveDicesThree() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(3, 3, 3, 3, 3), CategoryEnum.YAMS);

        // Assert
        assertEquals(50, result);
    }

    @Test
    void yams_return0Points_whenGivenRollWithDuplicates() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(1, 1, 2, 2, 3), CategoryEnum.YAMS);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void yams_return0Points_whenGivenRollWithoutDuplicates() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(1, 4, 5, 6, 3), CategoryEnum.YAMS);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void aces_return3Points_whenGivenRollWithThreeDicesAces() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(1, 1, 1, 2, 3), CategoryEnum.ACES);

        // Assert
        assertEquals(3, result);
    }

    @Test
    void twos_return6Points_whenGivenRollWithThreeDicesTwos() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(2, 2, 2, 1, 3), CategoryEnum.TWOS);

        // Assert
        assertEquals(6, result);
    }

    @Test
    void three_return9Points_whenGivenRollWithThreeDicesThrees() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(3, 3, 3, 2, 1), CategoryEnum.THREES);

        // Assert
        assertEquals(9, result);
    }

    @Test
    void four_return12Points_whenGivenRollWithThreeDicesFours() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(4, 4, 4, 2, 3), CategoryEnum.FOURS);

        // Assert
        assertEquals(12, result);
    }

    @Test
    void five_return15Points_whenGivenRollWithThreeDicesFives() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(5, 5, 5, 2, 3), CategoryEnum.FIVES);

        // Assert
        assertEquals(15, result);
    }

    @Test
    void sixes_return18Points_whenGivenRollWithThreeDicesSixes() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(6, 6, 6, 2, 3), CategoryEnum.SIXES);

        // Assert
        assertEquals(18, result);
    }

    @Test
    void threeOfAKind_return3Points_whenGivenThreeDicesOnes() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(1, 1, 1, 2, 3), CategoryEnum.THREE_OF_A_KIND);

        // Assert
        assertEquals(8, result);
    }

    @Test
    void threeOfAKind_return10Points_whenGivenThreeDicesTwos() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(2, 2, 2, 1, 3), CategoryEnum.THREE_OF_A_KIND);

        // Assert
        assertEquals(10, result);
    }

    @Test
    void threeOfAKind_returnIllegalArgumentException_whenGivenLargeStraight() {
        // Arrange
        Yams yams = new Yams();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> yams.computeScore(List.of(1, 2, 3, 4, 5), CategoryEnum.THREE_OF_A_KIND),
                "We cannot compute score with category ThreeOfAKind for this roll"
        );
    }

    @Test
    void fourOfAKind_return7Points_whenGivenFourDicesOnes() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(1, 1, 1, 1, 3), CategoryEnum.FOUR_OF_A_KIND);

        // Assert
        assertEquals(7, result);
    }

    @Test
    void fourOfAKind_return11Points_whenGivenFourDicesTwos() {
        // Arrange
        Yams yams = new Yams();

        // Act
        int result = yams.computeScore(List.of(2, 2, 2, 2, 3), CategoryEnum.FOUR_OF_A_KIND);

        // Assert
        assertEquals(11, result);
    }

    @Test
    void fourOfAKind_returnIllegalArgumentException_whenGivenLargeStraight() {
        // Arrange
        Yams yams = new Yams();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> yams.computeScore(List.of(1, 2, 3, 4, 5), CategoryEnum.FOUR_OF_A_KIND),
                "We cannot compute score with category FourOfAKind for this roll"
        );
    }

    @Test
    void shouldThrowInvalidRollSizeErrorWhenGivenLessThanFiveDices() {
        // Arrange
        Yams yams = new Yams();
        List<Integer> roll = List.of(1, 2, 3, 4);

        // Act & Assert
        assertThrows(
                InvalidRollSizeError.class,
                () -> yams.computeScore(roll, CategoryEnum.CHANCE),
                "Roll must contain exactly 5 dices"
        );
    }

    @Test
    void shouldThrowInvalidRollSizeErrorWhenGivenMoreThanFiveDices() {
        // Arrange
        Yams yams = new Yams();
        List<Integer> roll = List.of(1, 2, 3, 4, 5, 6);

        // Act & Assert
        assertThrows(
                InvalidRollSizeError.class,
                () -> yams.computeScore(roll, CategoryEnum.CHANCE),
                "Roll must contain exactly 5 dices"
        );
    }

    @Test
    void shouldThrowInvalidRollSizeErrorWhenGivenNoDice() {
        // Arrange
        Yams yams = new Yams();
        List<Integer> roll = List.of();

        // Act & Assert
        assertThrows(
                InvalidRollSizeError.class,
                () -> yams.computeScore(roll, CategoryEnum.CHANCE),
                "Roll must contain exactly 5 dices"
        );
    }
}
