import { Injectable } from '@angular/core';
import {BaseHttpService} from "../base/base-http.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductDto} from "../../dto/ProductDto";
import {UrlRegistry} from "../../utils/app.utils";
import {catchError} from "rxjs/operators";
import {ProductShortDto} from "../../dto/ProductShortDto";
import {ColorDto} from "../../dto/ColorDto";
import {SizeDto} from "../../dto/SizeDto";

@Injectable({
  providedIn: 'root'
})
export class ProductService extends BaseHttpService {

  private productApiUrl = UrlRegistry.rootBackendUrl() + "/product";

  constructor(private httpClient: HttpClient) {
    super();
  }

  public getAllProducts(): Observable<Array<ProductShortDto>> {
    return this.httpClient
      .get<Array<ProductShortDto>>(this.productApiUrl + "/get-all")
      .pipe(catchError(this.handleError));
  }

  public getAvailableColorsByProduct(productId): Observable<Array<ColorDto>> {
    return this.httpClient
      .get<Array<ColorDto>>(this.productApiUrl + "/get-available-colors" + (productId == "new" ? "" : "?product-id=" + productId))
      .pipe(catchError(this.handleError));
  }

  public getAvailableSizesByProduct(productId): Observable<Array<SizeDto>> {
    return this.httpClient
      .get<Array<SizeDto>>(this.productApiUrl + "/get-available-sizes"  + (productId == "new" ? "" : "?product-id=" + productId))
      .pipe(catchError(this.handleError));
  }

  public getProduct(productId: number): Observable<ProductDto> {
    return this.httpClient
      .get<ProductDto>(this.productApiUrl + "/get?id=" + productId)
      .pipe(catchError(this.handleError));
  }

  public saveProduct(product: ProductDto): Observable<ProductDto>{
    return  this.httpClient
      .post<ProductDto>(this.productApiUrl + "/save", product)
      .pipe(catchError(this.handleError));
  }
  
  public deleteProduct(productId: number): Observable<Array<ProductShortDto>>{
    return  this.httpClient
      .delete<Array<ProductShortDto>>(this.productApiUrl + "/delete?id=" + productId)
      .pipe(catchError(this.handleError));
  }
}
