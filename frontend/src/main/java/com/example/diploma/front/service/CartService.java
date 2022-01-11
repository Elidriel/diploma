package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.CartDao;
import com.example.diploma.persistence.dao.CartProductDao;
import com.example.diploma.persistence.dao.ProductDao;
import com.example.diploma.persistence.dao.UserDao;
import com.example.diploma.persistence.dto.front.CartDto;
import com.example.diploma.persistence.entity.CartEntity;
import com.example.diploma.persistence.entity.CartProductEntity;
import com.example.diploma.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartDao cartDao;
    UserDao userDao;
    ProductDao productDao;
    CartProductDao cartProductDao;

    public CartDto getCartDto() {

        CartEntity cartEntity = getOrCreateCartEntity();
        return createCartDto(cartEntity);

    }

    public CartDto getCartDto(CartEntity cartEntity) {
        return createCartDto(cartEntity);
    }

    public void addProductToCart(Long productId) {

        CartEntity cart = getOrCreateCartEntity();
        CartProductEntity cartProductEntity = new CartProductEntity(new CartProductEntity.CartProductId(cart.getId(), productDao.getOne(productId).getId()), 1L);
        cartProductDao.save(cartProductEntity);
        cart.getProducts().add(cartProductEntity);
        cartDao.save(cart);

    }

    public void changeProductInCart(Long productId, Long count) {

        CartEntity cart = getOrCreateCartEntity();
        cart.getProducts()
                .stream()
                .filter(cartProduct -> cartProduct.getId().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Продукт для изменения не найден в корзине"))
                .setCount(count);
        cartDao.save(cart);

    }

    public void removeProductFromCart(Long productId) {

        CartEntity cartEntity = getOrCreateCartEntity();
        cartProductDao.delete(cartEntity.getProducts()
                .stream()
                .filter(cartProductEntity -> cartProductEntity.getId().getProductId().equals(productId))
                .findFirst()
                .get());

    }

    private CartEntity getOrCreateCartEntity() {

        UserEntity currentUser = userDao.findByUsername(UserService.getCurrentUser().getUsername());
        CartEntity cart = cartDao.findByUserAndIsFramedIsFalse(currentUser);

        if (Objects.isNull(cart)) {
            cart = new CartEntity();
            cart.setUser(currentUser);
            cart.setIsFramed(false);
            return cartDao.save(cart);
        }

        return cart;

    }

    private CartDto createCartDto(CartEntity cartEntity) {

        CartDto cartDto = new CartDto();
        cartDto.setProducts(cartEntity.getProducts()
                .stream()
                .map(CartProductEntity::getProduct)
                .map(ProductService::convertToProductDto)
                .collect(Collectors.toList()));
        cartDto.getProducts().forEach(productDto -> productDto.setCount(
                cartEntity.getProducts()
                        .stream()
                        .filter(cartProductEntity -> cartProductEntity.getId().getProductId().equals(productDto.getId()))
                        .findFirst()
                        .get()
                        .getCount()
        ));
        cartDto.setPriceSum(cartDto.getProducts().stream().flatMapToDouble(product -> DoubleStream.of(product.getPrice() * product.getCount())).sum());
        return cartDto;

    }

}
