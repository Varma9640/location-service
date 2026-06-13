package org.example.locationservice.domain.service;

import org.example.locationservice.BaseTestClass;
import org.example.locationservice.domain.entity.Location;
import org.example.locationservice.domain.repository.LocationRepository;
import org.example.locationservice.exception.LocationNotFoundException;
import org.example.locationservice.model.LocationRequest;
import org.example.locationservice.model.LocationResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocationServiceTest extends BaseTestClass {
    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Test
    public void createLocationTest() {

        when(locationRepository.save(any(Location.class))).thenReturn(getLocation());

        LocationResponse response = locationService.createLocation(getLocationRequest());

        assertNotNull(response);
        assertEquals(1L, response.getLocationId());
        verify(locationRepository, times(2)).save(any(Location.class));
    }

    @Test
    public void getLocationTest() {

        when(locationRepository.findById(1L)).thenReturn(Optional.of(getLocation()));

        LocationResponse response = locationService.getLocation(1L);

        assertNotNull(response);
        assertEquals("Hyderabad", response.getCity());
    }

    @Test
    public void getLocationNotFoundTest() {

        when(locationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(LocationNotFoundException.class, () -> locationService.getLocation(1L));
    }

    @Test
    public void getAllLocationsTest() {

        when(locationRepository.findAll()).thenReturn(List.of(getLocation()));

        List<LocationResponse> response = locationService.getAllLocations();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    public void updateLocationTest() {

        when(locationRepository.findById(1L)).thenReturn(Optional.of(getLocation()));
        when(locationRepository.save(any(Location.class))).thenReturn(getLocation());

        LocationResponse response = locationService.updateLocation(1L, getLocationRequest());

        assertNotNull(response);
        verify(locationRepository, times(1)).save(any(Location.class));
    }

    @Test
    public void deleteLocationTest() {

        when(locationRepository.findById(1L)).thenReturn(Optional.of(getLocation()));
        doNothing().when(locationRepository).deleteById(1L);

        locationService.deleteLocation(1L);

        verify(locationRepository, times(1)).deleteById(1L);
    }

    private LocationRequest getLocationRequest() {

        LocationRequest request = new LocationRequest();
        request.setLocationName("ING");
        request.setCity("Hyderabad");
        request.setState("Telangana");
        request.setCountry("India");
        request.setLatitude(17.3850);
        request.setLongitude(78.4867);
        return request;
    }

    private Location getLocation() {

        return Location.builder()
                .locationId(1L)
                .locationName("ING")
                .city("Hyderabad")
                .state("Telangana")
                .country("India")
                .latitude(17.3850)
                .longitude(78.4867)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
    }
}
