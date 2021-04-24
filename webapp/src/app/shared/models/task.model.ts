import { TreeNode } from 'primeng/api';

export class Task implements TreeNode {

    constructor( id: number= 0, userId: string= '', detail: string= '', name: string= '', qty: number= 0, cost: number= 0){
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.cost = cost;
        this.taskIdentifier = userId;
        this.detail = detail;

    }


    get data(): any {
        return { id: this.taskIdentifier,
            name: `${this.taskIdentifier ? this.taskIdentifier + ' - ' : ''}${this.name}`,
            detail: this.detail ,
            qty: this.qty,
            qtyUnit: `${this.qty} ${this.unit}`,
            cost: this.cost,
            unit: this.unit,
            total: this.total() };
    }

    id: number;
    index: number;
    taskIdentifier: string;
    proposalId: number;
    scope: string;
    name: string;
    detail: string;
    qty: number;
    cost: number;
    unit: string;
    taskType: string;
    expanded = true;

    children: TreeNode[];
    leaf = true;


    total(): number {
        return this.qty * this.cost;
    }

}
