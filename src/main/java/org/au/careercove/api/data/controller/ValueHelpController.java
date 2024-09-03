package org.au.careercove.api.data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.au.careercove.api.data.utils.ValueHelp;
import org.au.careercove.api.data.utils.ValueType;
import org.au.careercove.api.data.utils.ValueHelp.ALLOWED_TYPES;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "values", description = "Value Helps endpoint", tags = { "values" })
public class ValueHelpController {

	@GetMapping(path = { "/values" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Value helps endpoint", tags = { "values" })
	public ResponseEntity<Object> getValues() {

		Map<ALLOWED_TYPES, List<ValueType>> valueHelps = new HashMap<>();

		valueHelps.put(ALLOWED_TYPES.GENDER, ValueHelp.getValueHelps(ALLOWED_TYPES.GENDER));
		valueHelps.put(ALLOWED_TYPES.USER, ValueHelp.getValueHelps(ALLOWED_TYPES.USER));
		valueHelps.put(ALLOWED_TYPES.ORGANIZATION, ValueHelp.getValueHelps(ALLOWED_TYPES.ORGANIZATION));
		valueHelps.put(ALLOWED_TYPES.PAY, ValueHelp.getValueHelps(ALLOWED_TYPES.PAY));
		valueHelps.put(ALLOWED_TYPES.SKILLS, ValueHelp.getValueHelps(ALLOWED_TYPES.SKILLS));

		return ResponseEntity.ok(valueHelps);
	}
}
