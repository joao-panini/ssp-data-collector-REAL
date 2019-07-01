package com.controller;

import com.entity.WVIEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.services.WVIService;


@Controller
public class WVIController {

    @Autowired
    private WVIService wviService;

    @Autowired
    private WVIEntity wviEntity;

    public void addIndicator(WVIEntity WVIEntity){
        wviEntity.setAno(WVIEntity.getAno());
        wviEntity.setMes(WVIEntity.getMes());
        wviEntity.setNumAmeacas(WVIEntity.getNumAmeacas());
        wviEntity.setNumEstupros(WVIEntity.getNumEstupros());
        wviEntity.setNumFeminicidioConsumado(WVIEntity.getNumFeminicidioConsumado());
        wviEntity.setNumFeminicidioTentado(WVIEntity.getNumFeminicidioTentado());
        wviEntity.setNumLesoesCorporais(WVIEntity.getNumLesoesCorporais());
        wviService.saveIndicator(wviEntity);
    }

}
