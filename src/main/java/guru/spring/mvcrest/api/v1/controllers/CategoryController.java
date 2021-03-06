package guru.spring.mvcrest.api.v1.controllers;

import guru.spring.mvcrest.api.v1.model.CategoryDTO;
import guru.spring.mvcrest.api.v1.model.CategoryListDTO;
import guru.spring.mvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/api/v1/customers")
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){

        return new ResponseEntity<CategoryListDTO>(
            new CategoryListDTO(
                    categoryService.getAllCategories()),
                    HttpStatus.OK
                );
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<CategoryDTO>(
                    categoryService.getCategoryByName(name),
                    HttpStatus.OK
                );
    }

}
