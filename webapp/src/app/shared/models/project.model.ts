import { Client } from './client.model';

export class Project {

    id: number;
    title: string;
    address: string;
    company: string;
    estimator: string;
    status: string;
    client: Client;
    date: Date;
    
}
