package org.example.locationservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.locationservice.api.swagger.LocationResponseSwagger;
import org.example.locationservice.model.LocationRequest;
import org.example.locationservice.model.LocationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Valid
@LocationResponseSwagger
@Tag(name = "Location Resource", description = "Location CRUD Operations")
@RequestMapping(value = "/locations", produces = APPLICATION_JSON_VALUE)
public interface LocationResource {
    @Operation(summary = "This API creates location details",
            description = "This API creates location details in database")
    @PostMapping(path = "/create")
    ResponseEntity<LocationResponse> createLocation(
            @Valid @RequestBody LocationRequest request);

    @Operation(summary = "This API fetches location details",
            description = "This API fetches location details using location id")
    @GetMapping(path = "/location-details/{locationId}")
    ResponseEntity<LocationResponse> getLocation(@PathVariable Long locationId);

    @Operation(summary = "This API fetches all location details",
            description = "This API fetches all location records from database")
    @GetMapping(path = "/all-location-details")
    ResponseEntity<List<LocationResponse>> getAllLocations();

    @Operation(summary = "This API updates location details",
            description = "This API updates location details using location id")
    @PutMapping(path = "/update-location-details/{locationId}")
    ResponseEntity<LocationResponse> updateLocation(@PathVariable Long locationId,
                                                    @Valid @RequestBody LocationRequest request);

    @Operation(summary = "This API deletes location details",
            description = "This API deletes location details using location id")
    @DeleteMapping(path = "/delete-location-details/{locationId}")
    ResponseEntity<Void> deleteLocation(@PathVariable Long locationId);
}
