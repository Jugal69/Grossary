package com.pkg.android.grossary.Adapter;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.pkg.android.grossary.model.CartItem;
import com.pkg.android.grossary.model.Product;

import java.util.List;

/**
 * Created by GAURAV on 06-03-2017.
 */
public class RetailListParent implements Parent<RetailList>,Comparable<RetailListParent> {
    private Product product;
    private int actual_stock;
    private int current_stock;
    private int expected_stock;
    private double colorval;
    private boolean selected;
    private CartItem cartItem;

    private List<RetailList> mChildrenList;

    public RetailListParent(Product product, int cartquantity) {
        this.product = product;
        this.actual_stock = cartquantity;
        this.cartItem = new CartItem(product,cartquantity);
    }


    public RetailListParent(Product p) {
        product = p;
        cartItem = new CartItem(p);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product cartItem) {
        this.product = cartItem;
        this.cartItem.setProduct(product);
    }

    public int incrementqty() {
        int newqty = ((RetailList)getChildList().get(0)).incrementqty();
        getCartItem().setCartquantity(newqty);
        return newqty;
    }

    public int decrementqty() {
        int newqty = ((RetailList)getChildList().get(0)).decrementqty();
        getCartItem().setCartquantity(newqty);
        return newqty;
    }

    public int getCurrent_stock() {
        return current_stock;
    }

    public void setCurrent_stock(int current_stoock) {
        this.current_stock = current_stoock;
    }

    public int getExpected_stock() {
        return expected_stock;
    }

    public void setExpected_stock(int expected_stock) {
        this.expected_stock = expected_stock;
    }

    public int getActual_stock() {
        return actual_stock;
    }

    public void setActual_stock(int actual_stock) {
        this.actual_stock = actual_stock;
        this.cartItem.setCartquantity(actual_stock);
    }

    public double getColorval() {
        return colorval;
    }

    public void setColorval(double colorval) {
        this.colorval = colorval;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    @Override
    public List getChildList() {
        return mChildrenList;
    }

    public void setChildList(List<RetailList> childrenList) {
        mChildrenList = childrenList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void updateActualStock(int days_past, int days_left,int previous_stock) {
        if(getCurrent_stock()<getExpected_stock()){
            //Scenario 1
            setActual_stock(getExpected_stock()-getCurrent_stock());
            setColorval(getActual_stock()/getExpected_stock());
        }else if(getCurrent_stock()>=getExpected_stock()){
            setActual_stock(0);
            setColorval(0);
        }else if(getCurrent_stock()==0){
            //setActual_stock(997);
            setActual_stock((int)(previous_stock*days_left/days_past));
            setColorval(1 - getActual_stock()/getExpected_stock());
        }else {
            //kind of error
            setActual_stock(999);
            setColorval(0);
        }

    }


    @Override
    public int compareTo(RetailListParent compareretailListParent) {
        //descending order
        //here we compare the color vals
        //because we have to consider the scaricty of the product and not the difference between C and A
        return (int) (compareretailListParent.getColorval() - this.getColorval());
    }


}
