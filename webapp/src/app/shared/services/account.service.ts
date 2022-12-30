import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DateTime } from "luxon";

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

    login(username: string, password: string): Observable<HttpResponse<string>> {

        return this.http.post<any>('auth/jwt/login', { username, password })
        .pipe(
            map( (res: any) => {
                this.setSession(res)
                return res
            })
        );

    }

    isLoggedIn(): boolean {
        return DateTime.now() < this.getExpiration()
    }

    isLoggedOut() {
        return !this.isLoggedIn()
    }

    getExpiration(): DateTime {
        const expiration = localStorage.getItem("vivid_expires_at")
        const expiresAt = JSON.parse(expiration)
        return expiresAt != undefined ? DateTime.fromMillis(expiresAt): undefined
    }  

    private setSession(tokenPayload) {
        const expiresAt = DateTime.fromMillis( tokenPayload.expires * 1000 )
        localStorage.setItem('vivid_id_token', tokenPayload.token);
        localStorage.setItem("vivid_expires_at", JSON.stringify(expiresAt.valueOf()) );
    }  

    clearUser(){
        this.userSubject.next(null);
        localStorage.removeItem("vivid_id_token");
        localStorage.removeItem("vivid_expires_at");
    }

    logout() {
      this.clearUser
    }
}
