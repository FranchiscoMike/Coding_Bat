package uz.pdp.coding_bat.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.CategoryDTO;
import uz.pdp.coding_bat.payload.LanguageDTO;
import uz.pdp.coding_bat.service.CategoryService;
import uz.pdp.coding_bat.service.LanguageService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class CategoryController {
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


    final CategoryService categoryService;

    /**
     * show all
     *
     * @return all languages
     */
    @GetMapping
    public ResponseEntity<?> all() {
        ApiResponse apiResponse = categoryService.all();
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
        ApiResponse apiResponse = categoryService.one(id);
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
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     *
     * @param dto for making new category
     * @return added language
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryDTO dto){
        ApiResponse apiResponse = categoryService.add(dto);
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
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody CategoryDTO dto){
        ApiResponse apiResponse =categoryService.edit(id,dto);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }



}
