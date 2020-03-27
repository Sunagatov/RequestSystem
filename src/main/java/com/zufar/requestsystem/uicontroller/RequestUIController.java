package com.zufar.requestsystem.uicontroller;

import com.zufar.requestsystem.dto.RequestDTO;
import com.zufar.requestsystem.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RequestUIController {

    private final RequestService requestService;

    @Autowired
    public RequestUIController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/requests")
    public String getAllRequests(ModelMap modelMap) {
        List<RequestDTO> requests = requestService.getAll();
        modelMap.addAttribute("requests", requests);
        return "requests";
    }

    @PostMapping("/addRequest")
    public String addRequest(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            ModelMap modelMap
    ) {
        RequestDTO request = requestService.createRequest(title, description);
        requestService.save(request);
        return "requests";
    }


}