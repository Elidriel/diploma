import {CategoryDto} from "./CategoryDto";
import {SizeDto} from "./SizeDto";
import {ColorDto} from "./ColorDto";

export class ProductDto {

	id: number;
	name: string;
	imageName: string;
	brand: string;
	price: number;
	category: CategoryDto;
	sizes: Array<SizeDto>;
	colors: Array<ColorDto>;

	constructor(id?: number, name?: string, imageName?: string, brand?: string, price?: number, category?: CategoryDto,
							sizes?: Array<SizeDto>, colors?: Array<ColorDto>) {
		this.id = id;
		this.name = name;
		this.imageName = imageName;
		this.brand = brand;
		this.price = price;
		this.category = category;
		this.sizes = sizes;
		this.colors = colors;
	}

}
