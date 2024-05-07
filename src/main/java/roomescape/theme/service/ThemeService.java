package roomescape.theme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import roomescape.theme.domain.Theme;
import roomescape.theme.repository.ThemeDao;
import roomescape.theme.response.RankTheme;

@Service
public class ThemeService {

    private final ThemeDao themeDao;

    public ThemeService(ThemeDao themeDao) {
        this.themeDao = themeDao;
    }

    public List<Theme> findAll() {
        return themeDao.findAll();
    }

    public Theme create(Theme theme){
        return themeDao.save(theme);
    }

    public void delete(long themeId){
        themeDao.delete(themeId);
    }

    public List<RankTheme> getRank() {
        return themeDao.getRank();
    }
}
