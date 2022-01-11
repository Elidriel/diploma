import {Component, OnInit} from '@angular/core';
import {BasePageComponent} from "../../base-page/base-page.component";
import {Store} from "@ngrx/store";
import {AppState} from "../../../interfaces/app-state";
import {CategoryService} from "../../../services/category/category.service";
import {SnackbarsUtils} from "../../../utils/snackbars.utils";
import {ActivatedRoute} from "@angular/router";
import {CategoryDto} from "../../../dto/CategoryDto";
import {ProductShortDto} from "../../../dto/ProductShortDto";

@Component({
	selector: 'app-category-list',
	templateUrl: './category-list.component.html',
	styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent extends BasePageComponent {

	categories: Array<CategoryDto> = new Array<CategoryDto>();

	constructor(store: Store<AppState>,
							private categoryService: CategoryService,
							private snackbarsUtils: SnackbarsUtils) {
		super(store);
		this.pageData = {
			title: "Список категорий",
			loaded: true
		};
	}

	ngOnInit(): void {
		super.ngOnInit();
		this.loadCategories();
	}

	public loadCategories() {
		this.categoryService.getAllCategories().subscribe(categories => {
			this.categories = categories;
		});
	}

	public delete(category: CategoryDto) {
		this.categoryService.deleteCategory(category.id).subscribe(categories => {
			this.categories = categories;
			this.snackbarsUtils.handleAnySuccess("Категория успешно удалена");
		}, () => this.snackbarsUtils.handleAnyError("Ошибка при удалении"));
	}
}
