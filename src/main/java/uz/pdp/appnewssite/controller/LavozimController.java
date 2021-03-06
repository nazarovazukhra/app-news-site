package uz.pdp.appnewssite.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.service.LavozimService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {

    final LavozimService lavozimService;

    public LavozimController(LavozimService lavozimService) {
        this.lavozimService = lavozimService;
    }

    @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody LavozimDto lavozimDto) {

        ApiResponse apiResponse = lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('EDIT_LAVOZIM')")
    @PutMapping("/{id}")
    public HttpEntity<?> editLavozim(@PathVariable Long id, @Valid @RequestBody LavozimDto lavozimDto) {

        ApiResponse apiResponse = lavozimService.editLavozim(id, lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('DELETE_LAVOZIM')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletetLavozim(@PathVariable Long id) {

        ApiResponse apiResponse = lavozimService.deleteLavozim(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('VIEW_LAVOZIMLAR')")
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Lavozim> lavozims = lavozimService.getAll();
        return ResponseEntity.ok(lavozims);
    }


}
