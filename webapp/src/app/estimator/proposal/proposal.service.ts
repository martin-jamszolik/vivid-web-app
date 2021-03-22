import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Proposal } from 'src/app/shared';

@Injectable({
  providedIn: 'root'
})
export class ProposalService {
  private proposalUrl = 'api/projects';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }


  /* GET Proposals by Project */
  getProposals(projectId: number): Observable<Proposal[]> {

    return this.http.get<Proposal[]>(`${this.proposalUrl}/${projectId}/proposals`)
      .pipe(
        catchError(this.handleError<Proposal[]>('getProposals', []))
      );
  }

  /* GET Proposal */
  getProposal(projectId: number, proposalId: number): Observable<Proposal> {

    return this.http.get<Proposal>(`${this.proposalUrl}/${projectId}/proposals/${proposalId}`)
      .pipe(
        catchError(this.handleError<Proposal>('getProposals', null))
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
