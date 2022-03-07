package uz.pdp.coding_bat.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ProblemDTO;
import uz.pdp.coding_bat.repository.ProblemRepository;
import uz.pdp.coding_bat.service.ProblemService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/problem")
@RequiredArgsConstructor
public class ProblemController {
    final ProblemRepository problemRepository;

    final ProblemService problemService;

    /**
     * all return
     * @return
     */
    @GetMapping
    public ResponseEntity<?> all(){
        ApiResponse apiResponse = problemService.all();
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     *  get id from front
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<?> one(@PathVariable Integer id){
        ApiResponse apiResponse = problemService.one(id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * deleting from db
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = problemService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * adding to the db
     * @param dto
     * @return
     */
    @PostMapping
    @Validated // checking valid
    public ResponseEntity<?> add(@RequestBody ProblemDTO dto){
        ApiResponse apiResponse = problemService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     *  editing data on db with checking
     * @param id from front
     * @param dto
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody ProblemDTO dto){
        ApiResponse apiResponse = problemService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * showing error messages in the front
     * @param ex
     * @return
     */

    //hammag controllerda bo'ladi

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
