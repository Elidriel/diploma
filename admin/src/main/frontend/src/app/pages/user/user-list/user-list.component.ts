import {Component, OnInit} from '@angular/core';
import {BaseHttpService} from "../../../services/base/base-http.service";
import {BasePageComponent} from "../../base-page/base-page.component";
import {AppState} from "../../../interfaces/app-state";
import {Store} from "@ngrx/store";
import {ProductService} from "../../../services/product/product.service";
import {SnackbarsUtils} from "../../../utils/snackbars.utils";
import {UserService} from "../../../services/user/user.service";
import {UserDto} from "../../../dto/UserDto";

@Component({
	selector: 'app-user-list',
	templateUrl: './user-list.component.html',
	styleUrls: ['./user-list.component.scss']
})
export class UserListComponent extends BasePageComponent {

	users: Array<UserDto> = new Array<UserDto>();

	constructor(store: Store<AppState>,
							private userService: UserService,
							private snackbarsUtils: SnackbarsUtils,) {
		super(store);
		this.pageData = {
			title: "Список покупателей",
			loaded: true
		};
	}

	ngOnInit(): void {
		super.ngOnInit();
		this.loadUsers();
	}

	public loadUsers() {
		this.userService.getAllUsers().subscribe(users => {
			this.users = users;
			this.sortUsers();
		});
	}

	public getStatus(isActive: boolean): string {
		return isActive ? `Активный` : `Заблокирован`;
	}

	public delete(user: UserDto) {
		this.userService.deleteUsers(user.id).subscribe(users => {
			this.users = users;
			this.sortUsers();
			this.snackbarsUtils.handleAnySuccess("Пользователь успешно удален");
		}, () => this.snackbarsUtils.handleAnyError("Ошибка при удалении"));
	}

	public block(user: UserDto) {
		this.userService.blockUser(user.id, !user.isActive).subscribe(users =>{
			this.users = users;
			this.sortUsers();
			this.snackbarsUtils.handleAnySuccess("Статус пользователя изменен");
		}, () => this.snackbarsUtils.handleAnyError("Ошибка при изменении статуса"));
	}

	private sortUsers() {
		this.users.sort((e1, e2) => {
			if (e1.id < e2.id) {
				return -1;
			}
			if (e1.id > e2.id) {
				return 1;
			}
			return 0;
		});
	}
}
