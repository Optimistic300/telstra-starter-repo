package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.SimActivationRequest;
import au.com.telstra.simcardactivator.service.SimActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sim")
public class SimActivationController {

    @Autowired
    private SimActivationService service;

    @PostMapping("/activate")
    public String activateSim(@RequestBody SimActivationRequest request) {
        boolean success = service.forwardToActuator(request.getIccid());
        if (success) {
            return "SIM Activation Successful";
        } else {
            return "SIM Activation Failed";
        }
    }
}