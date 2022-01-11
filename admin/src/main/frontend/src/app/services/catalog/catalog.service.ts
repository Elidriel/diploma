import {Injectable} from '@angular/core';
import {BaseHttpService} from "../base/base-http.service";
import {UrlRegistry} from "../../utils/app.utils";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {CatalogDto} from "../../dto/CatalogDto";

@Injectable({
  providedIn: 'root'
})
export class CatalogService extends BaseHttpService {

  private catalogApiUrl = UrlRegistry.rootBackendUrl() + "/catalog";

  constructor(private httpClient: HttpClient) {
    super();
  }

  public getAllCatalogs(): Observable<Array<CatalogDto>> {
    return this.httpClient
      .get<Array<CatalogDto>>(this.catalogApiUrl + "/get-all")
      .pipe(catchError(this.handleError));
  }

  public getById(id): Observable<CatalogDto> {
    return this.httpClient
      .get<CatalogDto>(this.catalogApiUrl + "/get/" + id)
      .pipe(catchError(this.handleError));
  }

  public saveCatalog(catalog: CatalogDto): Observable<CatalogDto>{
    return  this.httpClient
      .post<CatalogDto>(this.catalogApiUrl + "/save", catalog)
      .pipe(catchError(this.handleError));
  }

  public deleteCatalog(catalogId: number): Observable<Array<CatalogDto>>{
    return  this.httpClient
      .delete<Array<CatalogDto>>(this.catalogApiUrl + "/delete?id=" + catalogId)
      .pipe(catchError(this.handleError));
  }


}
