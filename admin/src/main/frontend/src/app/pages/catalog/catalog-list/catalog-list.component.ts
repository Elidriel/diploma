import { Component, OnInit } from '@angular/core';
import {BasePageComponent} from "../../base-page/base-page.component";
import {CategoryDto} from "../../../dto/CategoryDto";
import {CatalogDto} from "../../../dto/CatalogDto";
import {Store} from "@ngrx/store";
import {AppState} from "../../../interfaces/app-state";
import {CategoryService} from "../../../services/category/category.service";
import {CatalogService} from "../../../services/catalog/catalog.service";
import {SnackbarsUtils} from "../../../utils/snackbars.utils";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-catalog-list',
  templateUrl: './catalog-list.component.html',
  styleUrls: ['./catalog-list.component.scss']
})
export class CatalogListComponent extends BasePageComponent {

  catalogs: Array<CatalogDto> = new Array<CatalogDto>();

  constructor(store: Store<AppState>,
              private catalogService: CatalogService,
              private snackbarsUtils: SnackbarsUtils,
              private route: ActivatedRoute) {
    super(store);
    this.pageData = {
      title: "Список каталогов",
      loaded: true
    };
  }

  ngOnInit(): void {
    super.ngOnInit();
    this.loadCatalogs();
  }

  public loadCatalogs() {
    this.catalogService.getAllCatalogs().subscribe(catalogs => {
      this.catalogs = catalogs;
    });
  }

  public delete(catalog: CatalogDto) {
    this.catalogService.deleteCatalog(catalog.id).subscribe(catalogs => {
      this.catalogs = catalogs;
      this.snackbarsUtils.handleAnySuccess("Каталог успешно удален");
    }, () => this.snackbarsUtils.handleAnyError("Ошибка при удалении"));
  }

}
