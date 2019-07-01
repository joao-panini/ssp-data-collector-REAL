package com.services;

import com.entity.WVIEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import com.repository.WVIRepository;


@Service
public class WVIService {

    @Autowired
    private WVIRepository wviRepository;

    public WVIService(WVIRepository repo){
        wviRepository = repo;
    }

    public WVIEntity saveIndicator(WVIEntity WVIEntity){
        return wviRepository.save(WVIEntity);
    }

}
