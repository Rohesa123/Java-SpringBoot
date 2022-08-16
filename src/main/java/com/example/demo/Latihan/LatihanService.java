package com.example.demo.Latihan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LatihanService {
    private final LatihanRepository latihanRepository;
    
    @Autowired
    public LatihanService(LatihanRepository latihanRepository) {
        this.latihanRepository = latihanRepository;
    }

    public List<Latihan> getLatihans() {
        return latihanRepository.findAll();
    }

    public Optional<Latihan> showLatihan(Long latihanId) {
        if (latihanRepository.findById(latihanId) == null) {
            throw new IllegalStateException("Latihan with id "+latihanId+" Does not exists");
        }
        return latihanRepository.findById(latihanId);
    }

    public void addNewLatihan(Latihan latihan) {
        Optional<Latihan> latihanOptional = latihanRepository.findLatihanByEmail(latihan.getEmail());
        if(latihanOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        latihanRepository.save(latihan);
    }

    public void deleteLatihan(Long latihanId) {
        boolean exist = latihanRepository.existsById(latihanId);
        if(!exist) {
            throw new IllegalStateException("Latihan with id "+ latihanId + " does not exists");
        }
        latihanRepository.deleteById(latihanId);
    }

    @Transactional
    public void updateLatihan(Long latihanId, String nama, String jk, String email) {
        Latihan latihan = latihanRepository.findById(latihanId).orElseThrow(() -> new IllegalStateException("Latihan with id "+ latihanId + " does not exists"));

        if (nama != null && nama.length() > 0 && !Objects.equals(latihan.getNama(),nama)) {
            latihan.setNama(nama);
        }

        if (jk != null && jk.length() > 0 && !Objects.equals(latihan.getJk(),jk)) {
            latihan.setJk(jk);
        }

        if (email != null && email.length() > 0 && !Objects.equals(latihan.getEmail(),email)) {
            Optional<Latihan> latihanOptional = latihanRepository.findLatihanByEmail(email);
            if (latihanOptional.isPresent()) {
                throw new IllegalStateException("Email Taken");
            }
            latihan.setEmail(email);
        }
    }
}
