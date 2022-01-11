export class ProductShortDto {

	id: number;
	name: string;
	brand: string;
	categoryName: string;

	constructor(id: number, name: string, brand: string, categoryName: string) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.categoryName = categoryName;
	}
}