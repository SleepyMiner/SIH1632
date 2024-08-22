package org.au.careercove.api.data.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping()
@Api(value = "ping", description = "Ping Endpoint", tags = { "ping" })
public class PingController {
	
	@GetMapping(path = { "/ping" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Service ping endpoint", tags = { "ping" })
	public ResponseEntity<Object> ping() {
		return ResponseEntity.ok("{\"message\": \"Hello there!!!\"}");
	}
}
