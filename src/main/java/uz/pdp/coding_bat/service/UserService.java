package uz.pdp.coding_bat.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Users;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.UserDTO;
import uz.pdp.coding_bat.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public ApiResponse all() {
        return new ApiResponse("all",true,userRepository.findAll());
    }

    public ApiResponse one(Integer id) {
        if (userRepository.existsById(id)) {
            return new ApiResponse("found",true,userRepository.getById(id));
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse add(@NotNull UserDTO dto) {
        if (!userRepository.existsByEmail(dto.getEmail())) {
            Users user = new Users();

             user.setEmail(dto.getEmail());
             user.setPassword(dto.getPassword());

            Users save = userRepository.save(user);

            return new ApiResponse("added",true,save);
        }
        return new ApiResponse("already exists",false);
    }

    /**
     *
     * @param id from front
     * @param dto for payload information
     * @return edited user
     */
    public ApiResponse edit(Integer id, UserDTO dto) {
        if (userRepository.existsById(id)) {
            Users user = userRepository.getById(id);
            if (!userRepository.existsByEmailAAndIdNot(dto.getEmail())) {

                user.setEmail(dto.getEmail());
                user.setPassword(dto.getPassword());

                Users save = userRepository.save(user);

                return new ApiResponse("edited",true,save);
            }
        }
        return new ApiResponse("something went wrong",false);
    }
}
