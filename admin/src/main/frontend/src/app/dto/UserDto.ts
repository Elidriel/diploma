export class UserDto {

	id: number;
	fullname: string;
	phoneNumber: string;
	isActive: boolean;
	city: string;
	country: string;
	email: string;


	constructor(id?: number, fullname?: string, phoneNumber?: string, isActive?: boolean,
							city?: string, country?: string, email?: string) {
		this.id = id;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
		this.city = city;
		this.country = country;
		this.email = email;
	}
}