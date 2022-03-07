package uz.pdp.coding_bat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ProblemDTO;
import uz.pdp.coding_bat.repository.CategoryRepository;
import uz.pdp.coding_bat.repository.ProblemRepository;
import uz.pdp.coding_bat.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProblemService {
    final ProblemRepository problemRepository;
    final CategoryRepository categoryRepository;
    final UserRepository userRepository;

    /**
     *  showing all problems
     * @return
     */
    public ApiResponse all() {
        return new ApiResponse("all",true,problemRepository.findAll());
    }

    public ApiResponse one(Integer id) {
        if (problemRepository.existsById(id)) {
            return new ApiResponse("found",true,problemRepository.getById(id));
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse delete(Integer id) {
        if (problemRepository.existsById(id)) {
            problemRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse add(ProblemDTO dto) {
        if (problemRepository.existsByName(dto.getName())) {
            Problem problem = new Problem();
            return Duplicable_code(dto, problem);
        }
        return new ApiResponse("something went wrong",false);
    }

    public ApiResponse edit(Integer id, ProblemDTO dto) {
        if (problemRepository.existsById(id)) {
            Problem problem = problemRepository.getById(id);
            if (problemRepository.existsByNameAndIdNot(dto.getName())) {

                Duplicable_code(dto, problem);
            }
        }
        return new ApiResponse("something went wrong",false);
    }

    private ApiResponse Duplicable_code(ProblemDTO dto, Problem problem) {
        problem.setName(dto.getName());
        problem.setDescription(dto.getDescription());
        problem.setSolution(dto.getSolution());
        if (categoryRepository.existsById(dto.getCategoryId())){
            problem.setCategory(categoryRepository.getById(dto.getCategoryId()));
            if (userRepository.existsById(dto.getUserId())) {
                problem.setUser(userRepository.getById(dto.getUserId()));
                Problem save = problemRepository.save(problem);

                return new ApiResponse("added",true,save);
            }
        }
        return new ApiResponse("something went wrong",false);
    }
}
