package ru.axl.probeproject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.AdminApi;
import ru.axl.probeproject.model.PingResponse;

@RestController
public class AdminController implements AdminApi {

    @Override
    public ResponseEntity<PingResponse> ping() {
        PingResponse resp = new PingResponse();
        resp.setResult("success");
        resp.setMessage("Probe project for test testing");

        return ResponseEntity.ok(resp);
    }

}
