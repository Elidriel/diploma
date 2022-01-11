import {CatalogDto} from "./CatalogDto";

export class CategoryDto {

	id: number;
	name: string;
	url: string;
	catalogDto: CatalogDto;

	constructor(id?: number,
							name?: string,
							url?: string,
							catalogDto?: CatalogDto) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.catalogDto = catalogDto;
	}
}