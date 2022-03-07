package uz.pdp.coding_bat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.UserDTO;
import uz.pdp.coding_bat.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    /**
     * show all
     * @return
     */
    @GetMapping
    public ResponseEntity<?> all(){
        ApiResponse apiResponse = userService.all();

        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     *
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Integer id){
        ApiResponse apiResponse = userService.one(id);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     *
     * @param dto
     * @return added user
     */
    @PostMapping
    @Validated
    public ResponseEntity<?> add(@RequestBody UserDTO dto){
        ApiResponse apiResponse = userService.add(dto);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     *
     * @param id
     * @param dto
     * @return status for success or not
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody UserDTO dto){
        ApiResponse apiResponse = userService.edit(id,dto);
        return ResponseEntity.status( apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
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
