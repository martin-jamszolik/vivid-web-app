import { Scope } from './scope.model';

export class Proposal {

    id: number;
    projectId: number;
    title: string;
    date: Date;
    profitType: string;
    status: string;
    authorized: Date;
    editable = false;
    accepted = false;

    scopes: Scope[];


}
