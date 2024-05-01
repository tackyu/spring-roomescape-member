package roomescape.time.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import roomescape.reservation.repository.ReservationDao;
import roomescape.time.domain.ReservationTime;
import roomescape.time.repository.ReservationTimeDao;

@Service
public class ReservationTimeService {

    private final ReservationTimeDao reservationTimeDao;
    private final ReservationDao reservationDao;

    public ReservationTimeService(ReservationTimeDao reservationTimeDao, ReservationDao reservationDao) {
        this.reservationTimeDao = reservationTimeDao;
        this.reservationDao = reservationDao;
    }

    public List<ReservationTime> findAll() {
        return reservationTimeDao.findAll();
    }

    public ReservationTime save(ReservationTime reservationTime) {
        if (exists(reservationTime.startAt())) {
            throw new IllegalArgumentException("StartAt already exists");
        }
        return reservationTimeDao.save(reservationTime);
    }

    public boolean exists(LocalTime startAt) {
        return reservationTimeDao.exists(startAt);
    }

    public void delete(long id) {
        if(reservationDao.existsTime(id)){
            throw new IllegalArgumentException("Cannot delete a reservation that refers to that time");
        }
        reservationTimeDao.delete(id);
    }
}
