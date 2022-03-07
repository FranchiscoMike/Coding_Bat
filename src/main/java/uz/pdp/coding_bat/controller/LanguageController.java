package uz.pdp.coding_bat.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.LanguageDTO;
import uz.pdp.coding_bat.service.LanguageService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class LanguageController {
    /**
     * showing error messages in the front
     *
     * @param ex
     * @return
     */

    //hammag controllerda bo'ladi
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            @NotNull MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    final LanguageService languageService;

    /**
     * show all
     *
     * @return all languages
     */
    @GetMapping
    public ResponseEntity<?> all() {
        ApiResponse apiResponse = languageService.all();
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     *
     * @param id from front
     * @return one language using id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.one(id);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     *  delete function using id
     * @param id
     * @return not
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.delete(id);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     *
     * @param dto for making new language
     * @return added language
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody LanguageDTO dto){
        ApiResponse apiResponse = languageService.add(dto);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     *  for editing data return edited data
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody LanguageDTO dto){
        ApiResponse apiResponse =languageService.edit(id,dto);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }



}
