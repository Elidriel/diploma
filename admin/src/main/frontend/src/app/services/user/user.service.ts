import {Injectable} from '@angular/core';
import {BaseHttpService} from "../base/base-http.service";
import {HttpClient} from "@angular/common/http";
import {UrlRegistry} from "../../utils/app.utils";
import {Observable} from "rxjs";
import {UserDto} from "../../dto/UserDto";
import {catchError} from "rxjs/operators";

@Injectable({
	providedIn: 'root'
})
export class UserService extends BaseHttpService {

	private userApiUrl = UrlRegistry.rootBackendUrl() + "/user";

	constructor(private httpClient: HttpClient) {
		super();
	}

	public getAllUsers(): Observable<Array<UserDto>> {
		return this.httpClient
			.get<Array<UserDto>>(this.userApiUrl + "/get-all")
			.pipe(catchError(this.handleError));
	}

	public deleteUsers(userId: number): Observable<Array<UserDto>>{
		return  this.httpClient
			.delete<Array<UserDto>>(this.userApiUrl + "/delete?id=" + userId)
			.pipe(catchError(this.handleError));
	}

	public  blockUser(userId: number, active: boolean): Observable<Array<UserDto>> {
	 return  this.httpClient
		 .get<Array<UserDto>>( this.userApiUrl + "/block?id=" + userId + "&active=" + active)
		 .pipe(catchError(this.handleError));
	}
}
