import { Task } from './task.model';
import { TreeNode } from 'primeng/api';

export class Scope implements TreeNode {

    get data(): any {
        return { name: this.scopeName, total: this.total() };
    }
    get children(): TreeNode[] { return this.tasks; }

    id: string;
    scopeName: string;
    tasks: Task[] = [];

    expanded = true;


    total(): number {
        return this.tasks.reduce((sum, current) => sum + current.total(), 0);
    }


}
