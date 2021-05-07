package uz.pdp.appnewssite.service;

import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.repository.LavozimRepository;

@Service
public class LavozimService {

    final LavozimRepository lavozimRepository;

    public LavozimService(LavozimRepository lavozimRepository) {
        this.lavozimRepository = lavozimRepository;
    }

    public ApiResponse addLavozim(LavozimDto lavozimDto) {
        boolean existsByName = lavozimRepository.existsByName(lavozimDto.getName());
        if (existsByName)
            return new ApiResponse("Such lavozim already exists", false);

        Lavozim lavozim = new Lavozim(
                lavozimDto.getName(),
                lavozimDto.getHuquqList(),
                lavozimDto.getDescription()
        );

        lavozimRepository.save(lavozim);
        return new ApiResponse("Lavozim added", true);

    }

    public ApiResponse editLavozim(Long id, LavozimDto lavozimDto) {

        return new ApiResponse("", true);
    }
}
