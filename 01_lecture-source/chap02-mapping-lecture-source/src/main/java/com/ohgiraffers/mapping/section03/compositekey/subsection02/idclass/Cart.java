package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;


import javax.persistence.*;

@Entity
@Table(name = "TBL_CART")
@IdClass(CompositeKey.class)
public class Cart {

    @Id
    @Column(name ="CART_OWNER")
    private int cartOwner;

    @Id
    @Column(name = "ADDED_BOOK")
    private int addedBook;

    @Column(name = "QUANTITY")
    private int quantity;

    protected Cart() {}

    public int getCartOwner() {
        return cartOwner;
    }

    public int getAddedBook() {
        return addedBook;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartOwner=" + cartOwner +
                ", addedBook=" + addedBook +
                ", quantity=" + quantity +
                '}';
    }

    public Cart(int cartOwner, int addedBook, int quantity) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
        this.quantity = quantity;
    }
}
