package com.dalvinlabs.recyclerviewmultiselect;

import android.databinding.ObservableInt;

public class ViewModel {

  public final String name;
  public final String size;
  public final String color;
  public final String price;


  public ViewModel(Branches product) {
    this.name = product.getBranch();
    this.size = product.getPercent();
    this.color = product.getSeats_avali();
    this.price = product.getSeats_fill();
  }
}


