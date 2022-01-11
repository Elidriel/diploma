import {Injectable} from '@angular/core';
import {BaseHttpService} from "../base/base-http.service";
import {UrlRegistry} from "../../utils/app.utils";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {CategoryDto} from "../../dto/CategoryDto";

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends BaseHttpService {

  private categoryApiUrl = UrlRegistry.rootBackendUrl() + "/category";

  constructor(private httpClient: HttpClient) {
    super();
  }

  public getAllCategories(): Observable<Array<CategoryDto>> {
    return this.httpClient
      .get<Array<CategoryDto>>(this.categoryApiUrl + "/get-all")
      .pipe(catchError(this.handleError));
  }

  public saveCategory(category: CategoryDto): Observable<CategoryDto>{
    return  this.httpClient
      .post<CategoryDto>(this.categoryApiUrl + "/save", category)
      .pipe(catchError(this.handleError));
  }

  public deleteCategory(categoryId: number): Observable<Array<CategoryDto>>{
    return  this.httpClient
      .delete<Array<CategoryDto>>(this.categoryApiUrl + "/delete?id=" + categoryId)
      .pipe(catchError(this.handleError));
  }

}
