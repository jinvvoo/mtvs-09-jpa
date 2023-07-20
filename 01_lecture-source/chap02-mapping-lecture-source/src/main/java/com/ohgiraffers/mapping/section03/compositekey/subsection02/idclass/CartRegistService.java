package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartRegistService {

    private CartRepository cartRepository;

    public CartRegistService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void addItemToCart(CartDTO cartInfo) {

        Cart cart = new Cart(
                cartInfo.getCartOwnerMemberNo(),
                cartInfo.getAddedBookNo(),
                cartInfo.getQuantity()
        );

        cartRepository.save(cart);
    }
}
