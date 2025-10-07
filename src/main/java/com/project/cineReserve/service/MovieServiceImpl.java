package com.project.cineReserve.service;

import com.project.cineReserve.dto.MovieDTO;
import com.project.cineReserve.entity.City;
import com.project.cineReserve.entity.Movie;
import com.project.cineReserve.entity.Show;
import com.project.cineReserve.entity.Theatre;
import com.project.cineReserve.repository.CityRepository;
import com.project.cineReserve.repository.MovieRepository;
import com.project.cineReserve.repository.ShowRepository;
import com.project.cineReserve.repository.TheatreRepository;
import com.project.cineReserve.exception.GenException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

	
	private final TheatreRepository theatreRepository;
	private final ShowRepository showRepository;
	private final MovieRepository movieRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CityRepository cityRepository;


	public MovieServiceImpl(TheatreRepository theatreRepository, ShowRepository showRepository, MovieRepository movieRepository) {
		this.theatreRepository = theatreRepository;
		this.showRepository = showRepository;
		this.movieRepository = movieRepository;
	}


	@Override
	public List<MovieDTO> getAllMovies() {
		List<Movie> movies = movieRepository.findAll();
		return movies.stream().map(movie->modelMapper.map(movie,MovieDTO.class)).toList();
	}

	public Map<String,List<Show>> getAllShows(Long city, Long movie) {
		Optional<List<Long>> theatres = Optional.empty();//theatreRepository.findByCityId(city);
		if(theatres.get().isEmpty())
			throw new GenException("No Theatres found in your City");
		List<Long> theatreList = theatres.get();
		
		Optional<List<Show>> shows = showRepository.findByTheatreIdsAndMovieId(theatreList,movie);
		if(shows.get().isEmpty())
			throw new GenException("No Movies found in your City");
		List<Show> showList = shows.get();
		
		Map<String,List<Show>> map = new HashMap<>();
		
		for(Show eachShow: showList) {
			String threatreName = eachShow.getTheatre().getTheatreName();
			if(map.containsKey(threatreName)) {
				List<Show> show = map.get(threatreName);
				show.add(eachShow);
				map.put(threatreName, show);	
			}
			else
				map.put(threatreName, new ArrayList<>(Arrays.asList(eachShow)));
			
		}
		return map;
	}


	public MovieDTO addNewMovie(@Valid MovieDTO movieDTO) {
		Movie movie = modelMapper.map(movieDTO, Movie.class);
        Movie savedFromDb =  movieRepository.save(movie);
		return modelMapper.map(savedFromDb,MovieDTO.class);
	}

	@Override
	public List<MovieDTO> getAllMoviesFromCity(Long cityId) {

		City city = cityRepository.findById(cityId)
				.orElseThrow(()->new GenException("City Not Found"));

		List<Theatre> theatres = theatreRepository.findByCity(city);

		if(theatres.isEmpty())
			throw new GenException("No Theatre found in the City: "+city.getCityName());

		List<Movie> movies = showRepository.findDistinctMoviesByTheatreIn(theatres);
		if(movies.isEmpty())
			throw new GenException("No Movies found in the City: "+city.getCityName());

		return movies.stream()
				.map(movie -> modelMapper.map(movie,MovieDTO.class))
				.toList();
	}




	public List<Theatre> getthea() {
		// TODO Auto-generated method stub
		return theatreRepository.findAll();
	}


}
