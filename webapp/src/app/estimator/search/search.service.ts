import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Project,Page } from 'src/app/shared';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private projectsUrl = 'api/projects';

  public lastSearchResult: Page<Project>;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }


  /* GET projects */
  search(term: string,offset:number, limit:number): Observable<Page<Project>> {
    const params = new HttpParams()
      .set('search', term)
      .set('offset', offset)
      .set("limit",limit);

    return this.http.get<Page<Project>>(`${this.projectsUrl}`,{params})
      .pipe(
        catchError(this.handleError<Page<Project>>('searchProjects', new Page<Project>()))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


}
