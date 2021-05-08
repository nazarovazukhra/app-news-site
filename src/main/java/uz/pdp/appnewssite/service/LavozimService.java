package uz.pdp.appnewssite.service;

import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.repository.LavozimRepository;

import java.util.List;
import java.util.Optional;

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


        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(id);
        if (!optionalLavozim.isPresent())
            return new ApiResponse("Such lavozim not found", false);

        Lavozim editingLavozim = optionalLavozim.get();
        editingLavozim.setName(lavozimDto.getName());
        editingLavozim.setDescription(editingLavozim.getDescription());
        editingLavozim.setHuquqList(lavozimDto.getHuquqList());
        lavozimRepository.save(editingLavozim);
        return new ApiResponse("Lavozim edited", true);
    }

    public ApiResponse deleteLavozim(Long id) {

        boolean existsById = lavozimRepository.existsById(id);
        if (!existsById)
            return new ApiResponse("Such lavozim not found", false);
        lavozimRepository.deleteById(id);
        return new ApiResponse("Lavozim deleted", true);
    }

    public List<Lavozim> getAll() {

        return lavozimRepository.findAll();

    }
}
