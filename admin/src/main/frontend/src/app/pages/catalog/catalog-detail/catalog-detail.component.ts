import {Component, OnInit} from '@angular/core';
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
	selector: 'app-catalog-detail',
	templateUrl: './catalog-detail.component.html',
	styleUrls: ['./catalog-detail.component.scss']
})
export class CatalogDetailComponent extends BasePageComponent {

	catalog: CatalogDto;

	constructor(store: Store<AppState>,
							private catalogService: CatalogService,
							private snackbarsUtils: SnackbarsUtils,
							private route: ActivatedRoute) {
		super(store);
		this.pageData = {
			title: "Информация о каталоге",
			loaded: true
		};
	}

	ngOnInit() {
		super.ngOnInit();
		let catalogId = this.route.snapshot.paramMap.get("id");
		if (catalogId == "new") {
			this.catalog = new CatalogDto();
		} else {
			this.catalogService.getById(catalogId).subscribe(catalog => {
				this.catalog = catalog;
			})
		}
	}

	public save() {
		this.catalogService.saveCatalog(this.catalog).subscribe(catalog => {
			if (catalog.id == null) {
				this.snackbarsUtils.handleAnyError("Каталог с таким url уже существует");
			} else {
				this.catalog = catalog;
				this.snackbarsUtils.handleAnySuccess("Информация о каталоге изменена");
			}
		}, () => this.snackbarsUtils.handleAnyError("Ошибка при сохранении"));
	}

}
