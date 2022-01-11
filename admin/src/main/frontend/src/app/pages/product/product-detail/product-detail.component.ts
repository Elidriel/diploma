import {Component, OnInit} from '@angular/core';
import {BasePageComponent} from "../../base-page/base-page.component";
import {Store} from "@ngrx/store";
import {AppState} from "../../../interfaces/app-state";
import {ProductService} from "../../../services/product/product.service";
import {ActivatedRoute} from "@angular/router";
import {ProductDto} from "../../../dto/ProductDto";
import {SizeDto} from "../../../dto/SizeDto";
import {ColorDto} from "../../../dto/ColorDto";
import {Observable} from "rxjs";
import {SnackbarsUtils} from "../../../utils/snackbars.utils";
import {CategoryService} from "../../../services/category/category.service";
import {CategoryDto} from "../../../dto/CategoryDto";

@Component({
	selector: 'app-product-detail',
	templateUrl: './product-detail.component.html',
	styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent extends BasePageComponent {

	product: ProductDto;
	allCategories: Array<CategoryDto>;
	availableSizes: Observable<Array<SizeDto>>;
	availableColors: Observable<Array<ColorDto>>;

	constructor(store: Store<AppState>,
							private categoryService:CategoryService,
							private productService: ProductService,
							private snackbarsUtils: SnackbarsUtils,
							private route: ActivatedRoute) {
		super(store);
		this.pageData = {
			title: "Информация о товаре",
			loaded: true
		};
	}

	ngOnInit(): void {
		super.ngOnInit();
		let productId = this.route.snapshot.paramMap.get("id");
		if (productId == "new") {
			this.product = new ProductDto();
		} else {
			this.loadProduct(productId);
		}
		this.loadColors(productId);
		this.loadSizes(productId);
		this.loadAllCategories();
	}

	public loadProduct(productId) {
		this.productService.getProduct(productId).subscribe(product => {
			this.product = product;
		});
	}

	public loadColors(productId) {
		this.availableColors = this.productService.getAvailableColorsByProduct(productId);
	}

	public loadSizes(productId) {
		this.availableSizes = this.productService.getAvailableSizesByProduct(productId);
	}

	public loadAllCategories() {
		this.categoryService.getAllCategories().subscribe(categories => {
			this.allCategories = categories;
		});
	}

	public save() {
		this.productService.saveProduct(this.product).subscribe(product => {
			this.product = product;
			this.snackbarsUtils.handleAnySuccess("Информация о товаре изменена");
		}, () => this.snackbarsUtils.handleAnyError("Ошибка при сохранении"));
	}
}
