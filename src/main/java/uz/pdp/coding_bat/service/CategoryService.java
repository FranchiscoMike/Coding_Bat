package uz.pdp.coding_bat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Category;
import uz.pdp.coding_bat.entity.Language;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.CategoryDTO;
import uz.pdp.coding_bat.repository.CategoryRepository;
import uz.pdp.coding_bat.repository.LanguageRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
     final LanguageRepository languageRepository;

    public ApiResponse all() {
        return  new ApiResponse("all",true,categoryRepository.findAll());
    }

    public ApiResponse one(Integer id) {
        if (categoryRepository.existsById(id)) {
            return new ApiResponse("found",true,categoryRepository.getById(id));
        }
        return new ApiResponse("not found", false);
    }

    public ApiResponse delete(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found", false);    }

    public ApiResponse add(CategoryDTO dto) {
        if (!categoryRepository.existsByName(dto.getName())) {
            Category category = new Category();
            if (languageRepository.existsById(dto.getLanguageId())){

            category.setName(dto.getName());
            category.setLanguage(languageRepository.getById(dto.getLanguageId()));
                Category save = categoryRepository.save(category);
                return new ApiResponse("added",true,save);
            }
        }
        return new ApiResponse("something went wrong",false);
    }

    public ApiResponse edit(Integer id, CategoryDTO dto) {
        if (categoryRepository.existsById(id)) {
            Category category = categoryRepository.getById(id);
            if (!categoryRepository.existsByNameAndIdNot(dto.getName())) {

                category.setName(dto.getName());
                Category save = categoryRepository.save(category);

                return new ApiResponse("edited",true,save);
            }
        }
        return new ApiResponse("something went wrong",false);
    }
}
