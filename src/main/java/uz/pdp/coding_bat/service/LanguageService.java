package uz.pdp.coding_bat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Language;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.LanguageDTO;
import uz.pdp.coding_bat.repository.LanguageRepository;

@Service
@RequiredArgsConstructor
public class LanguageService {
    final LanguageRepository languageRepository;

    public ApiResponse all() {
        return  new ApiResponse("all",true,languageRepository.findAll());
    }

    public ApiResponse one(Integer id) {
        if (languageRepository.existsById(id)) {
            return new ApiResponse("found",true,languageRepository.getById(id));
        }
        return new ApiResponse("not found", false);
    }

    public ApiResponse delete(Integer id) {
        if (languageRepository.existsById(id)) {
            languageRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found", false);    }

    public ApiResponse add(LanguageDTO dto) {
        if (!languageRepository.existsByName(dto.getName())) {
            Language language = new Language();
            language.setName(dto.getName());
            Language save = languageRepository.save(language);

            return new ApiResponse("added",true,save);
        }
        return new ApiResponse("something went wrong",false);
    }

    public ApiResponse edit(Integer id, LanguageDTO dto) {
        if (languageRepository.existsById(id)) {
            Language language = languageRepository.getById(id);
            if (!languageRepository.existsByName(dto.getName())) {

                language.setName(dto.getName());
                Language save = languageRepository.save(language);

                return new ApiResponse("edited",true,save);
            }
        }
        return new ApiResponse("something went wrong",false);
    }
}
