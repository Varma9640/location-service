package org.example.locationservice.api.rest.impl;

import org.example.locationservice.BaseTestClass;
import org.example.locationservice.domain.service.LocationService;
import org.example.locationservice.model.LocationRequest;
import org.example.locationservice.model.LocationResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class LocationResourceImplTest extends BaseTestClass {
    @InjectMocks
    private LocationResourceImpl locationResource;

    @Mock
    private LocationService locationService;

    @Test
    public void createLocationTest() {

        when(locationService.createLocation(any()))
                .thenReturn(getLocationResponse());

        ResponseEntity<LocationResponse> response = locationResource.createLocation(getLocationRequest());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getLocationTest() {

        when(locationService.getLocation(1L)).thenReturn(getLocationResponse());
        ResponseEntity<LocationResponse> response = locationResource.getLocation(1L);
        assertNotNull(response);
        assertEquals(1L, response.getBody().getLocationId());
    }

    @Test
    public void getAllLocationsTest() {

        when(locationService.getAllLocations()).thenReturn(List.of(getLocationResponse()));
        ResponseEntity<List<LocationResponse>> response = locationResource.getAllLocations();
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void updateLocationTest() {

        when(locationService.updateLocation(anyLong(), any(LocationRequest.class))).thenReturn(getLocationResponse());
        ResponseEntity<LocationResponse> response = locationResource.updateLocation(1L, getLocationRequest());
        assertNotNull(response);
        assertEquals(1L, response.getBody().getLocationId());
    }

    @Test
    public void deleteLocationTest() {

        doNothing().when(locationService).deleteLocation(1L);
        ResponseEntity<Void> response = locationResource.deleteLocation(1L);
        assertEquals(204, response.getStatusCodeValue());
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

    private LocationResponse getLocationResponse() {

        return LocationResponse.builder()
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
