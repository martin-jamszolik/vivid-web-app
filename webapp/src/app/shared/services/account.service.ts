import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '@app/shared/models';

@Injectable({
    providedIn: 'root'
})
@Injectable()
export class AccountService {
    private userSubject: BehaviorSubject<User>;

    constructor(
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject<User>(null);
    }

    public get userValue(): User {
        return this.userSubject.value;
    }

    login(username, password): Observable<HttpResponse<any>> {

        return this.http.post<any>('auth/login', { username, password }, { observe: 'response' })
        .pipe(
            map( (response: HttpResponse<any>) => {
                if (response.status === 200) {
                    this.userSubject.next( new User(username));
                } else {
                    this.userSubject.next( null);
                }
                return response;
            })
        );

    }

    clearUser(){
        this.userSubject.next(null);
    }

    logout() {
        this.userSubject.next(null);
        return this.http.post('auth/logout', {});
    }

}
