package codeworld.tutorial.components.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import codeworld.tutorial.components.layout.MyUI;
import codeworld.tutorial.dto.Film;
import codeworld.tutorial.service.FilmService;

@Route(value = MovieDetailView.VIEW_NAME,
        layout = MyUI.class)
@PageTitle("movie-screen")
public class MovieDetailView extends Div implements AfterNavigationObserver {

    private static final long serialVersionUID = 5210018881247210736L;

    public static final String VIEW_NAME = "";

    private Grid<Film> filmGrid = new Grid<>();
    private ListDataProvider<Film> dataProvider;

    @Autowired
    private FilmService filmService;

    public MovieDetailView() {
        setSizeFull();
        initFilmGrid();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setPadding(true);
        mainLayout.add(filmGrid);

        add(mainLayout);
    }

    private void initFilmGrid() {
        filmGrid = new Grid<>();
        filmGrid.setSizeFull();
        filmGrid.setColumnReorderingAllowed(true);
        filmGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);

        filmGrid.addColumn(Film::getId)
                .setHeader("ID")
                .setSortable(true);

        filmGrid.addColumn(Film::getTitle)
                .setHeader("TITLE")
                .setSortable(true);

        filmGrid.addColumn(Film::getDescription)
                .setHeader("DESCRIPTION")
                .setSortable(true);

        filmGrid.addColumn(Film::getFeatures)
                .setHeader("FEATURE")
                .setSortable(true);

        filmGrid.addColumn(Film::getLanguage)
                .setHeader("LANGUAGE")
                .setSortable(true);

        filmGrid.addColumn(Film::getRating)
                .setHeader("RATING")
                .setSortable(true);

        filmGrid.addColumn(Film::getRating)
                .setHeader("RATING")
                .setSortable(true);

        filmGrid.addColumn(Film::getReleaseDate)
                .setHeader("REALEASE_DATE")
                .setSortable(true);

    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        Notification.show("navigate to MasterView");
        List<Film> data = filmService.findAll();

        dataProvider = new ListDataProvider<>(data);
        filmGrid.setDataProvider(dataProvider);
    }
}
