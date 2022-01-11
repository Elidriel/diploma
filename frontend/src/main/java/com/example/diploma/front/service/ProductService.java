package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.CartDao;
import com.example.diploma.persistence.dao.ProductDao;
import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.dto.front.ProductFilterDto;
import com.example.diploma.persistence.dto.front.ProductDto;
import com.example.diploma.persistence.entity.CartEntity;
import com.example.diploma.persistence.entity.CartProductEntity;
import com.example.diploma.persistence.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductDao productDao;
    CartDao cartDao;
    UserDao userDao;

    public List<ProductDto> getProductsByCatalogUrl(String catalogUrl, ProductFilterDto productFilterDto) {
        List<ProductDto> products = productDao.findProductsByCatalogUrl(catalogUrl)
                .stream()
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getBrands()) || productFilterDto.getBrands().isEmpty()) {
                        return true;
                    }
                    return productFilterDto.getBrands().contains(entity.getBrand());
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getStartingPrice())) {
                        return true;
                    }
                    return entity.getPrice() > productFilterDto.getStartingPrice();
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getFinalPrice())) {
                        return true;
                    }
                    return entity.getPrice() < productFilterDto.getFinalPrice();
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getSizes()) || productFilterDto.getSizes().isEmpty()) {
                        return true;
                    }
                    return entity.getSizes().stream().anyMatch(size -> productFilterDto.getSizes().contains(size.getValue()));
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getColors()) || productFilterDto.getColors().isEmpty()) {
                        return true;
                    }
                    return entity.getColors().stream().anyMatch(color -> productFilterDto.getColors().contains(color.getValue()));
                })
                .map(ProductService::convertToProductDto)
                .collect(Collectors.toList());

        if (UserService.isAuthorized()) {
            setCartProductsInfo(products);
        }

        return products;
    }

    public List<ProductDto> getProductsByCatalogUrlAndCategoryUrl(String catalogUrl, String categoryUrl, ProductFilterDto productFilterDto) {
        List<ProductDto> products = productDao.findProductsByCatalogUrlAndCategoryUrl(catalogUrl, categoryUrl)
                .stream()
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getBrands()) || productFilterDto.getBrands().isEmpty()) {
                        return true;
                    }
                    return productFilterDto.getBrands().contains(entity.getBrand());
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getStartingPrice())) {
                        return true;
                    }
                    return entity.getPrice() > productFilterDto.getStartingPrice();
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getFinalPrice())) {
                        return true;
                    }
                    return entity.getPrice() < productFilterDto.getFinalPrice();
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getSizes()) || productFilterDto.getSizes().isEmpty()) {
                        return true;
                    }
                    return entity.getSizes().stream().anyMatch(size -> productFilterDto.getSizes().contains(size.getValue()));
                })
                .filter(entity -> {
                    if (Objects.isNull(productFilterDto.getColors()) || productFilterDto.getColors().isEmpty()) {
                        return true;
                    }
                    return entity.getColors().stream().anyMatch(color -> productFilterDto.getColors().contains(color.getValue()));
                })
                .map(ProductService::convertToProductDto)
                .collect(Collectors.toList());

        if (UserService.isAuthorized()) {
            setCartProductsInfo(products);
        }

        return products;
    }

    public List<String> getAllBrands() {
        return productDao.findAllBrands();
    }

    public static ProductDto convertToProductDto(ProductEntity productEntity) {

        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setImageName(productEntity.getImageName());
        productDto.setInCart(false);
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        return productDto;

    }

    private void setCartProductsInfo(List<ProductDto> products) {
        CartEntity cartEntity = cartDao
                .findByUserAndIsFramedIsFalse(userDao.findByUsername(UserService.getCurrentUser().getUsername()));

        if (Objects.isNull(cartEntity)) {
            return;
        }
        List<CartProductEntity> cartProducts = cartEntity.getProducts();

        List<Long> cartProductIds = cartProducts
                .stream()
                .map(CartProductEntity::getId)
                .map(CartProductEntity.CartProductId::getProductId)
                .collect(Collectors.toList());
        products.forEach(product -> {
            if (cartProductIds.contains(product.getId())) {
                product.setCount(
                        cartProducts
                                .stream()
                                .filter(cartProduct -> cartProduct.getId().getProductId().equals(product.getId()))
                                .findFirst()
                                .get()
                                .getCount()
                );
                product.setInCart(true);
            }
        });
    }
}
