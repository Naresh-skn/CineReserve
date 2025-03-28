package com.project.BookMyShow.Service;

import com.project.BookMyShow.DTO.CityDTO;
import com.project.BookMyShow.DTO.TheatreDTO;
import com.project.BookMyShow.Entity.City;
import com.project.BookMyShow.Entity.SeatTypeConfiguration;
import com.project.BookMyShow.Entity.Theatre;
import com.project.BookMyShow.Repository.CityRepository;
import com.project.BookMyShow.Repository.TheatreRepository;
import com.project.BookMyShow.exception.GenException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService{

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CityDTO createNewCity(CityDTO cityDTO) {
        City city = cityRepository.findByCityName(cityDTO.getCityName());
        if(city!=null)
            throw new GenException("City Name present");
        city = modelMapper.map(cityDTO,City.class);
        City savedCity = cityRepository.save(city);
        return modelMapper.map(savedCity,CityDTO.class);
    }

    @Override
    public List<CityDTO> getAllCities() {
        List<City> cityList = cityRepository.findAll();
        return cityList.stream().map(city->
                        modelMapper.map(city,CityDTO.class)
                ).toList();

    }

    @Override
    public CityDTO updateCity(CityDTO cityDTO) {
        City city = cityRepository.findById(cityDTO.getCityId())
                .orElseThrow(()->new GenException("City Not Found"));
        city.setCityName(cityDTO.getCityName());
        city.setCountry(city.getCountry());
        city.setState(cityDTO.getState());
        City updatedCity = cityRepository.save(city);
        return modelMapper.map(updatedCity,CityDTO.class);

    }

    @Override
    public CityDTO deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(()->new GenException("City Not Found"));
        cityRepository.delete(city);
        return modelMapper.map(city,CityDTO.class);

    }

    @Override
    public TheatreDTO createTheatre(TheatreDTO theatreDTO) {

        City city = cityRepository.findById(theatreDTO.getCityId())
                .orElseThrow(()->new GenException("City Not Found"));

        Theatre theatre = modelMapper.map(theatreDTO, Theatre.class);

        List<SeatTypeConfiguration> seatConfigurations = theatreDTO.getSeatConfigurations().
                stream()
                .map(dto -> new SeatTypeConfiguration(dto.getSeatType(), dto.getSeatCount(), dto.getPrice(), theatre))
                .toList();
        theatre.setSeatConfigurations(seatConfigurations);

        Theatre theatreFromDb = theatreRepository.save(theatre);
        return modelMapper.map(theatreFromDb, TheatreDTO.class);
    }

    @Override
    public List<TheatreDTO> getTheatreFromCity(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(()->new GenException("City Not Found"));
        List<Theatre> theatres = theatreRepository.findByCity_CityId(cityId);
        return theatres.stream().map(theatre->
                modelMapper.map(theatre,TheatreDTO.class)
        ).toList();
    }

    @Override
    public TheatreDTO updateTheatre(TheatreDTO theatreDTO) {
        City city = cityRepository.findById(theatreDTO.getCityId())
                .orElseThrow(()->new GenException("City Not Found"));
        Theatre theatre = theatreRepository.findById(theatreDTO.getTheatreId())
                        .orElseThrow(()->new GenException("Theatre Not Found"));

        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setAddress(theatreDTO.getAddress());
        theatre.setCapacity(theatreDTO.getCapacity());
        theatre.setCity(city);

        theatre.getSeatConfigurations().clear();

        theatreDTO.getSeatConfigurations().forEach(seatDTO -> {
            SeatTypeConfiguration newConfig = new SeatTypeConfiguration(
                    seatDTO.getSeatType(),
                    seatDTO.getSeatCount(),
                    seatDTO.getPrice(),
                    theatre
            );
            theatre.getSeatConfigurations().add(newConfig);
        });

        Theatre updatedTheatre = theatreRepository.save(theatre);

        return modelMapper.map(updatedTheatre,TheatreDTO.class);
    }


}
