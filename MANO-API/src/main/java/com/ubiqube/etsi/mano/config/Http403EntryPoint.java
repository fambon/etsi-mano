package com.ubiqube.etsi.mano.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

@Service
public class Http403EntryPoint implements AuthenticationEntryPoint {
	private static final Logger LOG = LoggerFactory.getLogger(Http403EntryPoint.class);
	private final ObjectMapper mapper;

	@Inject
	public Http403EntryPoint(ObjectMapper _mapper) {
		super();
		mapper = _mapper;
	}

	@Override
	public void commence(HttpServletRequest _request, HttpServletResponse _response, AuthenticationException _authException) throws IOException, ServletException {
		LOG.error("Auth failed");
		_response.setContentType(MediaType.APPLICATION_JSON);
		_response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		final PrintWriter out = _response.getWriter();
		final ProblemDetails problemDetails = new ProblemDetails(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
		out.print(mapper.writeValueAsString(problemDetails));
	}

}