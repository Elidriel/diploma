import {Component, OnInit} from '@angular/core';
import {BasePageComponent} from "../../base-page/base-page.component";
import {Store} from "@ngrx/store";
import {AppState} from "../../../interfaces/app-state";
import {CategoryService} from "../../../services/category/category.service";
import {SnackbarsUtils} from "../../../utils/snackbars.utils";
import {ActivatedRoute} from "@angular/router";
import {ProductDto} from "../../../dto/ProductDto";
import {CategoryDto} from "../../../dto/CategoryDto";
import {CatalogService} from "../../../services/catalog/catalog.service";
import {CatalogDto} from "../../../dto/CatalogDto";

@Component({
	selector: 'app-category-detail',
	templateUrl: './category-detail.component.html',
	styleUrls: ['./category-detail.component.scss']
})
export class CategoryDetailComponent extends BasePageComponent {

	category: CategoryDto;
	allCategories: Array<CategoryDto>;
	allCatalogs: Array<CatalogDto>;

	constructor(store: Store<AppState>,
							private categoryService: CategoryService,
							private catalogService: CatalogService,
							private snackbarsUtils: SnackbarsUtils,
							private route: ActivatedRoute) {
		super(store);
		this.pageData = {
			title: "Информация о категории",
			loaded: true
		};
	}

	ngOnInit(): void {
		super.ngOnInit();
		let categoryId = this.route.snapshot.paramMap.get("id");
		if (categoryId == "new") {
			this.category = new CategoryDto();
		}
		this.loadAllCategoriesAndSelectCurrent(categoryId);
		this.loadAllCatalogs();
	}

	public loadAllCategoriesAndSelectCurrent(categoryId) {
		this.categoryService.getAllCategories().subscribe(categories => {
			this.allCategories = categories;

			this.allCategories.forEach(category => {
				if (category.id == categoryId) {
					this.category = category;
				}
			})
		});
	}

	public loadAllCatalogs() {
		this.catalogService.getAllCatalogs().subscribe(catalogs => {
			this.allCatalogs = catalogs;
		})
	}

	public save() {
		this.categoryService.saveCategory(this.category).subscribe(category => {
			if (category.id == null) {
				this.snackbarsUtils.handleAnyError("Категория с таким url уже существует");
			} else {
				this.category = category;
				this.snackbarsUtils.handleAnySuccess("Информация о категории изменена");
			}
		}, () => this.snackbarsUtils.handleAnyError("Ошибка при сохранении"));
	}

}
