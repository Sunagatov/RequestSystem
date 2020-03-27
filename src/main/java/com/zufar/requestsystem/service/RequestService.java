package com.zufar.requestsystem.service;

import com.zufar.requestsystem.configuration.CustomUserDetailsService;
import com.zufar.requestsystem.dto.RequestDTO;
import com.zufar.requestsystem.dto.UserDTO;
import com.zufar.requestsystem.entity.Request;
import com.zufar.requestsystem.entity.User;
import com.zufar.requestsystem.exception.RequestNotFoundException;
import com.zufar.requestsystem.repository.RequestRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class RequestService {

    private static final Logger LOGGER = LogManager.getLogger(RequestService.class);
    private final RequestRepository requestRepository;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public RequestService(RequestRepository requestRepository, CustomUserDetailsService userDetailsService) {
        this.requestRepository = requestRepository;
        this.userDetailsService = userDetailsService;
    }

    public List<RequestDTO> getAll() {
        return ((List<Request>) this.requestRepository.findAll())
                .stream()
                .map(RequestService::convertToRequestDTO)
                .collect(Collectors.toList());
    }

    public RequestDTO getById(Long id) {
        Request statusEntity = this.requestRepository.findById(id).orElseThrow(() -> {
            String errorMessage = "Getting an request with id=" + id + " is impossible. There is no a sort attribute.";
            RequestNotFoundException requestNotFoundException = new RequestNotFoundException(errorMessage);
            LOGGER.error(errorMessage, requestNotFoundException);
            return requestNotFoundException;
        });
        return RequestService.convertToRequestDTO(statusEntity);
    }

    public RequestDTO save(RequestDTO request) {
        Request requestEntity = RequestService.convertToRequest(request);
        requestEntity = this.requestRepository.save(requestEntity);
        return RequestService.convertToRequestDTO(requestEntity);
    }

    public RequestDTO update(RequestDTO request) {
        this.isExists(request.getId());
        Request requestEntity = RequestService.convertToRequest(request);
        requestEntity = this.requestRepository.save(requestEntity);
        return RequestService.convertToRequestDTO(requestEntity);
    }

    public void deleteById(Long id) {
        this.isExists(id);
        this.requestRepository.deleteById(id);
    }

    public Boolean isExists(Long id) {
        if (!this.requestRepository.existsById(id)) {
            String errorMessage = "The request with id = " + id + " not found.";
            RequestNotFoundException requestNotFoundException = new RequestNotFoundException(errorMessage);
            LOGGER.error(errorMessage, requestNotFoundException);
            throw requestNotFoundException;
        }
        return true;
    }

    public static RequestDTO convertToRequestDTO(Request request) {
        Objects.requireNonNull(request, "There is no request to convert.");
        UserDTO user = UserService.convertToUserDTO(request.getUser());
        return new RequestDTO(
                request.getId(),
                request.getTitle(),
                request.getDescription(),
                user
        );
    }

    public static Request convertToRequest(RequestDTO request) {
        Objects.requireNonNull(request, "There is no request to convert.");
        User user = UserService.convertToUser(request.getUser());
        return new Request(
                request.getId(),
                request.getTitle(),
                request.getDescription(),
                user
        );
    }

    public RequestDTO createRequest(String title, String description) {
        RequestDTO request = new RequestDTO();
        request.setId(null);
        request.setTitle(title);
        request.setDescription(description);
        UserDTO currentUser = userDetailsService.getCurrentUser();
        request.setUser(currentUser);
        return request;
    }

    public RequestDTO createRequest(String title, String description, UserDTO user) {
        RequestDTO request = new RequestDTO();
        request.setId(null);
        request.setTitle(title);
        request.setDescription(description);
        request.setUser(user);
        return request;
    }
}
