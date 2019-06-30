package services;

import entity.WomanViolenceIndicatorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import repository.WomanViolenceIndicatorsRepository;

import java.util.List;

public class WomanViolenceIndicatorsServiceImpl implements WomanViolenceIndicatorsService {

    @Autowired
    private WomanViolenceIndicatorsRepository womanViolenceIndicatorsRepository;

    public WomanViolenceIndicatorsEntity saveIndicator(WomanViolenceIndicatorsEntity WVIEntity){
        return womanViolenceIndicatorsRepository.save(WVIEntity);
    }

    public Iterable<WomanViolenceIndicatorsEntity> saveIndicators(List<WomanViolenceIndicatorsEntity> WVIEntitiesList){
        List<WomanViolenceIndicatorsEntity> WVIList = WVIEntitiesList;
        Iterable<WomanViolenceIndicatorsEntity> createIndicators = womanViolenceIndicatorsRepository.saveAll(WVIEntitiesList);
        return createIndicators;
    }

}
