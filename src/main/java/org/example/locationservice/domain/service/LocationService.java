package org.example.locationservice.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.locationservice.domain.entity.Location;
import org.example.locationservice.domain.repository.LocationRepository;
import org.example.locationservice.exception.LocationNotFoundException;
import org.example.locationservice.model.LocationRequest;
import org.example.locationservice.model.LocationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private static final String LOCATION_NOT_FOUND =
            "Location not found with id : ";

    private final LocationRepository locationRepository;

    public LocationResponse createLocation(
            LocationRequest request) {

        Location location = Location.builder()
                .locationName(request.getLocationName())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        return buildResponse(
                locationRepository.save(location));
    }

    public LocationResponse getLocation(
            Long locationId) {

        Location location =
                locationRepository.findById(locationId)
                        .orElseThrow(() ->
                                new LocationNotFoundException(
                                        LOCATION_NOT_FOUND + locationId));

        return buildResponse(location);
    }

    public List<LocationResponse> getAllLocations() {

        return locationRepository.findAll()
                .stream()
                .map(this::buildResponse)
                .toList();
    }

    public LocationResponse updateLocation(
            Long locationId,
            LocationRequest request) {

        Location location =
                locationRepository.findById(locationId)
                        .orElseThrow(() ->
                                new LocationNotFoundException(
                                        LOCATION_NOT_FOUND + locationId));

        location.setLocationName(request.getLocationName());
        location.setCity(request.getCity());
        location.setState(request.getState());
        location.setCountry(request.getCountry());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setModifiedDate(LocalDateTime.now());

        return buildResponse(
                locationRepository.save(location));
    }

    public void deleteLocation(
            Long locationId) {

        locationRepository.findById(locationId)
                .orElseThrow(() ->
                        new LocationNotFoundException(
                                LOCATION_NOT_FOUND + locationId));

        locationRepository.deleteById(locationId);
    }

    private LocationResponse buildResponse(
            Location location) {

        return LocationResponse.builder()
                .locationId(location.getLocationId())
                .locationName(location.getLocationName())
                .city(location.getCity())
                .state(location.getState())
                .country(location.getCountry())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .createDate(location.getCreateDate())
                .modifiedDate(location.getModifiedDate())
                .build();
    }
}
