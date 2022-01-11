import { Component, OnInit } from '@angular/core';
import {Store} from "@ngrx/store";
import {AppState} from "../../../interfaces/app-state";
import {ProductService} from "../../../services/product/product.service";
import {ProductDto} from "../../../dto/ProductDto";
import {BasePageComponent} from "../../base-page/base-page.component";
import {ProductShortDto} from "../../../dto/ProductShortDto";
import {SnackbarsUtils} from "../../../utils/snackbars.utils";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent extends BasePageComponent {

  products: Array<ProductShortDto> = new Array<ProductShortDto>();

  constructor(store: Store<AppState>,
              private productService: ProductService,
              private snackbarsUtils: SnackbarsUtils,) {
    super(store);
    this.pageData = {
      title: "Список товаров",
      loaded: true
    };
  }

  ngOnInit(): void {
    super.ngOnInit();
    this.loadProducts();
  }

  public loadProducts() {
    this.productService.getAllProducts().subscribe(products => {
      this.products = products;
    });
  }

  public delete(product: ProductShortDto) {
    this.productService.deleteProduct(product.id).subscribe(products =>{
      this.products = products;
      this.snackbarsUtils.handleAnySuccess("Товар успешно удален");
    }, () => this.snackbarsUtils.handleAnyError("Ошибка при удалении"))
  }

}
