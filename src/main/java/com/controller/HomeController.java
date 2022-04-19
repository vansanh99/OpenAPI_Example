package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.beans.Animals;
import com.beans.Cat;
import com.beans.Pet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "pet", description = "the pet API", externalDocs = @ExternalDocumentation(url = "http://example.com", description = "vi du"))
@RequestMapping("/api")
public class HomeController {
	private class AnimalsPet extends Animals<Pet> {
	};

	private class AnimalsCat extends Animals<Cat> {
	};

	/**
	 * @return Mono<ResponseEntity<Pet>>
	 */
	@Operation(summary = "find all your pets", description = "return all the pets, with response body", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json", /* array = @ArraySchema( */schema = @Schema(implementation = AnimalsPet.class)) }, required = true))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "here all the pet", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AnimalsPet.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid  supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "pet not found", content = @Content) })
	@GetMapping("/list")
	public Mono<ResponseEntity<List<Animals<Cat>>>> list() {
		List<Animals<Cat>> lst = new ArrayList<>();
		Cat p = Cat.builder()
				.id("123")
				.nameCat("meo")
				.build();
		Animals<Cat> a = new Animals<>();
		a.setId("345");
		a.setAnimal(p);
		lst.add(a);
		return Mono.just(ResponseEntity.ok()
				.body(lst));
	}

	/**
	 * @return Mono<ResponseEntity<Pet>>
	 */
	@Operation(summary = "add a pet", description = "return the added pet")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "here the pet", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalsCat.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid pet supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "pet not found", content = @Content) })
	@PostMapping("/add")
	public Mono<ResponseEntity<Animals<Pet>>> add(@RequestBody Animals<Pet> p) {
		return Mono.just(ResponseEntity.ok()
				.body(p));
	}

	/**
	 * @return Mono<ResponseEntity<Pet>>
	 */
	@Operation(summary = "get a pet", description = "return the pet by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "here the pet by id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "pet not found", content = @Content) })
	@GetMapping("/get/{id}")
	public Mono<ResponseEntity<Pet>> get(
			@Parameter(description = "id of pet to be searched", in = ParameterIn.PATH) @PathVariable("id") String id) {
		Pet p = Pet.builder()
				.id(id)
				.name("meo " + id)
				.build();
		return Mono.just(ResponseEntity.ok()
				.body(p));
	}

	/**
	 * @return Mono<ResponseEntity<Pet>>
	 */
	@Operation(summary = "update a pet", description = "return the updated pet")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "here your pet", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cat.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid pet supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "pet not found", content = @Content) })
	@Deprecated
	@PutMapping("/update")
	public Mono<ResponseEntity<Cat>> update(@RequestBody Cat p) {
		return Mono.just(ResponseEntity.ok()
				.body(p));
	}
}
