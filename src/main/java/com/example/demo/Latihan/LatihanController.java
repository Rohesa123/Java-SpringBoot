package com.example.demo.Latihan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/latihan")
public class LatihanController {

    private final LatihanService latihanService;

    @Autowired
    public LatihanController(LatihanService latihanService) {
        this.latihanService = latihanService;
    }

    @GetMapping
    public List<Latihan> getLatihans() {
        return latihanService.getLatihans();
    }

    @GetMapping("/{latihanId}/show")
    public Optional<Latihan> showLatihan(@PathVariable("latihanId") Long latihanId) {
        return latihanService.showLatihan(latihanId);
    }

    @PostMapping
    public void registerNewLatihan(@RequestBody Latihan latihan) {
        latihanService.addNewLatihan(latihan);
    }

    @DeleteMapping(path = "{latihanId}")
    public void deleteLatihan
            (@PathVariable("latihanId") Long latihanId) {
        latihanService.deleteLatihan(latihanId);
    }

    @PutMapping(path = "{latihanId}")
    public void updateLatihan(
            @PathVariable("latihanId") Long latihanId,
            @RequestParam(required = false) String nama,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String jk) {
        latihanService.updateLatihan(latihanId, nama, jk ,email);
    }
}
