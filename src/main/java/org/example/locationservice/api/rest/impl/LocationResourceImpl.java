package org.example.locationservice.api.rest.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.locationservice.api.LocationResource;
import org.example.locationservice.domain.service.LocationService;
import org.example.locationservice.model.LocationRequest;
import org.example.locationservice.model.LocationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LocationResourceImpl implements LocationResource {
    private final LocationService locationService;

    @Override
    public ResponseEntity<LocationResponse> createLocation(LocationRequest request) {
        return ResponseEntity.ok(locationService.createLocation(request));
    }

    @Override
    public ResponseEntity<LocationResponse> getLocation(Long locationId) {
        return ResponseEntity.ok(locationService.getLocation(locationId));
    }

    @Override
    public ResponseEntity<List<LocationResponse>>
    getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @Override
    public ResponseEntity<LocationResponse> updateLocation(Long locationId, LocationRequest request) {
        return ResponseEntity.ok(locationService.updateLocation(locationId, request));
    }

    @Override
    public ResponseEntity<Void> deleteLocation(Long locationId) {
        locationService.deleteLocation(locationId);
        return ResponseEntity.noContent().build();
    }
}
