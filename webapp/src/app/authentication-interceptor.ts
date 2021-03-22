import { Injectable } from '@angular/core';
import { map, catchError } from 'rxjs/operators';
import {
    HttpClient,
    HttpHeaders,
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpResponse,
    HttpErrorResponse
} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError, of } from 'rxjs';
import { AccountService } from '@app/shared';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {


    constructor(private userService: AccountService, private router: Router) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const clonedRequest =
            request.clone(
                {withCredentials: true}
            );
        return next.handle(clonedRequest)
            .pipe(
                map((event: HttpEvent<any>) => {
                    if (event instanceof HttpResponse) {
                        console.log('Http Response event... ' + event.status);
                    }
                    return event;
                }),
                catchError(error => {
                    console.log('Error response status: ', error.status);
                    if (error.status === 401) {
                        this.userService.clearUser();
                        this.router.navigateByUrl('/account/login');
                    }
                    return throwError(error);
                }));

    }
}
