package com.example.diploma.front.controller;

import com.example.diploma.front.service.CatalogService;
import com.example.diploma.front.service.CategoryService;
import com.example.diploma.front.service.ColorService;
import com.example.diploma.front.service.ProductService;
import com.example.diploma.front.service.SizeService;
import com.example.diploma.persistence.dto.front.CatalogDto;
import com.example.diploma.persistence.dto.front.ProductFilterDto;
import com.example.diploma.persistence.dto.front.ProductDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatalogController {

    SizeService sizeService;
    ColorService colorService;
    CatalogService catalogService;
    ProductService productService;
    CategoryService categoryService;

    @GetMapping("/get-all")
    @ResponseBody
    public List<CatalogDto> getAllCatalogs() {
        return catalogService.getAlLCatalogs();
    }

    @GetMapping("/{catalogUrl}/category/{categoryUrl}")
    public String getCategory(@PathVariable("catalogUrl") String catalogUrl,
                              @PathVariable("categoryUrl") String categoryUrl,
                              @RequestParam(value = "startingPrice", required = false) Double startingPrice,
                              @RequestParam(value = "finalPrice", required = false) Double finalPrice,
                              @RequestParam(value = "sizes", required = false) List<Integer> sizes,
                              @RequestParam(value = "colors", required = false) List<String> colors,
                              @RequestParam(value = "brands", required = false) List<String> brands,
                              Model model) {

        if (!categoryService.existsByUrlAndCatalogUrl(catalogUrl, categoryUrl)) {
            return "404";
        }

        List<ProductDto> products = productService.getProductsByCatalogUrlAndCategoryUrl(
                catalogUrl,
                categoryUrl,
                new ProductFilterDto(startingPrice, finalPrice, sizes, colors, brands)
        );

        model.addAttribute("currentCatalog", catalogService.getCatalogByUrl(catalogUrl));
        model.addAttribute("categories", categoryService.getCategoriesByCatalogUrl(catalogUrl));
        model.addAttribute("currentCategory", categoryService.getCategoryByUrlAndCatalogUrl(catalogUrl, categoryUrl));
        model.addAttribute("products", products);
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("brands", productService.getAllBrands());
         return "catalog";

    }

    @GetMapping("/{catalogUrl}")
    public String getCatalog(@PathVariable("catalogUrl") String catalogUrl,
                             @RequestParam(value = "startingPrice", required = false) Double startingPrice,
                             @RequestParam(value = "finalPrice", required = false) Double finalPrice,
                             @RequestParam(value = "sizes", required = false) List<Integer> sizes,
                             @RequestParam(value = "colors", required = false) List<String> colors,
                             @RequestParam(value = "brands", required = false) List<String> brands,Model model) {

        if (!catalogService.existsByUrl(catalogUrl)) {
            return "404";
        }
        model.addAttribute("currentCatalog", catalogService.getCatalogByUrl(catalogUrl));
        model.addAttribute("categories", categoryService.getCategoriesByCatalogUrl(catalogUrl));
        model.addAttribute("products", productService.getProductsByCatalogUrl(catalogUrl, new ProductFilterDto(startingPrice, finalPrice, sizes, colors, brands)));
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("brands", productService.getAllBrands());
        return "catalog";

    }

}
