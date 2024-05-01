package roomescape.reservation.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import roomescape.time.domain.ReservationTime;

public class ReservationTest {

    @Test
    @DisplayName("이름에 빈 값이 들어간 경우 예외가 발생한다.")
    void nameEmptyException() {
        assertThatThrownBy(() -> new Reservation(
                0L, " ", LocalDate.now(), new ReservationTime(0, LocalTime.now()))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name cannot be empty");
    }

    @Test
    @DisplayName("이름이 10글자 초과일 경우 예외가 발생한다.")
    void nameLengthException() {
        assertThatThrownBy(() -> new Reservation(
                0L, "abcdefghij1", LocalDate.now(), new ReservationTime(0, LocalTime.now())
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name cannot exceed 10 characters");
    }

    @Test
    @DisplayName("날짜와 시간이 과거일 경우 예외가 발생한다.")
    void dateTimeException() {
        assertAll(
                () -> assertThatThrownBy(() -> new Reservation(
                        0,
                        "brown",
                        LocalDate.now().minusDays(1),
                        new ReservationTime(0, LocalTime.now()))
                ).isInstanceOf(IllegalArgumentException.class),

                () -> assertThatThrownBy(() -> new Reservation(
                        0,
                        "brown",
                        LocalDate.now(),
                        new ReservationTime(0, LocalTime.now().minusHours(1)))
                ).isInstanceOf(IllegalArgumentException.class)
        );
    }
}